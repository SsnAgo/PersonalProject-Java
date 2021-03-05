import javax.crypto.spec.PSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordCount{
    int lineCount=0;
    Map<String,Integer> wordCountMap;
    int wordCount=0;
    byte[] sourceBytes;
    String outputFileName;




    public static void main(String[] args){
        if(args.length!=2){
            System.out.println("Wrong argument number!");
        }
        WordCount w=new WordCount();
        w.wordCountMap=new HashMap<>();
        String inputFileName=args[0];
        w.outputFileName=args[1];
        Lib lib=new Lib();
        w.sourceBytes=lib.readFileToBytes(inputFileName);
        if(w.sourceBytes==null){
            System.out.println("exit error");
        }else{
            lib.analyzeBytes(w);
            lib.output(w);
        }
    }
}
