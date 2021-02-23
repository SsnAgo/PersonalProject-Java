const { CHINESE_REGEX } = require("../constants");

const filterChinese = (content) => content.replace(new RegExp(CHINESE_REGEX, 'gm'), "");

module.exports = filterChinese;
