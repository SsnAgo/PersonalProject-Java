# 第一次commit
> 1. 创建了Lib工具类
> 2. 初步编写了读取文件方法与统计字符数量方法

# 第二次commit
> 1. 初步为Lib编写了统计有效行数的方法

# 第三次commit
> 1. 初步为Lib类编写了统计有效单词数方法，编写了创建wordMap<>集合的方法。（后续将使用wordMap<>集合实现单词频率排序）

# 第四次commit
> 1. 为Lib类编写了统计对单词频率进行排序的方法sortMap()

# 第五次commit
> 1. 修改了匹配单词的正则表达式，使其更符合题意
> 2. 修改了Lib类中创建wordMap<>集合方法，使其能正确匹配单词并统计频率
> 3. 为WordCount类添加了Mian函数，初步进行数据输出测试

# 第六次commit
> 1. 为Lib类添加了outMessage()方法，对将要输出的信息进行整合
> 2. 为Lib类添加了文件写入方法writeFile()
> 3. 修改了WordCount类中Main()函数部分代码，以便于测试输出

# 第七次commit
> 1. 修改Lib类，将正则表达式设为Lib静态成员变量
> 2. 修改Lib类，添加静态Map变量
> 3. 修改WordCount类，添加了成员变量inPut、outPut以及readStr
> 4. 为WordCount添加了构造函数以及初始化成员变量方法

# 第八次commit
> 1. 添加LibTest类，以便于进行单元测试

# 第九次commit
> 1. 删除了Lib类中对静态成员Map进行初始化的方法，Map在getWordNum()方法时就进行初始化，提升了性能。
> 2. 为LibTest类的所有方法添加显示运行时间的代码，方便测试
> 3. 修改WordCount类中Main函数代码，执行测试

# 第十次commit
> 1. 添加了部分注释
> 2. 修改了WordCount类的Main()函数，实现命令行参数传入功能。
