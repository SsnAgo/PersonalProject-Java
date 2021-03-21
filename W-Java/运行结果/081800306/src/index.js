const fs = require('fs');
const App = require('./lib/v2/index');
// const App = require('./lib/v1/index');
const { NotFindException, ParamException } = require('./exception');

console.time('test');
const readFile = process.argv[2];
const writeFile = process.argv[3];
try {
  if (!fs.existsSync(readFile)) {
    throw new NotFindException('input文件不存在');
  }
  if (!writeFile) {
    throw new ParamException('output不能为空');
  }
} catch (err) {
  console.error(err);
}
const app = new App(readFile, writeFile);
// app.getMessage(); // v1
app.handle(); // v2
// console.timeEnd('test'); // v1
