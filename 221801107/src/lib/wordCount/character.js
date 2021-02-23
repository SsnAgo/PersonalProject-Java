const filterChinese = require("./utils/filterChinese");

const getCharacter = (content) => filterChinese(content);

const calCharacterCount = (content) => getCharacter(content).length;

module.exports = {
  getCharacter,
  calCharacterCount,
};
