# README



## 项目结构

> 项目src目录包括四个java文件
>
> WordCount.java
>
> Utils.java
>
> Counters.java
>
> CounterResultHolder.java

```java
src
    | - WordCount.java 					// 主模块
		| - main() 					// 主程序
		| - writeOutputToFile() 	// 写入文件
		| - isValidFile()	 		// 判断文件是否合法
	| - Utils.java 						// 辅助工具模块
		| - getLineList() : 		// 获得字符串行
		| - compareStringByDict() 	// 字典顺序比较字符串
		| - sortMapByNum() 			// 排序映射
		| - generateOutputString()	// 生成输出信息
	| - Counters.java					// 计算模块
     	| - countCharacters()		// 计算字符
     	| - countLines()			// 计算行
     	| - countWordsAndTransform()//计算单词
	| - CounterResultHolder.java		// 数据保存容器模块
     	| - increaseCharactersCount()// 字符数加一
     	| - increaseLinesCount()	  // 行数加一
     	| - increaseWordsCount()	  // 单词数加一
     	| - putIntoMap()			  // 存入映射
```





## 如何运行



- 在IDEA中可直接通过运行main函数运行程序

> 若使用IDEA运行则请务必在运行时添加命令行参数，指定输入输出文件名。
>
> 通过运行按钮下**Edit Configurations**选项，在**Configuration**选项卡下的**Program arguments**出指定两个命令行参数，参数之间通过**空格**分隔。指定后再运行WordCount.java中的main方法



- 在命令行通过java指令运行

> 若使用命令行运行程序，首先确保命令行窗口所在目录与程序所在**目录一致。**
>
> 其次要确保四个java文件都在，没有遗漏或修改。
>
> 因为程序代码中含有中文注释，故应在编译时**指定编码为UTF-8**，否则可能编译出错。
>
> 因java文件有四个，故四个文件**都编译**后，再运行WordCount.java文件。运行时请确保提供两个命令行参数。

```java
// 命令行运行程序流程
javac -encoding UTF-8 WordCount.java
javac -encoding UTF-8 Utils.java
javac -encoding UTF-8 Counters.java
javac -encoding UTF-8 CounterResultHolder.java
    
java WordCount.java input.txt output.txt
```



## 功能简介



> 程序主要功能

扫描输入的文本文件，计算文件中字符，有效行以及有效单词的个数，同时统计词频，输出出现个数最多的是个单词，并分别输出他们出现的次数。



> 程序使用守则

为了使程序使用过程中顺利，准确，在使用过程中遵守一些规则说明如下：

- 输入文件，输出文件类型必须是文本文件，扩展名为`*.txt`
- 制表符规则：使用文本编辑器（如记事本）编辑文本时，一个tab被计为一个字符，使用IDEA编辑文本文件时，一个tab被计为四个字符。
- 有效行数规则：默认不计入空白行。以IDEA文本编辑器的显示行数为标准，即文本只有在碰到换行符时才进行换行，否则不进行换行。在文本编辑器中可以通过关闭“**自动换行**”选项查看效果。

- 当输入文件内容为空时不会写结果到输出文件，而是会返回一串字符串提示输入文件内容为空。
- 程序不处理中文字符，也未设置对中文内容的过滤或判断，使用前请确保输入文件中不包含任何除ASCII码以外的字符。

- 程序计算单词时使用的规则：前四个字符是英文字母即算单词。



## 作业链接

> 对该作业需求的更详细的描述见以下链接

https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740





## 博客链接

> 欢迎访问我的博客

https://www.cnblogs.com/Yang1428/