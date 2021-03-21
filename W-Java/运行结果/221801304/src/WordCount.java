import java.io.IOException;
import java.util.Map;

public class WordCount {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        run(args[0], args[1]);
        System.out.printf("耗时： %d ms\n",System.currentTimeMillis() - startTime);
    }

    public static void run(String inputFile, String outputFile) throws IOException {
        String str = Lib.getStr(inputFile);
        int characters = Lib.countChars(str);
        int lines = Lib.countLines(inputFile);
        Map<String, Integer> map = Lib.handleWords(str);
        int words = Lib.countWords(map);
        String freq = Lib.printWords(map);
        Lib.writeToFile(outputFile, characters, words, lines, freq);
    }

}
