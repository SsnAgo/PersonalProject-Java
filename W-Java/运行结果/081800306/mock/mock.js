/* eslint-disable */
const Mock = require('mockjs');
const fs = require('fs');

const Random = Mock.Random;
const num  = 100000;
let data = '';
for (let i = 0; i < num; i++) {
  data = data + Random.sentence(7,12) + "\n";
}
fs.writeFileSync('mock.txt',data,{encoding: 'ascii'})