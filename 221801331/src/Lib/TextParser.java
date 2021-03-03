package Lib;

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
  }

  /**
   * 有参构造
   * @param outputFile
   * @param inputFile
   */
  public TextParser(String inputFile,String outputFile,
                    CharParser charParser,WordParser wordParser)
  {
    this.outputFile = outputFile;
    this.inputFile = inputFile;
    this.validCharsNum=0;
    this.validLinesNum=0;
    this.wordNum=0;
    this.readFile(); //读取文件内容
    this.wordCountMap=new HashMap<>();
    this.charParser=charParser;  //生成字符解析器
    charParser.parseText(textContent);  //字符解析器解析文本
    this.wordParser=wordParser;  //生成单词解析器
  }


  /**
   * 全参数构造方法
   * @param inputFile
   * @param outputFile
   * @param textContent
   * @param validCharsNum
   * @param validLinesNum
   * @param wordNum
   * @param wordCountMap
   * @param charParser
   * @param wordParser
   */
  public TextParser(String inputFile, String outputFile, String textContent,
                    int validCharsNum, int validLinesNum, int wordNum,
                    Map<String, Integer> wordCountMap,
                    CharParser charParser, WordParser wordParser)
  {
    this.inputFile = inputFile;
    this.outputFile = outputFile;
    this.textContent = textContent;
    this.validCharsNum = validCharsNum;
    this.validLinesNum = validLinesNum;
    this.wordNum = wordNum;
    this.wordCountMap = wordCountMap;
    this.charParser = charParser;
    this.wordParser = wordParser;
  }

  /**
   * 将文件内容读取为文本
   * @return
   */
  public String readFile()
  {
    return textContent=IOHandler.readFile(inputFile);
  }

  /**
   * 统计有效ASCII字符
   * @return
   */
  public int countValidChars()
  {
    return validCharsNum=charParser.countValidChars();
  }

  /**
   * 统计有效行数
   * @return
   */
  public int countValidLines()
  {
    return validLinesNum=charParser.countValidLines();
  }

  /**
   * 统计单词数量
   * @return
   */
  public int countWordNum()
  {
    return wordNum=wordParser.countWord(textContent);
  }

  public Map<String,Integer> getWordCountMapBySize(int size)
  {
    return wordCountMap=wordParser.getWordCountMapBySize(size);
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

}
