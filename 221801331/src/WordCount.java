import Lib.*;

/**
 * WordCount；本次作业的可执行类
 */
public class WordCount
{
  private static CharParser charParser=new MyCharParser();  //本次作业使用的字符解析器

  private static WordParser wordParser=new MyWordParser();  //本次作业使用的单词解析器

  /**
   * 本次作业的执行函数
   * @param inputFile
   * @param outputFile
   */
  private static void execute(String inputFile,String outputFile)
  {
    TextParser textParser=new TextParser(inputFile,outputFile, charParser,wordParser);    //文本解析器
    textParser.countValidChars();         //统计有效字符数量
    textParser.countValidLines();         //统计有效行数统计
    textParser.countWordNum();            //统计单词数量
    textParser.getWordCountMapBySize(10); //获取单词频率map中的前十位
    textParser.writeToFile();             //将统计结果输出至对应文件路径
  }

  public static void main(String[] args)
  {
    if(args.length!=2)
    {
      System.out.println("参数数量应为2个!!!");
      return;
    }
    execute(args[0],args[1]);   //执行
  }

}
