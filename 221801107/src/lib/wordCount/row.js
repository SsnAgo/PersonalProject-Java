import { EMPTY_ROW_REGEX } from "./constants";

const calRowsCount = (content) => content.split(/\n/).length;

const calEmptyRowsCount = (content) => content.split(EMPTY_ROW_REGEX).length;

const calNoEmptyRowsCount = (content) => calRowsCount(content) - calEmptyRowsCount(content);

export {
  calRowsCount,
  calEmptyRowsCount,
  calNoEmptyRowsCount,
};
