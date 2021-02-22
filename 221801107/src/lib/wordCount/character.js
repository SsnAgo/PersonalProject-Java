import filterChinese from './utils/filterChinese';

const getCharacter = (content) => filterChinese(content);

const calCharacterCount = (content) => getCharacter(content).length;

export {
  getCharacter,
  calCharacterCount,
};
