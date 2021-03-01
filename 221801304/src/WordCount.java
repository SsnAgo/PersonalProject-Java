import java.io.IOException;
import java.util.Map;

public class WordCount {

    public static void main(String[] args) throws IOException {
        String str = Lib.getStr("221801304/src/input.txt");
        int characters = Lib.countChars(str);
        int lines = Lib.countLines("221801304/src/input.txt");
        Map<String, Integer> map = Lib.handleWords(str);
        int words = Lib.countWords(map);
        String freq = Lib.printWords(map);
        String result = Lib.writeToFile("221801304/src/output.txt", characters, words, lines, freq);
        System.out.println(result);
    }
}
