const App = require('./core/index')
let readFile = process.argv[2]
let writeFile = process.argv[3]

let app = new App('a.txt',writeFile)
app.getMessage()