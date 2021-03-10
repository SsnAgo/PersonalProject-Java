# 项目介绍

---

## 使用说明书
1. 首先在命令行下进入到该项目的根目录
2. 在命令行输入javac -encoding utf-8 WordCount.java（编译成功则下一步）
3. 紧接着再输入javac -encoding utf-8 Lib.java（编译成功则下一步）
4. 最后输入java WordCount input.txt output.txt(前一个为输入文件的绝对路径，后一个为输出文件的绝对路径）
5. 若运行成功，可在输出文件查看到输入文件中的字符数、单词数、有效行数、词频最高的十个单词及其词频

## 运行原理
1. 通过传入的输入输出文件的路径，BufferReader读取输入文件，把字符一个个读取出来存入StringBuilder中，并转换成String。
2. 字符数直接获取String的length即可。
3. 将获得的String，经过handleWords()函数（里面包含复杂的逻辑判断、字符串拼接）分割成有效单词，将单词(key)及其出现次数(value)存入Map中。
4. 从Map中取出每个Entry的value并计算就得到单词数。
5. 将Map通过自定义比较器转换成有特定顺序的List并逐个按标准输出，就得到要求统计的最高词频的单词及其出现次数。
6. 将以上各个过程的结果通过函数返回值获取，并通过BufferWriter输出到输出文件上。

## 功能简介
通过在命令行输入输入输出文件的绝对路径后，可以实现：
1. 统计字符数
2. 统计单词数
3. 统计有效行数
4. 打印词频最高前十的单词及其词频

## 作业链接
[寒假作业2/2](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740)

## 博客链接
[软工实践寒假作业2/2](https://www.cnblogs.com/NingMengBlog/p/14466783.html)
