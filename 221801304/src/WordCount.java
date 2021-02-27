import java.io.IOException;

public class WordCount {

    public static void main(String[] args) throws IOException {
        int count = Lib.countChar("221801304/src/input.txt","221801304/src/output.txt");
        int num = Lib.countWord("221801304/src/input.txt","221801304/src/output.txt");
    }
}
