import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        Lib lib = new Lib("C:\\Users\\xpy91\\Desktop\\Test\\test.txt", "C:\\Users\\xpy91\\Desktop\\Test\\output.txt");
        lib.handleFile();
    }
}
