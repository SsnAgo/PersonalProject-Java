const analyseChar = require('./analyseChar')
const analyseWord = require('./analyseWord')
const analyseMostFrequentWords = require('./analyseMostFrequentWords')
const analyseLine = require('./analyseLine')

const fs = require("fs");
const path = require("path");
const {resolve} = require("path");

const arg = process.argv;
const filenames = arg.slice(2);
const commandPath = resolve('./');
const inputFile = filenames[0];
const outputFile = filenames[1];

let text = fs.readFileSync(path.resolve(commandPath, inputFile), 'utf-8');
const charNumber = analyseChar(text);
const wordNumber = analyseWord(text);
const frequentWords = analyseMostFrequentWords(text);
const lineCount = analyseLine(text);

const resultFile = path.resolve(commandPath, outputFile);
fs.appendFileSync(resultFile, `characters: ${charNumber}\n`);
fs.appendFileSync(resultFile, `words: ${wordNumber}\n`);
fs.appendFileSync(resultFile, `lines: ${lineCount}\n`);
frequentWords.forEach((item) => {
    fs.appendFileSync(resultFile, `${item.word}: ${item.number}\n`);
});