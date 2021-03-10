# 语法规范
项目代码的规范采用的是eslint作为代码检测工具
- 缩进
tab统一采用两个space缩进。
``` 
// good
function test() {
∙∙let name;
}
```
- 变量命名
采用驼峰法命名。
```
let firstName = 'whh';
```
- 每行最多字符数
每行最多不能超过100字符
- 函数、类命名
禁止单字符直接命名
```
// bad
function a() {
  // ...
}
```
函数采用小驼峰命名
```
// bad
const this_object = {};
// good
const thisObject = {};
function thisIsFunction() {}
```
类采用大驼峰命名
```
class User {
  constructor() {
    ...
  }
}
```
- 空行规则
模块的引入要和逻辑代码间空一行
```
const xxx = require('xxx');

let name = xxx;
```
类当中的方法要空一行
- 注释规则
单行的注释采用，```//```
跨行的注释采用,```\* *\```
- 操作符前后空格
操作符前后要有一个空格
```
let a = b + c;
```
- 其他规则
字符串一律采用单引号
```
//bad
let name = "xxx";
//good
let name = 'xxx';
```
函数调用，if语句等的{}与() 要有空格。
```
if (xx) {

}
```
其他规范 https://github.com/airbnb/javascript#blocks