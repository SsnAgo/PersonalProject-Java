const { wordRex } = require('../config').reg;
/**
 *
 * @param  arr type: Array
 *
 */
function countWord(arr) {
  const res = arr.join('');
  const str = res.match(wordRex);
  return {
    words: str.length,
    table: str,
  };
}
module.exports = countWord;
