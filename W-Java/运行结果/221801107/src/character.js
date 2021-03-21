const filterChinese = require("./filterChinese");

const getCharacter = (content) => filterChinese(content);

const calCharacterCount = (content) => getCharacter(content).length;

module.exports = {
  getCharacter,
  calCharacterCount,
};
