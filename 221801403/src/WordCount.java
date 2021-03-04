import java.io.*;

class WordCount{
    public static void main(String[] args) throws IOException {
        Lib lib = new Lib("D://input.txt");
        lib.getCountChar();
        lib.getLine();
        lib.getWordNum();
        lib.getWordTopRate();
        }
}