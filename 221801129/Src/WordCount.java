import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
//        if (args.length < 2) {
//            System.err.println("Require 2 parameters");
//            return;
//        }
//        Lib lib = new Lib(args[0], args[1]);
        Lib lib = new Lib("C:\\Users\\xpy91\\Desktop\\Test\\input.txt", "C:\\Users\\xpy91\\Desktop\\Test\\output.txt");
        lib.handleFile();
    }
}
