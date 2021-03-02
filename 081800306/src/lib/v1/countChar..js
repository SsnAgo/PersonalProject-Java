/**
 *
 * @param  arr type: Array（字符数组）
 *         line type: number 行数
 *         restCount type: number 汉字或非ascii字符（eg：俄文等）
 * 这个函数用来统计字符个数，
 */
function countCharNum(arr, restCount) {
  return arr.length - restCount;
}
module.exports = countCharNum;
