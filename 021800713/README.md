## 项目介绍

---

### 操作说明书
* 首先在命令行下进入到该项目的根目录
* 在命令行输入
```
javac -encoding utf-8 WordCount.java
```

* 如果Lib没有自动编译，再输入
```
javac -encoding utf-8 Lib.java
```
* 最后输入
````
java WordCount input.txt output.txt
````
input为输入文件的绝对路径，output为输出文件的绝对路径
* 若运行成功，可在输出文件查看到输入文件中的字符数、单词数、有效行数、词频最高的十个单词及其词频

## 功能简介
通过在命令行输入输入输出文件的绝对路径后，可以实现：
1. 统计字符数(仅统计Ascii码)
2. 统计单词数（至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写）
3. 统计有效行数（任何包含非空白字符的行）
4. 打印词频最高前十的单词及其词频
```
characters: number
words: number
lines: number
word1: number
word2: number
...
```
## 作业链接
[WordCount](https://github.com/Lin-HYC/PersonalProject-Java)

## 博客链接
[寒假作业2/2](https://www.cnblogs.com/LinX666/p/14482674.html)
