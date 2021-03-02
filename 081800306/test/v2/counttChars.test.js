/* eslint-disable */
const {countChar} = require('../../src/lib/v2/countChar');

test('测试字符个数',() => {
  let obj = `abcd,
hello`
  expect(countChar(obj)).toBe(11)
})