const fs = require('fs');
const countWords = require('./countWords');
const { countChar } = require('./countChar');
const countRows = require('./countRows');
const { rank, classArr } = require('./rank');

function writeToFile(data, writeFile) {
  for (const key in data) {
    if (Object.hasOwnProperty.call(data, key)) {
      fs.appendFileSync(writeFile, `${key}: ${data[key]}\n`, { encoding: 'ascii' });
    }
  }
}
function handleData(readFile, writeFile) {
  // console.time('test');
  const readable = fs.createReadStream(readFile);
  let lines = 0;
  let words = 0;
  let arr = [];
  let characters = 0;
  let wordMap = new Map();
  let rankObj;
  readable.setEncoding('ascii');

  readable.on('data', (chunk) => {
    characters += countChar(chunk);
    lines += countRows(chunk);
    let table = countWords(chunk);
    arr = [...arr, ...table];
    words += table.length;
    wordMap = classArr(table, wordMap);
  });

  readable.once('close', () => {
    rankObj = rank(wordMap, 10);
    let data = {
      characters,
      words,
      lines,
      ...rankObj,
    };
    writeToFile(data, writeFile);
    console.timeEnd('test');
  });
}
module.exports = handleData;
