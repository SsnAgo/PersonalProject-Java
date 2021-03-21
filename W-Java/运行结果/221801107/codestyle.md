## 语法规范

1. 缩进

代码均采用 `tab` 进行缩进，长度为两个空格。

```js
if (true) {
  const arr = [];
  // do something...
}
```

2. 变量命名

采用小驼峰命名法进行命名，不采用中文命名。

```js
// good
const softwareProject = "软件工程";

// bad
const 软件工程 = "软件工程";
```

3. 每行最多字符数

不超过 `80` 个字符。以下为实例代码 `...` 代表很多字符。

```js
// good
const longStr = `long... 
			...long`

// bad
const badStr = `long... ...long`
```

4. 函数，类命名

函数均采用小驼峰命名法，类或构造函数采用大驼峰命名法。

```js
// 类
class Student {
  // ...
}

// 构造函数
function Student() {
    return this;
}

// 普通函数
function getName() {
    // do something
}
```

这里构造函数用大驼峰命名法区别于普通函数是为了防止忘记 `new` 关键字，`JS` 代码并不会报错，而是给 `window` 增加属性。这是 `JS` 的 "糟粕"，并没有方式区分构造函数或是普通函数，故应该从命名上对函数进行区分。

```js
const student = new Student();
```

5. 常量

特定文件的常量采用全大写加下划线分割的格式。

```js
// constants/xxx.js or configs/xxx.js
export const MAX_COUNT = 10;
```

`JS` 的 `const` 并不是实际意义的常量，只是指向的指针不变，因此 `const` 没有办法绝对的做到无法修改。

在普通文件中定义常量的时候可以不必遵循上述规范，这是为了与解构语法相匹配，以及进行代码简写。

```js
// index.js
const arr = [1];
arr.push(2);

const obj = { value: 1 };
const { value } = obj;

const obj2 = {
  value
};
```

6. 空行规则

函数与函数之前一般会用空行隔开，没有明确的要求，只需要看上去舒适即可。

```js
function fun1() {
  // ...
}

function fun2() {
  // ...
}
```

7. 注释规则

正常情况下采用 `//` 进行注释。在撰写三行以上文本考虑使用 `/**/` 进行注释。对于特殊文本一定采用 `/**/` 进行注释。

`// ` 后面会加一个空格再写文字

```js
// todo

/**
 * @param {number} a
 * @param {number} b
 */
const add = (a, b) => {
    return a + b;
}
```

对函数进行参数类型的设置，可以弥补 `JS` 因为弱类型语言带来的潜在 `BUG`

```js
// index.js
/**
 * @author huro
 */
```

在文档头部，可能会采用如上注释进行作者名称或文件的说明。

8. 操作符前后空格

所有操作符左右均留空格。

```js
const c = a + b;
const c = a * b;
const c = a ** b;
```

9. 其他规则

   1. 字符串一律使用双引号，单引号只用在双引号中

      ```js
      const str = "hello world";
      const say = "I'm huro";
      ```

   2. 对于模块导出，除了 `utils` 里的工具函数，不采用默认导出模式

      ```js
      // utils/filterChinese.js
      
      import {
        CHINESE_REGEX,
      } from "../constants";
      
      const filterChinese = (content) => content.replaceAll(CHINESE_REGEX, "");
      
      export default filterChinese;
      
      // row.js
      import { EMPTY_ROW_REGEX } from "./constants";
      
      const calRowsCount = (content) => content.split(/\n/).length;
      
      export {
        calRowsCount,
      };
      
      ```

   3. 对于行结尾使用 `LF` 或是 `CRLF` 没有要求。
   4. 该项目为命令行程序,在写 `console` 的时候不给予警告。
   5. 允许使用 `require` 全局导入模块
      ```js
      // ok
      require("xxx.js")
      ```
   6. 函数的值参数允许进行修改，但是引用参数不行
      ```js
      // ok
      const fun1 = (index) => {
        index += 1;
      } 

      // not
      const fun2 = (object) => {
        object = otherObject;
      }
   7. class的方法不一定要使用 `this` 关键字

   

除此之外，采用 [https://github.com/airbnb/javascript](https://github.com/airbnb/javascript) 提供的 `JS` 语法规范进行约束，并采用 `eslint` 工具配合 `vscode` 检查语法。确保语法的正确实施。