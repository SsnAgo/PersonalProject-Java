# 编码规范

1. 缩进

   4个空格

   ``` java
   private class InnerClass implements I1, I2 {
       public void bar() throws E1, E2 {
           
       }
   }
   ```

   

2. 变量命名

   lowerCamelCase风格

   ``` java
   int localValue = 0;
   ```

   

3. 每行最多字符数

   单行字符数限制不超过 120个，超出需要换行，换行时遵循如下原则：

   - 第二行相对第一行缩进 4个空格，从第三行开始，不再继续缩进，参考示例。
   - 运算符与下文一起换行。
   - 方法调用的点符号与下文一起换行。
   - 在多个参数超长，逗号后进行换行。
   - 在括号前不要换行，见反例。

   ```java
   正例：
   StringBuffer sb = new StringBuffer();
   //超过120个字符的情况下，换行缩进4个空格，并且方法前的点符号一起换行
   sb.append("zi").append("xin")...
   	.append("huang")...
   	.append("huang")...
   	.append("huang");
   反例：
   StringBuffer sb = new StringBuffer();
   //超过120个字符的情况下，不要在括号前换行
   sb.append("zi").append("xin")...append
   	("huang");
   //参数很多的方法调用可能超过120个字符，不要在逗号前换行
   method(args1, args2, args3, ...
   	, argsX);
   ```

   

4. 函数最大行数

   根据函数实际内容，一般不超100行

5. 函数、类命名

   lowerCamelCase风格

   ``` java
   void fooBar() {
       
   }
   ```

   

6. 常量

   全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长

   ``` java
   final int MAGIC_NUMBER = 10;
   ```

   

7. 空行规则

   - 函数之间1空行

   - 成员变量/常量间不空行
   - 注释与被注释代码段间不空行

   ``` java
   private class InnerClass implements I1, I2 {
       private int a;
       private int b;
       
       public void foo() throws E1, E2 {
           // 输出
           System.out.print("hello");
       }
       
       public void bar() throws E1, E2 {
           
       }
   }
   ```

   

8. 注释规则

   - 在需要说明的类与函数前使用 `/** */`（JavaDoc注释）
   - 在需要说明的变量或代码段前使用`//`

9. 操作符前后空格

   操作符前后均1个空格

   ``` java
   int a = b + c;
   ```

   

10. 其它规则

[alibaba-java-style-guide](https://github.com/chjw8016/alibaba-java-style-guide)