const { symbol } = require('../../config').reg;

function countRows(str) {
  let arr = str.split(symbol);
  let rows = 0;
  for (let i = 0; i < arr.length; i++) {
    if (arr[i].trim()) {
      rows += 1;
    }
  }
  return rows;
}
module.exports = countRows;
