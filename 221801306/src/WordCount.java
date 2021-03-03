import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
//      E:/JavaTest/input1.txt E:/JavaTest/output.txt
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        String output = scan.next();

        try {
            Date begin = new Date();
            long beginTime = begin.getTime();

            run(fileName, output);

            Date end = new Date();
            long endTime = end.getTime();
            long time = endTime - beginTime;
            System.out.println("successful,耗时" + time + "毫秒");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    /*    if (args.length < 2) {
            System.out.println("请输入“输入文件“和“输出文件”名称");
            return;
        } else {
            try {
                Date begin = new Date();
                long beginTime = begin.getTime();

                run(args[0], args[1]);

                Date end = new Date();
                long endTime = end.getTime();
                long time = endTime - beginTime;
                System.out.println("successful,耗时" + time + "毫秒");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        */
    }

    private static void run(String fileName, String output) {

        Map<String, Integer> map;
        int num = 10;
        String content;//文本内容
        content = Lib.getFile(fileName);//输入文件，仅用于统计单词

        map = Lib.countFrequency(content);//词频统计
        int chars = Lib.countCharacters(content);
        int words = Lib.countWords(content);
        int lines = Lib.countLines(fileName);
        String Frequency = Lib.sortFrequency(map, num);

        //输出字符、单词、行数、词频信息
        Lib.outputInfo(output,Lib.answerBuilder(chars, words, lines, Frequency));
    }
}