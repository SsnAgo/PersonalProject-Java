module.exports = {
  reg: {
    han: /[\u4e00-\u9fa5]+/, // 匹配汉字的正则
    symbol: /\n/g, // 匹配换行符
    asciiRex: /[\x00-\xff]+/, // 匹配ascii码
    wordRex: /[a-zA-Z]{4}([a-zA-Z0-9])*/, // 匹配单词
    wordSplit: /[^A-Za-z0-9]+/, // 切割单词
  },
};
