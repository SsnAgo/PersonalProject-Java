import {
  CHINESE_REGEX,
} from "../constants";

const filterChinese = (content) => content.replaceAll(CHINESE_REGEX, "");

export default filterChinese;
