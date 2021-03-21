const Lib = require('./Lib/Lib')

const fs = require("fs")
const path = require("path")
const {resolve} = require("path")

const arg = process.argv
const filenames = arg.slice(2)
const commandPath = resolve('./')
const inputFile = filenames[0]
const outputFile = filenames[1]

let text

try {
    text = fs.readFileSync(path.resolve(commandPath, inputFile), 'utf-8')
} catch (exception) {
    console.log('Error:', exception.message)
    console.log('请检查文件是否存在/是否有足够权限')
}

const charNumber = Lib.analyseChar(text)
const wordNumber = Lib.analyseWord(text)
const frequentWords = Lib.analyseMostFrequentWords(text)
const lineCount = Lib.analyseLine(text)
const resultFile = path.resolve(commandPath, outputFile)

try {
    fs.writeFileSync(resultFile, '')
    fs.appendFileSync(resultFile, `characters: ${charNumber}\n`)
    fs.appendFileSync(resultFile, `words: ${wordNumber}\n`)
    fs.appendFileSync(resultFile, `lines: ${lineCount}\n`)
    frequentWords.forEach((item) => {
        fs.appendFileSync(resultFile, `${item.word}: ${item.number}\n`)
    })
} catch (exception) {
    console.log('Error:', exception.message)
}
