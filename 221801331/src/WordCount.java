import Lib.*;

import java.io.IOException;

public class WordCount
{
  private static CharParser charParser=new MyCharParser();  //本次作业使用的字符解析器

  private static WordParser wordParser=new MyWordParser();  //本次作业使用的单词解析器

  public static void main(String[] args) throws IOException
  {
    TextParser textParser =new TextParser(args[0],args[1], charParser,wordParser);
    textParser.countValidChars();
    textParser.countValidLines();
    textParser.countWordNum();
    textParser.getWordCountMapBySize(10);
    textParser.writeToFile();
  }

}
