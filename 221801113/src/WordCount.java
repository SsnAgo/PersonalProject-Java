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

    public WordCount(String fileName) throws IOException {
        wordMap = new HashMap<>();
        textStr = readFile(fileName);
        charsNum = Lib.countCharacters(textStr);
        linesNum = Lib.countLines(textStr);
        wordsNum = Lib.countWords(textStr, wordMap);
    }

    public String readFile(String fileName) throws IOException {
        BufferedReader reader = null;
        StringBuilder str = new StringBuilder();
        int ch = 0;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while ((ch = reader.read()) != -1) {
                str.append((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return str.toString();
    }

    public static void writeFile(String OutputPath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(OutputPath));
        int cnt = 0;
        StringBuilder str = new StringBuilder("characters: " + charsNum + '\n' + "words: " + wordsNum + '\n'
                + "lines: " + linesNum + '\n');
        List<HashMap.Entry<String, Integer>> sortedList = Lib.getSortedList(wordMap);
        for(HashMap.Entry<String,Integer> entry:sortedList) {
            str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            cnt++;
            if (cnt >= 10) break;
        }
        writer.write(str.toString());
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2){
            System.out.println("命令行参数个数错误");
            return;
        }
        WordCount wordCount = new WordCount(args[0]);
        writeFile(args[1]);
    }
}