const {wordRex} = require('../config').reg
/**
 * 
 * @param  arr type: Array 
 *   
 */
function countWord(arr){
  let res = arr.join('')
  let str = res.match(wordRex)
  return {
    words: str.length,
    table: str
  }
}
module.exports = countWord