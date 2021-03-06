# 编程规约

1. 缩进
	* 采用4个空格
	* 如果使用tab缩进，必须设置1个tab为4个空格。IDEA不勾选Use tab character；eclipse勾选insert spaces for tabs

2. 变量命名
	* 不使用拼音或中文命名
	* 不以下划线或美元符号开始或结束
	* 使用lowerCamelCase风格，遵从驼峰形式

3. 每行最多字符数
	* 单行不超过120个，超出则换行
	* 换行原则：
		* 第二行相对第一行缩进4个空格，从第三行开始，不再继续缩进
		* 运算符与下文一起换行
		* 方法调用的点符号与下文一起换行
		* 在多个参数超长，逗号后进行换行
		* 在括号前不要换行

4. 函数最大行数		
	* 不超过60行

5. 函数、类命名
	* 函数命名：同变量命名
	* 类命名：
		* 使用UpperCamelCase风格，必须遵从驼峰形式，但以下情形例外：（领域模型的相关命名）DO / BO / DTO / VO等
		* 中括号是数组类型的一部分，数组定义如下：String[] args
		* 抽象类命名使用Abstract或Base开头；异常类命名使用Exception结尾；测试类命名以它要测试的类的名称开始，以Test结尾
		* 枚举类名建议带上Enum后缀
		* 一般普通类用单数，相关工具类用复数  

6. 常量
	* 命名全部大写
	* 单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长

7. 空行规则
	* 方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行
	* 方法之间用空行分割

8. 注释规则
	* 方法内部单行注释，在被注释语句上方另起一行，使用//注释（与注释内容之间有且仅有一个空格）。方法内部多行注释使用/ */注释，注意与代码对齐
	* 所有的枚举类型字段必须要有注释，说明每个数据项的用途
	* 与其“半吊子”英文来注释，不如用中文注释把问题说清楚
	* 类、类属性、类方法的注释必须使用Javadoc规范，使用/*内容/格式，不得使用//xxx方式
	* 注释宜少而精，不宜多而滥，不能正确表达代码意义的注释，只会损害代码的可读性

9. 操作符前后空格
	* 任何二目、三目运算符的左右两边都需要加一个空格
	
10. 其他规则
	* if/for/while/switch/do等保留字与左右括号之间都必须加空格
	* 方法参数在定义和传入时，逗号后边必须加一个空格