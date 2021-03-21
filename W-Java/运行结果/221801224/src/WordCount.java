import java.io.*;
import java.util.*;

public class WordCount {

    public static void main(String args[])throws IOException{
        String inputFileName = args[0];
        String outputFileName = args[1];
        if(args[0] == null ||args[1] == null){
            System.out.println("输入参数错误！");
        }
        //File file = new File("C:/Users/Lenovo/Desktop/input1.txt");
        File file = new File(inputFileName);
        Lib lib = new Lib(file);

        int chars = lib.getChars();
        int lines = lib.getLines();
        int words = lib.getWords();
        String[] wordSet = lib.getWordFreK();
        int[] freSet = lib.getWordFreV();

        /* idea输出测试 */
//        int i = 0;
//        while(wordSet[i] != null){
//            System.out.println(wordSet[i] + ":" + freSet[i]);
//            i++;
//        }

        /* 写入文件部分 */
        File file1 = new File(outputFileName);
        PrintWriter output = null;
        try
        {
            output = new PrintWriter(file1);
            output.println("characters: " + chars);
            output.println("words: " + words);
            output.println("lines: " + lines );
            int i = 0;
            while(wordSet[i] != null){
                output.println(wordSet[i] + ": " + freSet[i]);
                i++;
            }
            output.close();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
