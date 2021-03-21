## 编码规范

#### 缩进

- 以一次tab为标准（即4个空格）

  ```java
  for (int i = 0; i < 10; i++) {
      System.out.println("Hello World!")
  }
  ```

#### 变量命名

- 不以下划线开始
- 不使用拼音
- 使用lowerCamelCase

#### 每行最多字符数

- 之前没有具体要求，视自己的观看难度而定
- 在尽量不破坏语义或者适合观看的角度上，进行适当换行

- 考虑采用阿里巴巴的代码规范手册

#### 函数最大行数

- 一般来说不超过100行
- 视具体功能而定

#### 函数、类命名

- 函数名采用lowerCamelCase
- 类名采用UpperCamelCase

#### 常量

- 采用UpperCamelCase

#### 空行规则

- 成员变量之间不换行
- 函数定义之间空一行
- 变量与常量之间空一行

#### 注释规则

- 在需要说明的类与函数之前采用/** */

  ```java
  /**
  *说明
  */
  public void function()
  ```

- 在需要说明的变量或代码前使用//

#### 操作符

- 操作符前后有空格

  ```java
  int a = b + c;
  ```

#### 其他规则

- 以阿里巴巴Java手册为参照

>[alibaba-java-style-guide](https://github.com/chjw8016/alibaba-java-style-guide)
