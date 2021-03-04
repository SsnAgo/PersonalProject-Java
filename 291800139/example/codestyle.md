# 代码规范

## 命名规约（在遵循Java已有规则的基础上）

1. 标识符不以美元符号（$）或下划线（_）开始。

2. 不使用中文和拼音命名。

3. 包名使用全英文小写，各层级以点号（.）隔开。

   ```java
   eg. com.xxx.javaweb
   ```

4. 变量、方法、类名的命名应该见名知意。

   （1）类名：首字母大写+驼峰原则。

   ```java
   eg. Demo, WordCount
   //使用后缀名表示不同意义
       eg. UserService //用户服务类
           UserServiceImpl //实现类
           CycleInter //接口类
           UserDao //封装数据访问
           ......
   ```

   （2） 类成员变量、局部变量：首字母小写+驼峰原则。

   ```java
   eg. phoneNumber, monthSalary
   ```

   （3）方法名：首字母小写+驼峰原则。

   ```java
   eg. run(), toLowerCase()
   ```

5. 常量：全部使用大写字母，单词之间用下划线（_）隔开

   ```java
   eg. MAX_SIZE 
   ```

## 代码风格

1. 类中写新语句或嵌套方法另起一行时应该以四个空格缩进。

2. if，for，while，else等后面先加空格后加左小括号，右括号后+空格+左大括号，右大括号单独成行并与该语句首字母对齐。

3. 操作符左右皆有一个空格。参数值后不加空格，直接加逗号/分号或右小括号（视具体情况而定继续加空格）。for小括号语句内分号后加空格。

   ```java
   eg. 
   for (int i = 0; i < array1.length; i++) {
       for (int j = 0; j < array1[i].length; j++) {
           if (array1[i][j] != 0) {
               count++;
               array2[count][0] = i;
               array2[count][1] = j;
               array2[count][2] = array1[i][j];
           }
       } 
   }
   ```

4. 行宽不超过100字符。

5. 一个函数不超过100行。

6. 不同方法间取一空行。

## 注释规范

1. 注释应该少，且表达准确。
2. 方法内或变量的标注以单行//进行注释。方法注释在代码块之前，行内注释在行末。
3. 方法前标注多行注释简要写出方法的功能、参数、返回值。
```java
eg.
/**
     * 统计文件行数
     * @param data
     * @return lines
     */
```




