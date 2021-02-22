import { WORD_SPLIT_REGEX } from './constants';
import filterChinese from './utils/filterChinese';

const getWord = (content) => filterChinese(content).split(WORD_SPLIT_REGEX);

const calWordCount = (content) => getWord(content).length;

const getWordsFrequency = (content) => {
  const wordArr = getWord(content);
  const wordMap = new Map();
  wordArr.forEach((word) => {
    if (!wordMap.has(word)) {
      wordMap.set(word, 0);
    }
    const count = wordMap.get(word);
    wordMap.set(word, count + 1);
  });
  const ret = [];
  wordMap.forEach((value, key) => {
    ret.push({
      word: key,
      count: value,
    });
  });
  return ret;
};

const calSortedWordsFrequency = (content, count) => {
  const arr = getWordsFrequency(content);
  const sortArr = arr.sort((a, b) => a.count > b.count);
  if (typeof count === 'undefined') {
    return sortArr;
  }
  return sortArr.slice(0, count);
};

export {
  getWord,
  calWordCount,
  getWordsFrequency,
  calSortedWordsFrequency,
};
