import java.io.*;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) throws IOException {
        int characters = Lib.charactersCount("C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\input.txt", "C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\output.txt");
        int words = Lib.wordsCount("C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\input.txt", "C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\output.txt");
        int lines = Lib.linesCount("C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\input.txt", "C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\output.txt");
        Map<String, Integer> wordsMap = Lib.wordNum("C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\input.txt", "C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\output.txt");
    }
}
