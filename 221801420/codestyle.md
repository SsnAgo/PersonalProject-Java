# (一) 缩进 
1.缩进采用4个空格，在eclipse中采用tab，勾选insert spaces for tabs
# (二) 变量命名
1.代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束
```
反例：_studentNum,$studentNum_
```
2.代码中的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式
> 命名应尽量切合英文规范，不要使用中文直译
```
反例：xuesheng，zongNum
```
3.成员变量，局部变量使用lowerCamelCase风格，遵从驼峰形式
```
正例：studentNum，studentName，highestPrice
```
# (三) 每行最多字符数
1. 单行字符数限制不超过70个，超出需要换行，换行时遵循如下原则：运算符与下文一起换行，方法调用的点符号和下文一起换行，在多个参数超长逗号后进行换行，在括号钱不要换行
# (四) 函数最大行数
1. 函数行数最大不超过30行
# (五) 函数、类命名
1. 类名采用UpperCamelCase风格，遵从驼峰形式；
```
正例：StduentCore，Teacher，PersonalInformation
```
2. 函数名采用lowerCamelCase风格，遵从驼峰形式.
```
正例：int getPrice();void printfStudentInformation();
```
# (六) 常量
1. 不允许出现未经定义的常量直接在代码中使用
2. long或者Long初始赋值时，必须使用大写的L，不能是小写的l，小写容易跟数字1混淆，造成误解
3. 不要使用一个常量类维护所有常量，应该按常量功能进行归类，分开维护
4.常量命名全部大写，单词间用下划线隔开
# (七) 空行规则
1. 类中的变量定义和方法的定义之间相隔一个空行
```
正例：

int num;//

/*
*/
public int getNum(){
...
}
```
2. 方法内部中，具有一定功能的代码块之间相隔一个空行（即某个代码块的功能注释前存在一个空行）
3. 类的方法定义之间也相隔一个空行
```
正例：
/*
*/
String getName(){
...
}

/*
*/
int getAge(){
...
}
```
# (八) 注释规则
1. 类中的每个成员变量后用单行注释注释其含义，每个成员方法在定义前介绍其功能，以及参数和返回值介绍
> 类方法注释使用多行注释，变量使用单行注释
2. 在较为冗长费解的代码块前注释解释
> 代码块一般使用单行注释，若解释较长，可使用多行注释
# (九) 操作符前后空格
1. 操作符前后均有一个空格
> 左括号右边以及右括号左边与变量之间均没有空格
```
正例：Student student = new Student(name);
price = a + b;
```
