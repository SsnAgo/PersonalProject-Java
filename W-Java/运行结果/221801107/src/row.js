const calRowsCount = (content) => content.split(/\n/).length;

const calEmptyRowsCount = (content) => content.split(/\n/).filter((row) => row.trim() === "").length;

const calNoEmptyRowsCount = (content) => calRowsCount(content) - calEmptyRowsCount(content);

module.exports = {
  calRowsCount,
  calEmptyRowsCount,
  calNoEmptyRowsCount,
};
