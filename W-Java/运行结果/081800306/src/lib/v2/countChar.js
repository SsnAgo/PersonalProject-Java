const { symbol } = require('../../config').reg;

function countChar(str) {
  let arr = str.split('');
  return arr.length;
}
function countRN(str) {
  let arr = str.match(symbol);
  return arr == null ? [] : arr;
}
module.exports = { countChar, countRN };
