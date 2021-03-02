/**
 *
 * @param  arr type: Array  单词列表
 * @param  num type: number 返回前几个
 */

function rank(wordMap, num) {
  let arr = [];
  wordMap.forEach((value, key) => {
    let o = {
      [key]: value,
    };
    arr.push(o);
  });
  function compare2(a, b) {
    const key1 = Object.keys(a)[0];
    const key2 = Object.keys(b)[0];
    return b[key2] - a[key1];
  }
  function compare1(a, b) {
    const key1 = Object.keys(a)[0];
    const key2 = Object.keys(b)[0];
    if (a[key1] === b[key2]) {
      return key1 < key2 ? -1 : 1;
    }
    return 0;
  }
  // arr.sort(compare1);
  arr.sort(compare2);
  arr = arr.slice(0, 10);
  arr.sort(compare1);
  let dataMsg = {};
  for (let i = 0; i < num; i += 1) {
    dataMsg = { ...dataMsg, ...arr[i] };
  }
  return dataMsg;
}

function classArr(arr, wordMap) {
  arr.forEach((item) => {
    if (wordMap.has(item)) {
      let num = wordMap.get(item);
      wordMap.set(item, num + 1);
    } else {
      wordMap.set(item, 1);
    }
  });
  return wordMap;
}
module.exports = { rank, classArr };
