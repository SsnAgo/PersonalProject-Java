/**
 *
 * @param  arr type: Array  单词列表
 * @param  num type: number 返回前几个
 */

function rank(arr, num) {
  const obj = {
  };
  arr.sort();
  const proxy = new Proxy(obj, {
    get(obj, key) {
      if (key in obj) {
        return obj[key];
      }
      return 0;
    },
  });
  arr.forEach((item) => {
    proxy[item.toLowerCase()]++;
  });
  const data = [];
  for (const key in obj) {
    if (Object.prototype.hasOwnProperty.call(obj, key)) {
      const o = {};
      o[key] = obj[key];
      data.push(o);
    }
  }
  function compare(a, b) {
    const key1 = Object.keys(a)[0];
    const key2 = Object.keys(b)[0];
    return b[key2] - a[key1];
  }
  data.sort(compare);
  let dataMsg = {};
  for (let i = 0; i < num; i += 1) {
    dataMsg = { ...dataMsg, ...data[i] };
  }
  return dataMsg;
}
module.exports = rank;
