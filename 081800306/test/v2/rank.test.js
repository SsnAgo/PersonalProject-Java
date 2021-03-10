/* eslint-disable */
const {rank,classArr} = require('../../src/lib/v2/rank')
let table = [
  'windows2000',
  'windows98',
  'windows95',
]
let wordMap = new Map();
test('测试单词统计', () => {
  wordMap = classArr(table,wordMap)
  expect([...wordMap]).toEqual([['windows2000',1],['windows98',1],['windows95',1]])
})
test('rank测试单词排行hot', () => {
  expect(rank(wordMap,2)).toEqual({'windows2000': 1,'windows95': 1})
})