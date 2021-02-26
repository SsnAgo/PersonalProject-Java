'use strict'

/**
 * read file
 * @param {string} fileUrl
 */
function readFile(fileUrl) {
    let fs = require('fs');
    try {
        let data = fs.readFileSync(fileUrl, 'utf-8');
        return data;
    }
    catch (err) {
        console.log("file system can't read file, error msg follow:");
        console.log(err);
        return null;
    }
}

/**
 * write file
 * @param {string} fileUrl
 * @param {string} output
 */
function writeFile(fileUrl, output) {
    let fs = require('fs');
    try {
        fs.writeFileSync(fileUrl, output);
        return true;
    }
    catch (err) {
        console.log("file system can't write file, error msg follow:");
        console.log(err);
        return false;
    }
}

/**
 * count charactor quantity
 * @param {string} data 
 */
function countChar(data) {
    let charArrat = data.split("");
    // console.log(charArrat);
    return charArrat.length;
}

/**
 * count total word
 * @param {string} data 
 */
function countWord(data) {
    let words = data.split(/[^A-Za-z0-9]/);
    // console.log(words);
    for(let i=0; i<words.length; i++) {
        // console.log(words[i]);
        if(words[i].length < 4) {
            words.splice(i, 1);
            i--;
        }
        const reg = new RegExp("^[a-zA-Z]{4}");
        if(!reg.test(words[i])) {
            words.splice(i, 1);
            i--;
        }
    }
    // console.log(words);
    return words.length;
}

/**
 * count valid line
 * @param {string} data 
 */
function countValidLine(data) {
    let lines = data.split("\n");
    // console.log(lines);
    for(let i=0; i<lines.length; i++) {
        if(lines[i] == "") {
            lines.splice(i, 1);
            i--;
        }
    }
    // console.log(lines);
    return lines.length;
}

/**
 * return top 10 frequent words and frequency
 * @param {string} data 
 */
function countTopOccur(data) {
    return [
        {
            wrod: "a",
            frequency: 1,
        },
    ];
}

module.exports = { readFile, writeFile, countChar, countValidLine, countWord, countTopOccur };