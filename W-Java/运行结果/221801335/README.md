# 说明
文档中包含WordCount模块,使用该模块仅需在python文件头加上import WordCount即可,如果只是需要进行调用的话只需要将src\__pycache__文件夹中的WordCount.cpython-39.pyc文件拷贝到需要调用的项目中即可，不需要复制代码.

## 如何运行
如果为了测试则clone项目，使用控制台运行项目文件`WordCount.py`，需要配置Python3.0以上环境。或者修改`WordCount.py`模块中的`main`n内容，给`intxt`和`outtxt`赋值

## 功能简介
模块名为WordCount  
模块内有多个函数  

**输入输出文件在行为空间路径下**

统计字符数  filename为输入文件名
```count_char(filename)```

统计单词数  filename为输入文件名
```count_word(filename)```

统计行数  filename为输入文件名
```count_row(filename)```

统计最多的10个单词及其词频  filename为输入文件名
```count_fword(filename)```

测试函数，对上面的接口进行测试  intxt为输入文件名 outtxt为输出文件名
```file_read_out(intxt,outtxt)```

## 作业链接
[作业要求的链接](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740)
## 博客链接
[博客链接](https://www.cnblogs.com/honghh/p/14460664.html)
