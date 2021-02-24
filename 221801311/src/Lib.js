'use strict'

/**
 * read file
 * @param {string} fileUrl
 */
function readFile(fileUrl) {
    
}

/**
 * write file
 * @param {string} fileUrl
 * @param {string} output
 */
function writeFile(fileUrl, output) {

}

/**
 * count charactor quantity
 * @param {string} data 
 */
function charCount(data) {

}

/**
 * count word quantity
 * @param {string} data 
 */
function wordCount(data) {
    return 2;
}

/**
 * return top 10 frequent words and frequency
 * @param {string} data 
 */
function topOccur(data) {
    return [
        {
            wrod: "a",
            frequency: 1,
        },
    ];
}

module.exports = { readFile, writeFile, charCount, wordCount, topOccur };