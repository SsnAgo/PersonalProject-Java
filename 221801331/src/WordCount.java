import java.io.File;
import java.io.IOException;

public class WordCount
{
  public static void main(String[] args) throws IOException
  {

    FileParser fileParser=new FileParser(new File(args[0]),args[1]);
    fileParser.countValidChars();
    fileParser.countValidLines();
    fileParser.countWord();
    fileParser.writeToFile();
  }

}
