/* eslint-disable */
const countCharNum = require('../../src/lib/v1/countChar.');
const {handleData} = require('../../src/lib/v1/handleData');

test('测试字符个数',() => {
  let obj = handleData(`abcd,
hello`)
  expect(countCharNum(obj.arr,obj.restCount)).toBe(11)
})