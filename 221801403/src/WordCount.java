import java.io.*;

class WordCount{
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("please input more than 2 chars!");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];
        //Lib lib = new Lib("D://input.txt","D://output.txt");
        Lib lib = new Lib(inputFile,outputFile);
        lib.getCountChar();
        lib.getLineCount();
        lib.getWordNum();
        lib.getWordTopRate();
        lib.writeFile();
        }
}