>**编程规范**
## 编程规范 ##

 - 缩进
 - 变量命名
 - 每行最多字符数
 - 函数最大行数
 - 函数、类命名
 - 常量
 - 空行规则
 - 注释规则
 - 操作符前后空格
 - 其他规则


----------
**缩进**
> 1.缩进采用4个空格，由于我是用的是Eclipse设置的是一个tab为4个空格，所以也可以采用tab键进行缩进。 2.当语句过长时，在一个标点符号后或者操作符前进行断行。 3.同级别的表达式断行后左对齐。 4.不同级别的表达式采用4个空格缩进。

**变量命名**
>   1.单个单词里尽量不出现下划线等符号，不以符号进行结尾
        反例：num_
    2.如果是普通的没有特殊含义的变量，用简单的单个字母a-z命名，如i用于for循环自增量的命名
    3.如果是有特殊含义的变量在能力允许的情况下尽量采用英文单词或者单词的缩写来命名，不要采用拼音的方式，如name表明变量带有名字的含义，而num可以用来表示带有number含义的变量。
        正例：name   num   word
        反例：xingming   xuehao
    4.一些变量可以采用单词拼接的方式，如成员变量、局部变量，并使用lowerCamelCase风格来命名。
        正例：wordCount   peopleNum
        反例：wordnum   peoplecount
    5.采用单词的缩写来命名要保证在不破坏单词原有的意思，容易让人理解的前提下进行使用，如condition将其命名为condi就无法理解原本的含义。
        正例: number->num
        反例：condition->condi
        
**每行最多字符数**
> 因为自己使用的是IDEA开发工具，而它的工作区的左侧都有相应的编辑器，所以避免一行的长度超过150个字符。

**函数、类命名**
>   1.类名使用UpperCamelCase风格，遵从驼峰形式，如WordCount来命名本次作业的一个类
        正例：WordCount
    2.函数名、方法名、参数名使用lowerCamelCase风格来命名，如getWordCount()等。
        正例：getWordCount()
    3.异常类命名使用Exception结尾，如FileNotFoundException。
        正例：FileNotFoundException   IOException
    4.数组的定义包括括号，如String[] args。
    5.枚举类加Enum后缀，并且枚举类成员均要采用大写形式。
        正例：seasonEnum   SPRING   SUMMER   AUTUMN   WINTER
    6.一些特殊的函数名可以在单词前加上特定的前缀来命名，如删除对象的方法加上delete前缀，添加对象的方法加上add前缀，获取和设置对象分别加上get、set前缀等。
        正例：deleteObject()   addObject()   getValue()   setValue()
    7.包名采用小写。
    
**常量**
>   1.常量的命名采用单词拼接的方式，并且全部大写，单词之间用下划线作为分隔符，如MAX_NUM。
        正例：MAX_NUM   MIN_NUM
        反例：MAXNUM   MINNUM
    2.不使用未经定义的常量
    
**空行规则**
> 1.函数体之间加入空行使得函数体更加清晰。 2.函数体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。

**注释规则**
> 1.避免使用过多的注释。 2.块注释放在函数体前或是类前。 3.尽量使用//来单行注释，并且注释多放在要注释的代码行前单起一行。

**操作符前后空格**
> 操作符前后都加上一个空格，使代码看起来更美观一些。在标点符号前不加空格，在标点符号后加空格。

**其他规则**
> 1.左大括号前加一个空格但是不换行，左大括号后换行。 2.右大括号前换行。 3.对于switch语句一定要有default。 4.对于if-else语句，如果else的动作只有一行代码，直接跟在右括号后面，如果包含多行代码，则另起一行并加上大括号。 5.数据结构的构造或初始化要指定大小。