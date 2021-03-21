import java.io.IOException;
import java.nio.file.Paths;

public class WordCount {

    public static void main(String[] args) throws IOException {
        WordCountHelper.solve(Paths.get(args[0]), Paths.get(args[1]));
    }
    
}