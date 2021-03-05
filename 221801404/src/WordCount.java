import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WordCount
{
    public static void main(String[] args)
    {
        String filePath = args[0];
        String content = Lib.readFile(filePath);
        int charactersNum = Lib.getCharacterNum(content);
        int linesNum = Lib.getLinesNum(content);
        int a = Lib.getWordsNum(content);
        Lib lib = new Lib();
        List list = lib.sortMap();
        System.out.println("charactersNum: "+charactersNum+"\nlinesNum: "+linesNum+"\n");
        System.out.println(list);
    }
}