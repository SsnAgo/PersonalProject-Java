# 实现一个命令行程序WordCount
## 运行方法
``` Java
//Java语言
java WordCount input.txt output.txt
```

## 功能简介
### 1.统计文件的字符数（对应输出第一行）：
- 只需要统计Ascii码，汉字不需考虑。
- 空格，水平制表符，换行符，均算字符。

### 2.统计文件的单词总数（对应输出第二行），单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
- 英文字母： A-Z，a-z
- 字母数字符号：A-Z， a-z，0-9
- 分割符：空格，非字母数字符号。
- 例：file123是一个单词， 123file不是一个单词。file，File和FILE是同一个单词。

### 3.统计文件的有效行数（对应输出第三行）：任何包含非空白字符的行，都需要统计。

### 4.统计文件中各单词的出现次数（对应输出接下来10行），最终只输出频率最高的10个。
- 频率相同的单词，优先输出字典序靠前的单词。
> 例如，windows95，windows98和windows2000同时出现时，则先输出windows2000
- 输出的单词统一为小写格式。

### 5.将统计结果输出到output.txt，输出的格式如下；其中`word1`和`word2`对应具体的单词，`number`为统计出的个数；换行使用'\n'，编码统一使用UTF-8。
``` Java
characters: number
words: number
lines: number
word1: number
word2: number
...
```

## 作业链接
[软工实践寒假作业（2/2）](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740)

## 博客链接
[软工实践寒假作业（2/2）](https://www.cnblogs.com/FZU-TKQ/p/14453148.html)
