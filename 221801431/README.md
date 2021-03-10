语言：JAVA

1. 运行方法（命令行）：

   - 在路径（本项目，例：G:\SoftEngineeringPractice\WinterVacation\PersonalProject-Java\221801431\src）上运行命令行

   - 编译：javac -Xlint:unchecked -encoding UTF-8 WordCount.java 

   - 运行：（例）java WordCount input.txt output.txt

     （注意：input.txt输入文件与WordCount.java在同一目录下！）

   ![image-20210305203739889](C:\Users\Lenov\AppData\Roaming\Typora\typora-user-images\image-20210305203739889.png)

   运行之后：

   ![image-20210305203948539](C:\Users\Lenov\AppData\Roaming\Typora\typora-user-images\image-20210305203948539.png)

   

2. 功能简介

   - 统计文件的字符数（对应输出第一行）：

     ```
     只需要统计Ascii码，汉字不需考虑。空格，水平制表符，换行符，均算字符
     ```

   - 统计文件的单词总数（对应输出第二行）：

     ```
     单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
         英文字母： A-Z，a-z
         字母数字符号：A-Z， a-z，0-9
         分割符：空格，非字母数字符号
         例：file123是一个单词， 123file不是一个单词。file，File和FILE是同一个单词
     ```

   - 统计文件的有效行数（对应输出第三行）：

     ```
     任何包含非空白字符的行，都需要统计。
     （空白字符为：空格，'\t'，'\n'，'\r'四种）
     ```

   - 统计文件中各单词的出现次数（对应输出接下来10行），最终只输出频率最高的10个：

     ```
     频率相同的单词，优先输出字典序靠前的单词。
     	例如，windows95，windows98和windows2000同时出现时，则先输出windows2000输出的单词统一为小写格式
     ```

   - 统计结果输出到output.txt：

     ```
     输出的格式如下：
     	characters: number
        	words: number
        	lines: number
        	word1: number
        	word2: number
        	...
     其中word1和word2 对应具体的单词，number为统计出的个数；换行使用'\n'，编码统一使用UTF-8。
     ```

     

3. 作业链接：https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740

4. 博客链接：https://www.cnblogs.com/LetooJ/p/14457601.html