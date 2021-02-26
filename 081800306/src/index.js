const fs = require('fs')
const handleData = require('./core/handleData')
const countCharNum = require('./core/countChar.')
const countWord = require('./core/countWord')
const rank = require('./core/rank')
let readFile = process.argv[2]
let writeFile = process.argv[3]
let message = {} // 最后的输出数据

let data = fs.readFileSync('a.txt',{encoding: 'utf8'})
const {lines,arr,restCount} = handleData(data)
const {words, table}  = countWord(arr)
message = {
  characters: countCharNum(arr,lines,restCount),
  words,
  lines,
  ...rank(table,3)
}

fs.writeFileSync(writeFile, JSON.stringify(message) )
/**
 *  在ascii编码中，汉字一个字节，换行符两个字节，标点符号一个字节，水平制表符
 */
