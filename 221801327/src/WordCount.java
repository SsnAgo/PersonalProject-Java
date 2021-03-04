import java.io.*;
import java.util.*;

import static java.lang.Character.isLetter;

/*
  类名：WordCount
  作者:黄明亮 日期:2021-3-4
  模块描述: 实现读取一个文件，调用Lib类的方法执行操作，并输出一个output文件。
  函数列表: outputFile(File inputFile, File outputFile)
*/
public class WordCount{
    public static void main(String[] args){
        //File inputFile = new File(args[0]);
        File inputFile = new File("src\\input.txt");
        File OUTPUT_File = new File("output.txt");
        outputFile(inputFile, OUTPUT_File);
    }
    /*
     函数名：   outputFile(File inputFile, File outputFile)
     函数描述:  执行函数获取返回值并输出文件
     输入:      输入文件路径，输出文件路径
     返回值:
     其他说明:  函数包括：计算文件字符数，单词总数，有效行数，单词的出现次数函数
    */
    public static void outputFile(File inputFile, File outputFile){
        int PRINT_WORDS_NUMBER = 10;

        int characters = Lib.countCharacters(inputFile);
        System.out.println(characters);
        int words = Lib.countTotalWords(inputFile);
        System.out.println(words);
        int lines = Lib.countValidLines(inputFile);
        System.out.println(lines);
        List<Map.Entry<String, Integer>> list = Lib.countWords(inputFile);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getKey()+":"+list.get(i).getValue());
        }

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
            String content = "characters: " + characters + '\n';
            content += "words: " + words + "\n";
            content += "lines: " + lines + "\n";
            for(int i = 0; i < PRINT_WORDS_NUMBER && i < list.size(); i++)
                content += list.get(i).getKey() + ": " + list.get(i).getValue() + "\n";

            bufferedOutputStream.write(content.getBytes());

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}