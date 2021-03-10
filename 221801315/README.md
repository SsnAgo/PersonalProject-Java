# 项目简介
> 编写一个程序，统计文件的字符数、单词总数、有效行数和频率最高的10个单词及其频率。
> 
> 文件路径参数以命令行形式传入
> 
#如何运行
## 命令行输入
> 1. javac -encoding utf-8 WordCount.java Lib.java
> 2. java WordCount 你的输入文件路径 你的输出文件路径
> 
## 自己编写程序调用
以下是模块接口，可以直接调用。
```java
/* 统计输入文件中的字符总数
输入参数：输入文件路径inFilePath
返回值：字符总数chars */
public static int countTotalChar(String inFilePath);

/* 统计输入文件中的单词总数
输入参数：输入文件路径inFilePath
返回值：单词总数words */
public static int countTotalWord(String inFilePath);

/* 统计输入文件中的有效行数
输入参数：输入文件路径inFilePath
返回值：行数lines */
public static int countValidLine(String inFilePath);

/* 先按频率后按字典序给单词记录表排序
输入参数：输入文件路径inFilePath
返回值：记录最高的10个频率的单词的列表list */
public static List<Map.Entry<String, Integer>> getSortWordFrequencyRecords(String inFilePath);

/* 将统计结果写入输出文件
输入参数：输入文件路径inFilePath，输出文件路径outFilePath
返回值：空 */
public static void writeToOutFile(String inFilePath, String outFilePath);
```
# [作业链接](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740)
# [博客链接](https://www.cnblogs.com/Aurora315/p/14480894.html#_label3_1_4_1)