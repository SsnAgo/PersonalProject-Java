/**
 * 判断文本是否为空行
 *
 * @param {string} text 文本
 * @return {Boolean} 是否为空行
 */
function isBlankLine(text){
    return /^[ ]*$/.test(text)
}

/**
 * 获取文本行数
 *
 * @param {string} text 文本
 * @return {Number} 行数
 */
function analyseLine(text) {
    let lineCount = 0
    let lines = text.split('\n')
    lines.forEach(line => {
        let l = line.replace('\r', '')
        if (l.length > 0 && !isBlankLine(l)) {
            lineCount ++
        }
    });
    return lineCount
}

module.exports = analyseLine
