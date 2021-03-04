import Lib.MyCharParser;
import Lib.MyWordParser;
import Lib.TextParser;

import java.io.IOException;
import java.util.Date;

public class WordCount
{
  public static void main(String[] args) throws IOException
  {
    TextParser textParser =new TextParser("D:\\DeskTop\\input.txt",
        "D:\\DeskTop\\output.txt",
        new MyCharParser(),new MyWordParser());
    textParser.countValidChars();
    textParser.countValidLines();
    textParser.countWordNum();
    textParser.getWordCountMapBySize(10);
    textParser.writeToFile();

  }

}
