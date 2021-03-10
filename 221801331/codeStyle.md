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





- 缩进

  - tab键
  - `{`另起一行

  ```java
  if((c>='A'&&c<='Z')||(c>='a'&&c<='z'))
  {
    return true;
  }
  else
  {
    return false;
  }
  ```

  

- 变量命名

  - 驼峰命名法，第一个字母小写开头

  ```java
  private int letterNum=0; 
  ```

- 每行最多字符数

  - 通常100个字符

- 函数最大行数 

  - 通常50行

- 函数命名

  - 驼峰命名法，第一个字母大写开头
  - 获取数据方法get开头
  - 设置数据方法set开头
  - 根据功能语义进行命名

  ```java
  private int recordWord(int num)
  ```

- 类命名

  - 驼峰命名法，第一个字母大写开头
  - 抽象类命名Abstract开头
  - 异常类命名使用Exception结尾

  ```java
  public class TextParser
  ```

  

- 常量

  - 全大写+下划线

  ```java
  public static final HELLO_WORLD=1;
  ```

  

- 空行规则

  - 类中每一个域、每一个方法之间空一行
  - 方法中不同语义或是功能模块空一行

  ```java
  public class TextParser
  {
    private String inputFile;   //输入文件路径
  
    private String outputFile;    //输出文件路径
  
    private String textContent;   //文本内容
  
    private int validCharsNum;    //有效字符数量
  
    private int validLinesNum;    //有效行数
  
    private int wordNum;    //单词数量
  
    private Map<String,Integer> wordCountMap;   //单词统计map
  
    private CharParser charParser;    //内置的字符解析器
  
    private WordParser wordParser;    //内置的单词解析器
  
  ```

  

- 注释规则

  - 方法用/**注释
  - 变量或语句用//在句尾注释

  ```java
  /**
   * 解析文本;解析后继续统计
   * @return
   */
  @Override
  public String parseText(String text)
  {
    char c;
    StringBuilder sb=new StringBuilder();   //保存解析结果
    for (int i=0;i<text.length();i++)
    {
      c = text.charAt(i);
      if (isCharValid(c))     //判断是否为有效字符
      {
        sb.append(c);     //若有效则加入stringbulider
      }
    }
    textContent=sb.toString();
    return textContent;   //返回解析结果
  }
  ```

  

- 操作符前后空格

  - 通通不要空格（但是编译器自动生成的语句总是会自带空格）