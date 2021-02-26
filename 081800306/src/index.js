const fs = require('fs')
const App = require('./core/index')
const {NotFindException,paramException} = require('./exception')

let readFile = process.argv[2]
let writeFile = process.argv[3]
try {
  if(!fs.existsSync(readFile)){
    throw new NotFindException('input文件不存在')
  }
  if(!writeFile){
    throw new paramException('output不能为空')
  }
} catch (err) {
  console.log(err);
}
let app = new App('a.txt',writeFile)
app.getMessage()