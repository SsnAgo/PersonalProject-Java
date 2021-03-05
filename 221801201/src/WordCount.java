import javax.crypto.spec.PSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordCount{
    int lineCount=0;
    Map<String,Integer> wordCountMap=new HashMap<>();
    int wordCount=0;
    int characterCount=0;
    byte[] sourceBytes;
    String outputFileName;

    public static void main(String[] args){
        if(args.length!=2){
            System.out.println("Wrong argument number!");
        }
        WordCount w=new WordCount();
        String inputFileName=args[0];
        w.outputFileName=args[1];
        Lib lib=new Lib();
        w.sourceBytes=lib.readFileToBytes(inputFileName);
        if(w.sourceBytes==null){
            System.out.println("Exit with file reading error.");
        }else{
            lib.analyzeBytes(w);
            w.lineCount=lib.countLines(inputFileName);
            lib.output(w);
            int temp=0;
//            for(int i=0;i<w.sourceBytes.length;i++){
//                if(w.sourceBytes[i]=='\r'){
//                    System.out.print("\\r");
////                    System.out.print(i+"\\r");
//                }else if(w.sourceBytes[i]=='\n'){
//                    System.out.print("\\n");
////                    System.out.println(i+"\\n");
//                    if(i>1&&w.sourceBytes[i]=='\n'&&w.sourceBytes[i-1]!='\n'&&w.sourceBytes[i-2]!='\n'){//分析行数
//                        temp++;
//                        System.out.println(temp);
//                    }
//                }else{
//                    System.out.print((char)w.sourceBytes[i]);
//                }
//            }
        }
    }
}
