/* eslint-disable */
const countWords = require('../../src/lib/v2/countWords');

test('测试单词个数',() => {
  let obj = `abcd,aaaa.
abcd.sdfgsfg`
  expect(countWords(obj).length).toBe(4)
})