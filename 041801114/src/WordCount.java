import java.io.IOException;
import java.nio.file.Paths;

import info.tozzger.demo.DemoHelper;

public class WordCount {

    public static void main(String[] args) throws IOException {
        DemoHelper.solve(Paths.get(args[0]), Paths.get(args[1]));
    }
    
}