import java.io.File;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {

        String str = Lib.readFile(Lib.DIR+"\\src\\input.txt");
        int lines = Lib.countLines(new File(Lib.DIR+"\\src\\input.txt"));
        int words = Lib.countWords(str);
        System.out.println("Lines:"+lines);
        System.out.println("Words:"+words);
    }
}
