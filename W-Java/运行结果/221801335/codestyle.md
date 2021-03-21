# 代码规范

- 缩进
  
  每个缩进使用4个空格，对于连续行使用可选择缩进，参考如下
  
  ```Python
    //函数
    foo = long_function_name(
        var_one, var_two,
        var_three, var_four)
    print(var_one)  
    //自定义宏
    def long_function_name(
        var_one, var_two, var_three,
        var_four):
    print(var_one)
    //条件
    if (this_is_one_thing and
        that_is_another_thing):
        do_something()
    //构造函数
    my_list = [
        1, 2, 3,
        4, 5, 6,
        ]
    result = some_function_that_takes_arguments(
        'a', 'b', 'c',
        'd', 'e', 'f',
        )

- 变量命名

    避免使用“l”,“O”用作单字符变量，软件包命名使用简短全名，不使用下划线，普通变量命名使用使用单词简写，如有多单词使用下划线连接，对于特殊变量如协程后可加_co

- 每行最多字符数

    限制所有行最多79个字符。对于文本（注释或文档字符串）最多72个字符

- 函数最大行数

    函数最大行数不超过30行

- 函数,类命名
    对函数名：函数名称应小写，必要时用下划线分隔单词以提高可读性，变量名与函数名遵循相同的约定。
    函数变量名：如果函数参数的名称与保留关键字发生冲突，通常最好在其后附加一个下划线。class_优于clss。
    仅对非公共方法和实例变量使用前导下划线。
    对类命名：采用驼峰原则（单词首字母大写）
- 常量

    所有大写字母书写，并用下划线分隔单词。示例包括MAX_OVERFLOW和TOTAL。

- 空行规则

    用两个空行包围顶级函数和类定义。类内的方法定义由单个空白行包围。在函数中使用空白行，以节省空间，以指示逻辑部分。多余的空白行可以（分别）用于分隔相关功能组。一堆相关的单线之间（例如，一组虚拟实现）可以省略空白行。

- 注释规则

    文档注释每行最多72个字符

- 操作符前后空格
    在操作符之前换行,操作符前后皆空格

    ```Python
        income = (gross_wages
          + taxable_interest
          + (dividends - qualified_dividends)
          - ira_deduction
          - student_loan_interest)

- 其他  
    紧靠在括号，方括号或大括号内：

    ```Python
        spam(ham[1], {eggs: 2})
