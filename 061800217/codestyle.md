# 代码规范


---
###缩进
缩进采用4个空格，禁止使用tab字符。
```
public static void main(String args[]) {
	//缩进4个空格
	String say = "hello";
}
```
###变量命名
使用下划线分割单词
```
File new_file = new File（……）;
```
###每行最多字符数
单行字符数限制不超过 120个，如需换行，换行缩进4个空格，并且方法前的点符号一起换行
```
sb.append("zi").append("xin")...
	.append("huang")...
	.append("huang")...
	.append("huang");
```
###函数最大行数

###函数、类命名
1、类名使用UpperCamelCase风格，遵从驼峰形式
2、函数名使用lowerCamelCase风格，遵从驼峰形式
```
public int countCharacters(String file_name){}

class WordCount{……}
```
###常量
常量命名全部大写，单词间用下划线隔开
```
MAX_STOCK_COUNT
```
###空行规则
大括号的使用约定。如果是大括号内为空，则简洁地写成{}即可，不需要换行；如果是非空代码块则：
左大括号前不换行。
左大括号后换行。
右大括号前换行。
右大括号后还有else等代码则不换行；表示终止右大括号后必须换行。
###注释规则
尽可能在同一行注释，使用"//"注释
###操作符前后空格
任何运算符左右必须加一个空格
```
a + b = c
```
