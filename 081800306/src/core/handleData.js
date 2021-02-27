/**
 *
 * @param  data type: string
 * data是文件读取的内容
 * 功能：
 *  1.处理文本内容，过滤汉字和非ascii码
 *  2.计算出原始文本的行数 lines
 *  3.返回一个对象，{ lines: , arr: [] , restCount: }
 */
const { han, symbol, asciiRex } = require('../config').reg;

function countRows(data) {
  if (data.match(symbol)) {
    return data.match(symbol).length + 1;
  }
  return 1;
}

function handleData(data) {
  const lines = countRows(data);
  const dataCopy = data.replace(symbol, ' ');
  let restCount = 0;
  let arr = dataCopy.split('');
  arr = arr.map((word) => {
    if (!han.test(word) && !symbol.test(word) && asciiRex.test(word)) {
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
