import java.io.*;

public class WordCount {

    public static void main(String[] args) throws IOException {
        Counter counter = new Counter();
        counter.open();
        counter.write();
    }
}
