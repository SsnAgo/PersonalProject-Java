const fs = require('fs');
const countWords = require('./countWords');
const { countChar } = require('./countChar');
const countRows = require('./countRows');
const { rank } = require('./rank');

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
  // let lines = 0;
  let words = 0;
  // let arr = [];
  let characters = 0;
  let rankObj;
  let longWord = '';
  readable.setEncoding('utf8');

  readable.on('data', (chunk) => {
    characters += countChar(chunk);
    // lines += countRows(chunk);
    // let table = countWords(chunk);
    // arr = [...arr, ...table];
    // words += table.length;
    longWord += chunk;
  });

  readable.once('close', () => {
    let table = countWords(longWord);
    words = table.length;
    rankObj = rank(table, 10);
    let data = {
      characters,
      words,
      lines: countRows(longWord),
      ...rankObj,
    };
    writeToFile(data, writeFile);
    console.timeEnd('test');
  });
}
module.exports = handleData;
