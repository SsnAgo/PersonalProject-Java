/* eslint-disable */
const countRows = require('../../src/lib/v2/countRows');

test('测试字符个数',() => {
  let obj = `abcd,
hello`
  expect(countRows(obj)).toBe(2)
})