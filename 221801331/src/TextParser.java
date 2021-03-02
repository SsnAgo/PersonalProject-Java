import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文本解析
 */
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

  /**
   * 无参构造
   */
  public TextParser()
  {
    this.charParser=new CharParser();
    this.wordParser=new WordParser();
  }

  /**
   * 有参构造
   * @param outputFile
   * @param inputFile
   */
  public TextParser(String inputFile,String outputFile)
  {
    this.outputFile = outputFile;
    this.inputFile = inputFile;
    this.validCharsNum=0;
    this.validLinesNum=0;
    this.wordNum=0;
    this.wordCountMap=new HashMap<>();
    this.charParser=new CharParser();
    this.wordParser=new WordParser();
  }

  /**
   * 全参数构造
   * @param outputFile
   * @param inputFile
   * @param textContent
   * @param validCharsNum
   * @param validLinesNum
   * @param wordNum
   * @param wordCountMap
   */
  public TextParser(String inputFile, String outputFile,String textContent, int validCharsNum,
                    int validLinesNum, int wordNum, Map<String, Integer> wordCountMap)
  {
    this.outputFile = outputFile;
    this.inputFile = inputFile;
    this.textContent = textContent;
    this.validCharsNum = validCharsNum;
    this.validLinesNum = validLinesNum;
    this.wordNum = wordNum;
    this.wordCountMap = wordCountMap;
    this.charParser=new CharParser();
    this.wordParser=new WordParser();
  }

  /**
   * 将文件内容解析为文本
   * @return
   */
  public String parseFile()
  {
    return textContent=IOHandler.filePretreatment(inputFile);
  }

  /**
   * 统计有效ASCII字符
   * @return
   */
  public int countValidChars()
  {
    return charParser.countValidChars();
  }

  /**
   * 统计有效行数
   * @return
   */
  public int countValidLines()
  {
    return charParser.countValidLines();
  }

  /**
   * 统计单词数量
   * @return
   */
  public int countWordNum()
  {
    return wordParser.countWord();
  }

  public Map<String,Integer> getWordCountMapBySize(int size)
  {
    return wordParser.getWordCountMapBySize(size);
  }

  /**
   * 将结果输出至目标文件
   */
  public void writeToFile()
  {
    StringBuilder content=new StringBuilder("characters:"+validCharsNum+"\n" +
        "words:"+wordNum+"\n" +
        "lines:"+validLinesNum+"\n");
    Map<String,Integer> map=getWordCountMapBySize(10);
    for (String key: map.keySet())
    {
      content.append(key+":"+map.get(key)+"\n");
    }
    IOHandler.writeToFile(outputFile,content.toString());

    //
    System.out.println(content.toString());
    //
  }

  /**
   * 字符解析器
   */
  class CharParser
  {
    private boolean isLineHasChar=false;    //标志位,判断该行是否有非空白字符

    /**
     * 统计有效字符
     * @return
     */
    public int countValidChars()
    {
      validCharsNum=textContent.length();
      return validCharsNum;
    }

    /**
     * 统计有效行数
     * @return
     */
    public int countValidLines()
    {
      char c;
      int num=0;
      for (int i=0;i<textContent.length();i++)
      {
        c=textContent.charAt(i);
        if(c!='\t'||c!='\r'||c!='\n'||c!=' ')      //非空白字符
        {
          isLineHasChar=true;
        }
        if(c=='\n'||i==textContent.length()-1)    //遇到换行或文本内容结束
        {
          if(isLineHasChar==true)
          {
            num++;
            isLineHasChar=false;
          }
        }
      }
      validLinesNum=num;
      return validLinesNum;
    }
  }

  /**
   * 单词解析器
   */
   class WordParser
   {
    private int letterNum=0;    //连续字母数量

    private boolean isWordReading=false; //单词读取位；判断是否正在读取单词

    private boolean isThisWordValid=true;   //单词合法判断位;判断当前单词是否合法

    private StringBuilder wordReader=new StringBuilder(); //单词读取器

     /**
      * 判断是否是字母
      * @param c
      * @return
      */
    public boolean isLetter(char c)
    {
      if((c>='A'&&c<='Z')||(c>='a'&&c<='z'))
      {
        return true;
      }
      else
      {
        return false;
      }
    }

     /**
      * 判断是否是有效字符
      * @param c
      * @return
      */
    public boolean isValidChar(char c)
    {
      if((c>='A'&&c<='Z')||(c>='a'&&c<='z')||(c>='0'&&c<='9'))
      {
        return true;
      }
      else
      {
        return false;
      }
    }


    /**
    * 统计单词
    * @return
    */
    public int countWord()
    {
      char c;
      int num=0;
      for (int i=0;i<textContent.length();i++)
      {
        c=textContent.charAt(i);
        if(isWordReading)   //正在读取单词
        {
          if(isValidChar(c)) //若是有效单词
          {
            wordReader.append(c);   //加入单词读取器
          }
          else  //否则
          {
            isWordReading=false;    //单词读取结束，读取位改为false
            isThisWordValid=true;   //单词读取结束,重置合法位
            num++;   //单词数量+1
            Integer count;
            String word=wordReader.toString().toLowerCase();

            letterNum=0;  //清空连续字母数量
            wordReader.delete(0,wordReader.length());   //清空单词读取器
            wordCountMap.put(word,(count=wordCountMap.get(word))==null?1:count+1);  //更新map
          }
        }
        else   //若否
        {
          if(!isValidChar(c))   //若为分隔符
          {
            isThisWordValid=true;   //重置合法位；开始读取下一个单词
            letterNum=0;  //清空连续字母数量
            wordReader.delete(0,wordReader.length());   //清空单词读取器
            continue;
          }
          if(!isThisWordValid)    //若此单词已不合法
          {
            continue;
          }

          if(isLetter(c))     //若为字母
          {
            wordReader.append(c);  //加入单词读取器
            letterNum++;  //字母数量+1
          }
          else
          {
            isThisWordValid=false;    //此单词不合法;将合法位置false
          }
          if(letterNum>=4)    //连续字母数量超过4个
          {
            isWordReading=true;  //开始读取单词
          }
        }
      }
      if(isWordReading)
      {
        isWordReading=false;
        num++;   //单词数量+1
        Integer count;
        String word=wordReader.toString().toLowerCase();
        letterNum=0;  //清空连续字母数量
        wordReader.delete(0,wordReader.length());   //清空单词读取器
        wordCountMap.put(word,(count=wordCountMap.get(word))==null?1:count+1);  //更新map
      }
      wordNum=num;
      return wordNum;
    }

    /**
    * 获取单词统计map
    * @return
    */
    public Map<String, Integer> getWordCountMapBySize(int size)
    {
      return wordCountMap
        .entrySet()
        .stream()
        .sorted(Map.Entry.<String, Integer> comparingByValue() //字母频率升序排序
            .reversed()//倒序
            .thenComparing(Map.Entry.comparingByKey()))//按照key排序
        .limit(size) //选择最前面的十个
        .collect(  //以map形式返回
            Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldVal, newVal) -> oldVal,
                LinkedHashMap::new
            )
        );
    }
  }

}
