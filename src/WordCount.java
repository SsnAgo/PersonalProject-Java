import java.io.*;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) throws IOException {
        int characters = Lib.charactersCount("C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\input.txt", "C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\output.txt");
        System.out.println("characters:" + characters);
        int words=Lib.wordsCount("C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\input.txt","C:\\Users\\WWJ20\\IdeaProjects\\WordCount\\src\\output.txt");
        System.out.println("words:"+words);

    }
}
