/* eslint-disable */
const {handleData, countRows} = require('../../src/lib/v1/handleData');

test('handleData处理原始文本', () => {
  expect(handleData(`abcd,
hello`)).toEqual({ lines: 2, arr: ['a', 'b', 'c', 'd', ',','\n', 'h', 'e', 'l', 'l', 'o'], restCount: 0 });
})

test('countRows测试行数', () => {
  expect(countRows(`abc,
hello`)).toBe(2);
})