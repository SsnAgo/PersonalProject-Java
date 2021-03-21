/* eslint-disable */
const countWord = require('../../src/lib/v1/countWord')
const {handleData} = require('../../src/lib/v1/handleData');

test('countWord测试统计单词个数',() => {
  let {arr} =  handleData(`abcd,
hello`)
  expect(countWord(arr).words).toBe(2)
})