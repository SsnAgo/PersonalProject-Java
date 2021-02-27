module.exports = {
  reg: {
    han: /[\u4e00-\u9fa5]+/, // 匹配汉字的正则
    symbol: /[\n\r]{1,2}/g, // 匹配换行符
    asciiRex: /[\x00-\xff]+/, // 匹配ascii码
    wordRex: /[A-Za-z]{4,}[\'\-]?[A-Za-z0-9]*/gi, // 匹配单词
  },
};
