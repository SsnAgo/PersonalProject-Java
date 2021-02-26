const fs = require('fs')
const handleData = require('./handleData')
const countCharNum = require('./countChar.')
const countWord = require('./countWord')
const rank = require('./rank')

module.exports = class app {
  constructor(readFile,writeFile){
    this.readFile = readFile
    this.writeFile = writeFile
    this.message = {}
  }
  getMessage(){
    let data = fs.readFileSync('a.txt',{encoding: 'utf8'})
    const {lines,arr,restCount} = handleData(data)
    const {words, table}  = countWord(arr)
    this.message = {
      characters: countCharNum(arr,lines,restCount),
      words,
      lines,
      ...rank(table,3)
    }
    if(this.writeFile)
    fs.writeFileSync(this.writeFile, JSON.stringify(this.message) )
  }
}
