/* eslint-disable */
const countCharNum = require('../src/core/countChar.');
const {handleData} = require('../src/core/handleData');

test('测试字符个数',() => {
  let obj = handleData(`abcd,
hello`)
  expect(countCharNum(obj.arr,obj.restCount)).toBe(11)
})