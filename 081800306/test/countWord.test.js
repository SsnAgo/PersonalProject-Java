/* eslint-disable */
const countWord = require('../src/core/countWord')
const {handleData} = require('../src/core/handleData');

test('countWord测试统计单词个数',() => {
  let {arr} =  handleData(`abcd,
hello`)
  expect(countWord(arr).words).toBe(2)
})