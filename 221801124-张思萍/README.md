- 缩进

  使用IDEA的自然缩进，为4个空格

  ```java
      public static void writeToFile(String filePath, String str) throws IOException {
          File file = new File(filePath);
          BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
          bufferedWriter.write(str);
          bufferedWriter.close();
      }
  ```

- 变量命名

  统一采用小驼峰式命名规则，即除第一个单词之外，其他单词首字母大写

  ```java
  firstName
  secondName
  ```

- 每行最多字符数

  - 每一行代码字符数不超过 `120`
  - 注释行可以超过 `120` 个字符，但最大不超过 `150`

- 函数最大行数

  最大行数为50，行数太多会增加复杂程度和增加阅读难度和增加维护难度。

- 函数、类命名

  函数名命令采用小驼峰法，类命名使用大驼峰法，并且要做到见名知意

  ```java
  //统计文件中各单词的出现次数并排序
  public static List<Map.Entry<String, Integer>> sortWords(ArrayList<String> lines) throws IOException {...}
  
  class WordCount{...}
  ```

- 常量

  常量命名全部大写，单词间用下划线隔开

- 空行规则

  - 定义变量后要空行。

  - 每个函数定义结束之后都要加空行。

- 注释规则

  - 在方法体前使用单行注解//...注明方法体的功能

  - 代码较长时，特别是有多重嵌套的时候，在段落的结束处加注释，便于阅读。

  - 对于难以达意的变量在其后（同一行） 进行注解解释

  ```java
  	//输出使用频率前10的单词
      public static String printTop10(List<Map.Entry<String, Integer>> maplist) {
          int cnt = 0;
          String outToFile = "";
  
          for (int i = 0; i < maplist.size(); i++) {
              Map.Entry<String, Integer> e = maplist.get(i);
              outToFile += e.getKey() + ": " + e.getValue() + "\n";
              if (cnt++ >= 9) break;
          }
          return outToFile;
      }
  ```

  

- 操作符前后空格

  任何运算符左右必须加一个空格

  ```java
  if (cnt++ >= 9) break;
  ```

- 其他规则

  - if/for/while/switch/do 等保留字与括号之间都必须加空格。

  - 一个函数做一件事，函数要保持精简、精练、简练、简洁

  - 避免在子父类的成员变量之间、或者不同代码块的局部变量之间采用完全相同的命名，使可读性降低

  - 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式