import java.util.Date;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
//      E:/JavaTest/input1.txt E:/JavaTest/output.txt

        String fileName = "E:/JavaTest/input2.txt";
        String output = "E:/JavaTest/output.txt";

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
/*
        if (args.length < 2) {
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
        //输入文件内容
        String content= Lib.getFile(fileName);
        // 词频统计
        map = Lib.countFrequency(content);
        //获得字符数
        int chars = Lib.countCharacters(content);
        //获得单词数
        int words = Lib.countWords(map);
        //获得行数
        int lines = Lib.countLines(content);
        //获得词频数前十
        String Frequency = Lib.sortFrequency(map, num);

        //输出字符、单词、行数、词频信息
        Lib.outputInfo(output,Lib.answerBuilder(chars, words, lines, Frequency));
    }
}