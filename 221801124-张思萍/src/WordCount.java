import java.io.IOException;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {

        String filePath="C:\\Users\\yy\\Desktop\\input.txt";
        try {
            System.out.println("characters: "+DoWordCount.countChars(filePath));
            System.out.println("words: "+DoWordCount.countWords(FileIO.readFile(filePath)));
            System.out.println("lines: "+DoWordCount.countLines(FileIO.readFile(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
