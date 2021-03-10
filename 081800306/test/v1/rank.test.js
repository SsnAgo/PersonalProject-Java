/* eslint-disable */
const rank = require('../../src/lib/v1/rank');
test('rank测试单词排行hot', () => {
  let table = [
    'windows2000',
    'windows98',
    'windows95',
  ]
  expect(rank(table,1)).toEqual({'windows2000': 1})
})