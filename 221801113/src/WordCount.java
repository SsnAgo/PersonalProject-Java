import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
    private static HashMap<String, Integer> wordMap;
    private static int charsNum;
    private static int linesNum;
    private static int wordsNum;
    private static String textStr;

    /**
     * @description    构造函数
     * @param filePath 读入文件的文件路径
     * @throws IOException
     */
    public WordCount(String filePath) throws IOException {
        wordMap = new HashMap<>();
        textStr = Lib.readFile(filePath);
        charsNum = Lib.countCharacters(textStr);
        linesNum = Lib.countLines(textStr);
        wordsNum = Lib.countWords(textStr, wordMap);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2){
            System.out.println("命令行参数个数错误");
            return;
        }

        WordCount wordCount = new WordCount(args[0]);
        Lib.writeFile(args[1], charsNum, wordsNum, linesNum, wordMap);
    }
}