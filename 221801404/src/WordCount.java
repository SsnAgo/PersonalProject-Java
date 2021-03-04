import java.io.FileWriter;
import java.io.IOException;

public class WordCount
{
    public static void main(String[] args)
    {
        String filePath = args[0];
        String content = Lib.readFile(filePath);
        FileWriter writer;
        try {
            writer = new FileWriter(args[1]);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int charactersNum = Lib.getCharacterNum(content);
        System.out.println("字符数："+charactersNum);
        int linesNum = Lib.getLinesNum(content);
        System.out.println("行数："+linesNum);
    }
}