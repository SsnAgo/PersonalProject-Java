const fs = require('fs');
const App = require('./core/index');
const { NotFindException, ParamException } = require('./exception');

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
const app = new App('a.txt', writeFile);
app.getMessage();
