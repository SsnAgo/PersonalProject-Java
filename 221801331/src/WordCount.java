import java.io.File;
import java.io.IOException;

public class WordCount
{
  public static void main(String[] args) throws IOException
  {
    TextParser textParser =new TextParser("D:\\DeskTop\\input.txt","D:\\DeskTop\\output.txt");
    textParser.parseFile();
    textParser.countValidChars();
    textParser.countValidLines();
    textParser.countWordNum();
    textParser.getWordCountMapBySize(10);
    textParser.writeToFile();
    
  }

}
