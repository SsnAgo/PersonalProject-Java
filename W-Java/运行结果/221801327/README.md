

##ReadMe
[toc]

###WordCount项目简介###
>    &emsp;该文件是本人软工实践[寒假作业(2/2)](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740)要求完成的程序，[个人博客链接](https://www.cnblogs.com/huangmingliang/p/14488689.html)中包含了对本次作业完成的解题思路，和重要代码讲解。
&emsp;作业要求如下

1. 统计文件的字符数（对应输出第一行）
    
+ 只需要统计Ascii码，汉字不需考虑
+ 空格，水平制表符，换行符，均算字符

2. 统计文件的单词总数（对应输出第二行），单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。

+ 英文字母： A-Z，a-z
+ 字母数字符号：A-Z， a-z，0-9
+ 分割符：空格，非字母数字符号
+ 例：file123是一个单词， 123file不是一个单词。file，File和FILE是同一个单词

3. 统计文件的有效行数（对应输出第三行）：任何包含非空白字符的行，都需要统计。
4. 统计文件中各单词的出现次数（对应输出接下来10行），最终只输出频率最高的10个。

+ 频率相同的单词，优先输出字典序靠前的单词。
(例如，windows95，windows98和windows2000同时出现时，则先输出windows2000)
+ 输出的单词统一为小写格式
###函数设计接口###
 1. &emsp;一个读取文件路径的接口和一个将上述计算结果输出文件的接口outputFile(File inputFile, File outputFile)
功能：用于输入操作文件和输出文件的路径，执行以下方法操作，并负责将函数返回的结果输出到输出文件。
 
 2. &emsp;一个实现统计字符数的接口。countCharacters(File file)
 功能：用于统计输入文件的字符数。
 3. &emsp; 一个统计单词总数的接口。countTotalWords(File inputFile)
 功能：用于统计输入文件的单词总数。
 4. &emsp; 一个统计有效行数的接口。countValidLines(File inputFile)
 功能：用于统计输入文件的有效行数。
 5. &emsp;一个统计单词出现次数的接口。List<Map.Entry<String, Integer>> countWords
 功能：用于统计单词词频。

###WordCount项目简介###
####文件运行方式####
> &emsp;用户在dos窗口下，通过java指令运行该文件，将被检测文件和结果输出的文件输入到程序中即可。
 &emsp;程序运行路径：countCharacters->countTotalWords->countValidLines->countWords
 
***
感谢您的阅读！欢迎联系作者：3278787757@qq.com


