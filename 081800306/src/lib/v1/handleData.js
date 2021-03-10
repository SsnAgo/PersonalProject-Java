/**
 *
 * @param  data type: string
 * data是文件读取的内容
 * 功能：
 *  1.处理文本内容，过滤汉字和非ascii码
 *  2.计算出原始文本的行数 lines
 *  3.返回一个对象，{ lines: , arr: [] , restCount: }
 */
const { symbol, asciiRex } = require('../../config').reg;

function countRows(data) {
  const arr = data.split(symbol);
  let lines = 0;
  for (let i = 0; i < arr.length; i++) {
    if (arr[i].trim()) {
      lines += 1;
    }
  }
  return lines;
}

function handleData(data) {
  const lines = countRows(data);
  let restCount = 0;
  let arr = data.split('');
  arr = arr.map((word) => {
    if (asciiRex.test(word)) {
      return word;
    }
    restCount += 1;
    return ' ';
  });
  return {
    lines,
    arr,
    restCount,
  };
}
module.exports = { handleData, countRows };
