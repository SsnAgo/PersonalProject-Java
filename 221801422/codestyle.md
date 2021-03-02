# 编码规范

[toc]

## 缩进

缩进为4个空格

``` java
public class Lib
{
    Lib lib = new Lib();
}
```

## 变量命名

变量用单个单词或者单词拼接(lowerCamelCase)的方式来命名

```java
    int count = 0;
    int wordNum = 0;
```

## 每行最多字符数

每行最多字符数为80

## 函数最大行数

函数控制在80行以内

## 函数、类命名

函数命名用lowerCamelCase方式, 类命名用UpperCamelCase方式

```java
public class WordCount
{
    public static void 
} 
```

## 常量

全部大写, 如有必要用下划线连接.

```java
    int MAX_NUM = 0;
```

## 空行规则

* 函数体之间加入一个空行
* 方法中不同的逻辑相关中用加入一个空行

## 注释规则

单行注释用//, 块注释的话要放在类和函数体之前.

## 操作符前后空格

操作符前后各加一个空格

```java
    int i = i + 1;
```

参数的逗号前面不加空格, 后面加空格.

```java
int CountNum(int a, int b)
```

## 其他规则

* 大括号都换行
* switch语句一定要有default
* if-else如果else后面只有一行代码, 直接跟在')'后面, 如果不止一行代码要用大括号, 代码放在大括号中