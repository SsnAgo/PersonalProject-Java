const analyseChar = require('./analyseChar')
const analyseWord = require('./analyseWord')
const analyseMostFrequentWords = require('./analyseMostFrequentWords')
const analyseLine = require('./analyseLine')
const assert = require('assert')

describe('test analyseChar', () => {
    it('hello\\r\\nworld! should be 13', () => {
        assert.strictEqual(analyseChar('hello\r\nworld!'), 13)
    })

    it('hello\\nworld! should be 12', () => {
        assert.strictEqual(analyseChar('hello\nworld!'), 12)
    })
})

describe('test analyseWord',  () => {
    it('abcd should be one', () => {
        assert.strictEqual(analyseWord('abcd'), 1)
    })

    it('abc1d should be zero', () => {
        assert.strictEqual(analyseWord('abc1d'), 0)
    })

    it('12abcd should be zero', () => {
        assert.strictEqual(analyseWord('12abcd'), 0)
    })

    it('3 should be zero', () => {
        assert.strictEqual(analyseWord('3'), 0)
    })

    it('none should be zero', () => {
        assert.strictEqual(analyseWord(''), 0)
    })

    it('abcdefg123abcdefg should be one', () => {
        assert.strictEqual(analyseWord('abcdefg123abcdefg'), 1)
    })
})

describe('test analyseLine', () => {
    it('\\n    \\naisodjo \\t  \\n ???---\\n\\ni\\nrr should be 4', () => {
        assert.strictEqual(analyseLine('\n    \naisodjo \t  \n ???---\n\ni\nrr should be 4'), 4)
    })
})