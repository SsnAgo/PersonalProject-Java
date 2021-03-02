const fs = require('fs');
const { handleData } = require('./handleData');
const countCharNum = require('./countChar.');
const countWord = require('./countWord');
const rank = require('./rank');

module.exports = class app {
  constructor(readFile, writeFile) {
    this.readFile = readFile;
    this.writeFile = writeFile;
    this.message = {};
  }

  getMessage() {
    const data = fs.readFileSync(this.readFile, { encoding: 'utf8' });
    const { lines, arr, restCount } = handleData(data);
    const { words, table } = countWord(arr);
    this.message = {
      characters: countCharNum(arr, restCount),
      words,
      lines,
      ...rank(table, 10),
    };
    if (this.writeFile) {
      this.writeToFile();
    }
  }

  writeToFile() {
    const data = this.message;
    for (const key in data) {
      if (Object.hasOwnProperty.call(data, key)) {
        fs.appendFileSync(this.writeFile, `${key}: ${data[key]}\n`, { encoding: 'ascii' });
      }
    }
  }
};
