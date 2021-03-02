# PersonalProject-Java
## 项目描述
本项目用于英文文本的词频统计，可以统计出总字符数、总单词数，以及频率top10的单词
## 运行方式
`javac -encoding UTF-8 WordCount.java`

`java WordCount input.txt output.txt`

JVM内存支持的话可以运行100,000,000行以上的数据
## 作业链接
[寒假作业(2/2)](https://www.cnblogs.com/FZU-SE-LYK/p/14464990.html)
## 博客链接
[个人博客](https://www.cnblogs.com/FZU-SE-LYK/)
## commit记录
### 第一次commit
- 文件读取、转化为字符串
- 单词统计
- 行数统计

### 第二次commit
- 修正判断单词合法性的正则表达式
- 实现中文字符、字符串判断与过滤删除
- ASCII码统计

### 第三次commit
- 实现合法单词存储进HashMap
- TODO:统计单词个数功能修改

### 第四次commit
- 将单词个数统计以及单词存储整合为一个函数，用pair返回

### 第五次commit
- makeWordPair函数声明以及注释修正
- 前十单词排序输出

### 第六次commit
- 以文件形式输出
- 基本功能已完成
- TODO：接口封装，样例测试并修复问题

### 第七次commit
- 补充以文件输出函数注释
- 修改异常部分

### 第八次commit
- 实现命令行运行
- 修改部分异常处理

### 第九次commit
- 将\r\n改为\n
- 代码换行改为LF

### 第十次commit
- 修复因编码问题导致的结果出错
- 将中文字符当作分隔符 优化结果

### 第十一次commit
- 完成多线程计算
- JVM 8G时 可以60s计算5E7行数据

### 第十二次commit
- IO使用NIO优化 大幅提高IO性能
- 添加对应函数注释
- 代码重审
- TODO：以随机化分块大小减小线程阻塞概率
- TODO: 接口完善

### 第十三次commit
- 代码重构
- 多线程重构
- 修改为命令行使用

### 第十四次commit
- 删除不必要输出信息
- 更新readme

### 第十五次commit
- 完善接口