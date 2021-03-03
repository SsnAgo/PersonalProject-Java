import java.io.*;
import java.util.Collections;

class WordCount{
    public static void main(String[] args) {
        File inputFile=new File(args[0]);
        File outputFile=new File(args[1]);
        int characterCount= CountCore.characterCount(inputFile);
        System.out.println(characterCount);
        CountCore.toLowerCase(inputFile);
    }

}