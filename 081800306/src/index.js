const fs = require('fs')
let readFile = process.argv[2]
let writeFile = process.argv[3]
let message = {} // 最后的输出数据
const han = /[\u4e00-\u9fa5]+/ //匹配汉字的正则
const symbol = /[\n\r]+/
const asciiRex = /[\x00-\xff]+/
const wordRex = /[A-Za-z]{4,}[\'\-]?[A-Za-z0-9]*/gi
let wordCount = 0
let lineBreaks = 0
let rows = 0
let data = fs.readFileSync('a.txt',{encoding: 'utf8'})
let arr = data.split('')




arr = arr.filter(word => {
  // return !han.test(word)
  // if(asciiRex.test(word)){
  //   console.log(word);
  // }
  if(symbol.test(word)){
    lineBreaks++
  }
  return !han.test(word) && !symbol.test(word) && asciiRex.test(word)
})
let res = arr.join('')

let str = res.match(wordRex)
rows = lineBreaks/2 + 1
wordCount = arr.length + rows-1
// console.log(countNum(str)); 

message = {
  characters: wordCount,
  words: str.length,
  lines: rows,
  ...countNum(str)
}

fs.writeFileSync(writeFile, JSON.stringify(message) )
/**
 *  在ascii编码中，汉字一个字节，换行符两个字节，标点符号一个字节，水平制表符
 */

 /**
  * 
  * @param  arr: Array 
  */
 
function countNum(arr){
  let obj = {
  }
  arr.sort()
  var proxy = new Proxy(obj,{
    get: function(obj,key){
      if(key in obj){
        return obj[key]
      }else{
        return 0
      }
    }
  })
  arr.forEach( item => {
    proxy[item]++;
  });
  let data = []
  for (const key in obj) {
    let o = {}
    o[key] = obj[key]
    data.push(o)
  }
  data.sort(function(a,b){
    let key1 = Object.keys(a)[0]
    let key2 = Object.keys(b)[0]
    return b[key2] - a[key1]
  })
  let dataMsg = {}
  for (let i = 0; i<3;i++){
    dataMsg = {...dataMsg,...data[i]}
  }
  return dataMsg
}