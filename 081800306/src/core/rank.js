/**
 * 
 * @param  arr type: Array  单词列表 
 * @param  num type: number 返回前几个
 */

function rank(arr,num){
  let obj = {
  }
  arr.sort()
  var proxy = new Proxy(obj,{
    get: function(obj,key){
      if(key in obj){
        return obj[key]
      }else{
        return 0
      }
    }
  })
  arr.forEach( item => {
    proxy[item.toLowerCase()]++;
  });
  let data = []
  for (const key in obj) {
    let o = {}
    o[key] = obj[key]
    data.push(o)
  }
  data.sort(function(a,b){
    let key1 = Object.keys(a)[0]
    let key2 = Object.keys(b)[0]
    return b[key2] - a[key1]
  })
  let dataMsg = {}
  for (let i = 0; i<num;i++){
    dataMsg = {...dataMsg,...data[i]}
  }
  return dataMsg
}
module.exports = rank