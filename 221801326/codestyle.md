## 代码规范指定

- 缩进

  按照IDEA的自然缩进，使用四个空格，而不能使用tab缩进

  ```java
  	public static Reader openInputFile(String fileName) throws FileNotFoundException {
          File file = new File(fileName);
          Reader reader = new InputStreamReader(new FileInputStream(file));
          return reader;
      }
  ```

- 方法名、参数名、成员变量、局部变量采取驼峰命名法,第一个单词以小写字母开始,从第二个单词开始以后的每个单词的首字母都采用大写字母

  ```java
charactersCount
  ```
  
- 每行最多字符数

  每行最多为100个字符，超出需要换行，这样写出来的代码看起来舒服简洁
  
- 函数最大行数

  函数最大行数为50行，方便阅读。

- 函数名、类命名

  函数名命名使用驼峰命名法，类命名第一个字母要大写,命名要具有含义，能指示出函数或类的功能

  ```java
  	public static void printWords(Map<String, Integer> map, FileWriter writer) throws IOException {
          int i = 0;
          for (Map.Entry<String, Integer> entry : map.entrySet()) {
              writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
              if (i++ >= 9) {//打印频率前十的单词
                  break;
              }
          }
      }
  
  public class WordCount {
      public static void main(String[] args) throws IOException {
          int characters = Lib.charactersCount(args[0], args[1]);
          int words = Lib.wordsCount(args[0], args[1]);
          int lines = Lib.linesCount(args[0], args[1]);
          Map<String, Integer> wordsMap = Lib.wordNum(args[0], args[1]);
      }
  }
  ```

- 常量

  不要使用一个常量类维护所有常量，应该按常量功能进行归类，分开维护。

- 空行规则

  不同的业务逻辑之间或者不同的语义之间插入一个空行

  ```java
          writer.close();
          return result;
      }
  
      //打印出频率前十的单词
      public static void printWords(Map<String, Integer> map, FileWriter writer) throws IOException {
          int i = 0;
          for (Map.Entry<String, Integer> entry : map.entrySet()) {
  ```

- 注释规则

  方法体前用//...写出这个方法的功能，对于一些比较难理解的常量，在常量的后面标志他的含义

  ```java
  	//打印出频率前十的单词
      public static void printWords(Map<String, Integer> map, FileWriter writer) throws IOException {}
  
  	int wordNum;//单词的总数
  ```

- 操作符前后空格

  任何运算符左右必须加一个空格

  ```java
  int i = (3 + 1) & (5 / 2);
  ```

- 其他规则

  方法参数在定义和传入时，多个参数逗号后边必须加空格。

  ```java
  	public static void printWords(Map<String, Integer> map, FileWriter writer) throws IOException {
  ```

  包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。

  ```java
  com.jia.pojo
  com.jia.service
  com.jia.dao
  ```

  杜绝完全不规范的缩写

  