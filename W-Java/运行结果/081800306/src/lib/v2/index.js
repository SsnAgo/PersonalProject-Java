/* eslint-disable */
const handleData = require('./handleData')

module.exports = class app {
  constructor(readFile, writeFile) {
    this.readFile = readFile;
    this.writeFile = writeFile;
    this.message = {};
  }

  handle() {
    handleData(this.readFile,this.writeFile)
  }
};
