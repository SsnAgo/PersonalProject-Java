/**
 * 检查字符是否为字母
 *
 * @param {string} c 字符
 * @return {Boolean} 是否为字母
 */
function isLetter(c) {
    return /[a-zA-Z]/.test(c);
}

/**
 * 检查字符是否为数字
 *
 * @param {string} c 字符
 * @return {Boolean} 是否为数字
 */
function isNumber(c) {
    return /[0-9]/.test(c);
}

/**
 * 统计最多的10个单词及其词频
 *
 * @param {string} text 文本
 * @return {Array} 最多的10个单词及其词频的数组
 */
function analyseMostFrequentWords(text) {
    let wordLetters = 0;
    let newWord = "";
    let map = new Map();
    const addToMap = (word) => {
        word = word.toLowerCase();
        if (!map.has(word)) {
            map.set(word, 1);
        } else {
            const count = map.get(word);
            map.set(word, count + 1);
        }
    }
    for (let i = 0; i < text.length; i++) {
        const c = text[i];
        switch (true) {
            case isLetter(c):
                wordLetters++;
                newWord += c;
                if (i === text.length - 1 && wordLetters >= 4) {
                    addToMap(newWord);
                }
                break;
            case isNumber(c):
                if (wordLetters < 4) {
                    continue;
                } else {
                    newWord += c;
                    if (i === text.length - 1 && wordLetters >= 4) {
                        addToMap(newWord);
                    }
                }
                break;
            default:
                if (wordLetters >= 4) {
                    addToMap(newWord);
                }
                wordLetters = 0;
                newWord = "";
        }
    }

    const words = [];
    for (let word of map.keys()) {
        words.push({
            word,
            number: map.get(word)
        })
    };
    words.sort((a, b) => {
        if (a.number === b.number) {
            return a.word > b.word ? 1 : -1;
        }
        return a.number < b.number ? 1 : -1;
    });
    return words.slice(0, 10);
}

module.exports = analyseMostFrequentWords;