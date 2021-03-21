const { wordRex, wordSplit } = require('../../config').reg;

function countWords(str) {
  let table = [];
  const temp = str.split(wordSplit);
  table = temp.filter((item) => {// eslint-disable-line
    return wordRex.test(item); // eslint-disable-line
  });
  return table;
}

module.exports = countWords;
