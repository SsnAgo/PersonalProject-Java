/**
 * 获取文本行数
 *
 * @param {string} text 文本
 * @return {Number} 行数
 */
function analyseLine(text) {
    let lineCount = 0;
    let lines = text.split("\n");
    lines.forEach(line => {
        let l = line.replace("\r", "");
        if (l.length > 0) {
            lineCount ++;
        }
    });
    return lineCount;
}

module.exports = analyseLine;
