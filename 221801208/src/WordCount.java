import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args){
        try{
            //程序开始运行的时间
            Date begin = new Date();
            long beginTime = begin.getTime();

            //输入文件与输出文件
            File inputFile = new File(args[0]);
            File ouputFile = new File(args[1]);

            Lib counter = new Lib(inputFile,ouputFile);
            //输出字符数和单词数
            counter.countCharAndWord();
            //输出有效行数
            counter.countRowNum();
            //输出频率最高的10个单词的出现次数
            counter.printFrequency();

            //关闭文件
            counter.closeFile();

            //程序结束运行的时间
            Date end = new Date();
            long endTime = end.getTime();

            //运行耗时
            long time = endTime - beginTime;
            System.out.println("耗时"+time+"毫秒");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
