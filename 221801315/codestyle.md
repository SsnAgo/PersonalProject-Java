## 缩进
缩进采用4个空格，禁止使用tab字符，若要使用Tab则必须设置为1Tab = 4空格
> 正例：<pre>    System.out.println();   //4个空格</pre>
> 
> 反例：<pre>  System.out.println();  //Tab</pre>

```if```/```for```/```while```/```switch```/```do```等保留字与左右括号之间都必须加空格
> 正例：```if (isTrue)```
> 
> 反例：```if(isTrue)```

方法参数在定义和传入时，多个参数逗号后边必须加空格
> 正例：```method(args1, args2, args3);```
>
> 反例：```method(args1,args2,args3);```

大括号的使用约定。如果是大括号内为空，则简洁地写成{}即可，不需要换行；如果是非空代码块则：
- 左大括号前加空格且不换行
- 左大括号后换行
- 右大括号前换行
- 右大括号后还有else等代码则不换行；表示终止右大括号后必须换行
> 正例：
>```
> if (count == 1) {
>     System.out.println("Yes");
> } else {
>     System.out.println("No");
> }
> ```
>
> 反例：
> ```
> if (count == 1)
> {
>     System.out.println("Yes");
> }
> else
> {
>     System.out.println("No");
> }
> ```

## 变量命名
代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束
> 反例：```_name```/```$name```/```name_```/```name$```

不允许使用拼音和直接使用中文
> 反例：```taobao```/```tencent腾讯```

统一使用**lowerCamelCase**风格，必须遵从驼峰形式
> 正例：```bool isPhone;```
>
> 反例：```bool IsPhone;```/```bool isphone;```


## 每行最多字符数
每行不超过120个字符，超出需要换行，换行时遵循如下原则：
- 第二行相对第一行缩进 4个空格，从第三行开始，不再继续缩进，参考示例
- 运算符与下文一起换行
- 方法调用的点符号与下文一起换行
- 在多个参数超长，逗号后进行换行
- 在括号前不要换行，见反例
> 正例：
> ```
> StringBuffer str = new StringBuffer();
> str.append("1").append("2")...
>     .append("999")...    //缩进4个空格，点符号与下文一起换行
>     .append("5555")...   //相对上一行不缩进
>     .append("10000");
> method(args1, args2, args3, ...,   //逗号后换行
>     argsX);</pre>
> ```
>
> 反例：
> ```
> str.append("1").append("2")...append
>     ("999")；
> ```

## 函数最大行数
除空行、右大括号、注释以外，一个函数的**有效行数**最大不超过40行，除实在无法拆分的情况（如分支判断比较多，且互相依赖）外，都要考虑拆分。

## 函数、类命名
### 函数名
统一使用**lowerCamelCase**风格，必须遵从驼峰形式，但以下情形例外：ID等
> 正例：```void printAddress() {}```
>
> 反例：```void PrintAddress() {}```/```void printaddress() {}```
### 类名
使用**UpperCamelCase**风格，必须遵从驼峰形式，但以下情形例外：（领域模型的相关命名）DO / BO / DTO / VO等
> 正例：```class WordCount {}```
>
> 反例：```class wordCount {}```/```class wordcount {}```

## 常量
常量命名全部大写，单词间用下划线隔开
> 正例：```MAX_COUNT```
>
> 反例：```Max_Count```/```max_count```

## 空行规则
两个函数之间要有一空行，代码体中按逻辑块或功能块插入空行，不使用无效空行
> 正例：
> ```
> void printID() { 
>     System.out.println("ID");
> }
> 
> public static void main(String[] args) {
>     //输入
>     Scanner scanner = new Scanner(System.in);
>     int i = scanner.nextInt();<br/>
> 
>     //输出
>     System.out.println(i);
> }
> ```

## 注释规则
### 类
- 在开头使用<strong>/** *内容 */</strong>格式，不得使用//xxx方式
- 标注功能  
- 标注作者信息（名字、邮箱、创建时间、最后修改时间）
> 正例：
> ```
> /** 
> * 功能：XXXXX
> * 作者：XXX 
> * 邮箱：xxxxx@xxx.com
> * 创建时间：2021/2/28 15:20
> * 最后修改时间：2021/2/28 18:00
> */
> class WordCount {}
> ```
### 函数
- 在函数名上一行用<strong>/* 内容 */</strong>注释，不得使用//xxx方式
- 标注功能
- 有传入参数和返回值时，标注参数类型、意义
> 正例：
> ```
> /* 计算两个整数和
>    传入参数：整数a，整数b
>    返回值：整数a+b的结果c */
> public int add(int a, int b) {
>     int c = a + b;
>     return c;
> }
> ```
### 变量
使用<strong>//xxx</strong>方式注明变量的含义
> 正例：```int name;  //名字```

## 操作符前后空格
任何运算符左右必须加一个空格
> 正例：```count = 1```
>
> 反例：```count=1```

## 其他规则
- 抽象类的类名最后加上**Abstract**
- 枚举类的类名最后加上**Enum**
- 接口名最后加上**Interface**
> 正例：
> ```
> abstract class BaseAbstract {}
> enum colorEnum {}
> interface purchaseInterface {}
> ```