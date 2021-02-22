import {
  CHINESE_REGEX,
} from "../constants";

const filterChinese = (content) => content.replace(new RegExp(CHINESE_REGEX, 'gm'), "");

export default filterChinese;
