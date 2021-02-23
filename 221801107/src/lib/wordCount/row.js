const { EMPTY_ROW_REGEX } = require("./constants");

const calRowsCount = (content) => content.split(/\n/).length;

const calEmptyRowsCount = (content) => content.split(EMPTY_ROW_REGEX).length;

const calNoEmptyRowsCount = (content) => calRowsCount(content) - calEmptyRowsCount(content);

module.exports = {
  calRowsCount,
  calEmptyRowsCount,
  calNoEmptyRowsCount,
};
