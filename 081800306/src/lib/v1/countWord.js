const { wordRex, wordSplit } = require('../../config').reg;
/**
 *
 * @param  arr type: Array
 *
 */
function countWord(arr) {
  const res = arr.join('').toLocaleLowerCase();
  const data = res.split(wordSplit);
  const str = data.filter((item) => {
    let bool = wordRex.test(item);
    return bool;
  });
  return {
    words: str.length,
    table: str,
  };
}
module.exports = countWord;
