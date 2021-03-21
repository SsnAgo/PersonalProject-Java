# 编程规范
[toc]
## 缩进
采用四个空格缩进，由于我的IDEA上tabe也是四个空格，所以用tabe缩进。
## 变量命名
采用变量的英文名命名，例如：name,不恰当命名：xingming。

长变量命名采用lowerCamelCase风格来命名，例如：wordCount。

一些没有特殊含义的一般变量采用简单字母命名。

若是单词过长可以采用能看懂的缩写形式，例如：student->stu;
## 每行最多字符数
不超过100字符
## 函数最大行数
不超过90行
## 函数、类的命名
类的命名采用UpperCamelCase风格，如：WordCount；

函数的命名用lowerCamelCase风格，如：readFile；

异常类的命名要以Exception结尾；

命名要符合实际，能够让人看到名字就知道是做什么的，例如：getLine。
## 常量
常量的命名采用大写，并且用下划线分开，由两个单词拼接成，例如：MIN_SIZE.
## 空行
函数与函数之间要空行，同一个函数内不同逻辑的语句要空行。例如变量的定义和判断语句的执行。
## 注释规则
注释尽量简洁明了，注释放在执行语句之前，且注释单开一行，尽量用单行注释。
## 操作符前后空格
操作符前后都加一个空格，标点符号前后都不加空格。
例如a + b = c;
## 其他规则
采用如下代码风格
public class HelloWord

{

    public static void main(String[] args)
    {
        System.out.println("hello word");
    }
}