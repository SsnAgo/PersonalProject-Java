import java.io.*;
import java.util.*;

public class WordCount {
    public static void main(String[] args) {
//      "E:/JavaTest/input.txt E:/JavaTest/output.txt";
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        String output = scan.next();

        run(fileName,output);
    }

    private static void run(String fileName,String output){
        String content;//文本内容
        Map<String, Integer> map;
        int num = 10;//输出词频最高的10个单词

        content = Lib.getFile(fileName);//输入文件，仅用于统计单词
        Lib.printInfo(output, Lib.countCharacters(fileName), Lib.countWords(content), Lib.countLines(fileName));
        
        map = Lib.countFrequency(content);//词频统计
        Lib.sortFrequency(output, map, num);//词频排序和输出
    }
}