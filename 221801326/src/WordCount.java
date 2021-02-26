import java.io.*;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) throws IOException {
        int characters = Lib.charactersCount(args[0],args[1]);
        int words = Lib.wordsCount(args[0],args[1]);
        int lines = Lib.linesCount(args[0],args[1]);
        Map<String, Integer> wordsMap = Lib.wordNum(args[0],args[1]);
    }
}
