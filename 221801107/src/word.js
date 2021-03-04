const { WORD_SPLIT_REGEX, WORD_REGEX } = require("./regex");
const Heap = require("./heap");
const filterChinese = require("./filterChinese");

const getWord = (content) => filterChinese(content)
  .toLowerCase()
  .split(WORD_SPLIT_REGEX)
  .filter((word) => WORD_REGEX.test(word));

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
  const sortArr = arr.sort((a, b) => {
    if (a.count === b.count) {
      return a.word < b.word ? -1 : 1;
    }
    return b.count - a.count;
  });
  if (typeof count === "undefined") {
    return sortArr;
  }
  return sortArr.slice(0, count);
};

const calSortedWordsFrequencyByHeap = (content, count) => {
  const arr = getWordsFrequency(content);
  const heap = new Heap(count, (a, b) => {
    if (a.count === b.count) {
      return a.word < b.word ? -1 : 1;
    }
    return b.count - a.count;
  });
  arr.forEach((value) => {
    heap.add(value);
  });
  const ret = [];
  while (!heap.empty()) {
    ret.push(heap.top());
    heap.pop();
  }
  return ret.reverse();
};

module.exports = {
  getWord,
  calWordCount,
  getWordsFrequency,
  calSortedWordsFrequency,
  calSortedWordsFrequencyByHeap,
};
