## 项目介绍
### 1.项目简介
该项目为cmd命令行程序，该程序接受的第一个参数为源文件地址，第二个参数为目标文件地址。该程序将对源文件进行读取，并统计文件内字符数、单词数（以四个字母开头）、有效行数（含有非空白字符的行）、单词出现次数。并将以上字符数、单词书、有效行数的统计结果和出现次数最多的十个单词及其次数在目标文件内按以下格式进行输出。
characters: number
words: number
lines: number
word1: number
word2: number
...
其中word1和word2 对应具体的单词，number为统计出的个数；换行仅使用'\n'，编码统一使用UTF-8。
### 2.如何运行
使用cmd进入到项目所在文件夹，进入src文件夹内，对WordCount类和Lib类进行编译后，输入指令java WordCount 源文件文件名 目标文件文件名 即可运行。
### 3.作业链接
[寒假作业2/2](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740)
### 4.博客链接
[作业博客](https://www.cnblogs.com/ShikiRe/p/14482671.html)