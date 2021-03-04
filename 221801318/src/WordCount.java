import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        Lib lib = new Lib();
        String inFilePath = args[0];
        String outFilePath = args[1];
        lib.createOutput(inFilePath,outFilePath);
    }
}