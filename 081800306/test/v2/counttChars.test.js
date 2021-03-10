/* eslint-disable */
const {countChar} = require('../../src/lib/v2/countChar');
// 测试计算换行符
test('测试字符个数',() => {
  let obj1 = `abcd,
hello`
  expect(countChar(obj1)).toBe(11)
})

// 测试计算空格
test('测试字符个数',() => {
  let obj1 = `abcd, hello`
  expect(countChar(obj1)).toBe(11)
})