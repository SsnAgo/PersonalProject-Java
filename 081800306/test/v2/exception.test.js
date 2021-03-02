/* eslint-disable */
const { NotFindException } = require('../../src/exception');

function compileAndroidCode() {
  throw new NotFindException('资源未找到');
}

test('compiling android goes as expected', () => {
  expect(() => compileAndroidCode()).toThrow(NotFindException);
});