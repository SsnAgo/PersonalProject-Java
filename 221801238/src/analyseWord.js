/**
 * 检查字符是否为字母
 *
 * @param {string} c 字符
 * @return {Boolean} 是否为字母
 */
function isLetter(c) {
    return /[a-zA-Z]/.test(c)
}

/**
 * 检查字符是否为数字
 *
 * @param {string} c 字符
 * @return {Boolean} 是否为数字
 */
function isNumber(c) {
    return /[0-9]/.test(c)
}

/**
 * 获取单词总数
 *
 * @param {string} text 文本
 * @return {Number} 单词总数
 */
function analyseWord(text) {
    let words = []
    let wordLetters = 0
    let newWord = ""
    for (let i = 0; i < text.length; i++) {
        const c = text[i]
        switch (true) {
            case isLetter(c):
                wordLetters++
                newWord += c
                if (i === text.length - 1 && wordLetters >= 4) {
                    words.push(newWord)
                }
                break;
            case isNumber(c):
                if (wordLetters < 4) {
                    continue
                } else {
                    newWord += c;
                    if (i === text.length - 1 && wordLetters >= 4) {
                        words.push(newWord)
                    }
                }
                break
            default:
                if (wordLetters >= 4) {
                    words.push(newWord)
                }
                wordLetters = 0
                newWord = ""
        }
    }
    return words.length
}

module.exports = analyseWord