const { CHINESE_REGEX } = require("./regex");

const filterChinese = (content) => content.replace(new RegExp(CHINESE_REGEX, "gm"), "");

module.exports = filterChinese;
