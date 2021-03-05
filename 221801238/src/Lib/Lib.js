const analyseChar = require('./analyseChar')
const analyseWord = require('./analyseWord')
const analyseMostFrequentWords = require('./analyseMostFrequentWords')
const analyseLine = require('./analyseLine')

module.exports =  class {
    /**
     * 统计文本的字符数
     *
     * @param {string} text 文本
     * @return {Number} 字符数
     */
    static analyseChar(text) {
        return analyseChar(text)
    }

    /**
     * 获取单词总数
     *
     * @param {string} text 文本
     * @return {Number} 单词总数
     */
    static analyseWord(text) {
        return analyseWord(text)
    }

    /**
     * 统计最多的10个单词及其词频
     *
     * @param {string} text 文本
     * @return {Array} 最多的10个单词及其词频的数组
     */
    static analyseMostFrequentWords(text) {
        return analyseMostFrequentWords(text)
    }

    /**
     * 获取文本行数
     *
     * @param {string} text 文本
     * @return {Number} 行数
     */
    static analyseLine(text) {
        return analyseLine(text)
    }
}