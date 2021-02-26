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

function handleData(data) {
  let lineBreaks = 0;
  let restCount = 0;
  let arr = data.split('');
  arr = arr.map((word) => {
    if (symbol.test(word)) {
      lineBreaks += 1;
    }
    if (!han.test(word) && !symbol.test(word) && asciiRex.test(word)) {
      return word;
    }
    restCount += 1;
    return ' ';
  });
  return {
    lines: lineBreaks / 2 + 1,
    arr,
    restCount,
  };
}

module.exports = handleData;
