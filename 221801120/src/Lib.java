import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    private String inputFile;
    private String outputFile;
    private String content;
    private int charsNum;
    private int wordsNum;
    private int linesNum;
    private Map<String, Integer> wordsMap;

    /**
     * 构造函数
     * @param inputFile 读取文件地址
     * @param outputFile 写入文件地址
     */
    public Lib(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     * 读取文件内容
     */
    public void readFile() throws IOException {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try{
            reader = new BufferedReader(new FileReader(inputFile));
            int num = 0;
            char ch;
            while ((num = reader.read()) != -1) {
                // UTF-8中'\n'对应编码int值为13
                if (num != 13) {
                    ch = (char) num;
                    builder.append(ch);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
        content = builder.toString();
    }

    /**
     * 写入文件
     */
    public void writeFile() throws IOException {
        countCharsNum();
        countWordsNum();
        countLinesNum();
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write("characters: " + charsNum + "\n");
            writer.write("words: " + wordsNum + "\n");
            writer.write("lines: " + linesNum + "\n");
            List<Map.Entry<String, Integer>> topWords = sortWordsMap();
            int top = 0;
            for(Map.Entry<String, Integer> map : topWords){
                if (top < 10){
                    writer.write(map.getKey() + ": " + map.getValue() + "\n");
                    top++;
                }
                else {
                    break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 统计字符数
     */
    public void countCharsNum(){
        charsNum = content.length();
    }

    /**
     * 统计单词数，以及每个单词出现的次数
     */
    public void countWordsNum(){
        wordsNum = 0;
        wordsMap = new TreeMap<String,Integer>();
        String word;
        Integer num;
        String[] words = content.split("[^a-zA-Z0-9]+");
        for (int i = 0; i < words.length; i++){
            if (words[i].matches("[a-zA-Z]{4,}[a-zA-Z0-9]*")) {
                wordsNum++;
                word = words[i].toLowerCase();
                if (wordsMap.containsKey(word)){
                    num = wordsMap.get(word);
                    num++;
                    wordsMap.put(word, num);
                }
                else {
                    wordsMap.put(word, 1);
                }
            }
        }
    }

    /**
     * 统计非空行数
     */
    public void countLinesNum(){
        linesNum = 0;
        Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");
        Matcher matcher = linePattern.matcher(content);
        while (matcher.find()){
            linesNum++;
        }
    }

    /**
     * 根据单词频率进行排序
     */
    public List<Map.Entry<String, Integer>> sortWordsMap(){
        List<Map.Entry<String, Integer>> wordsList = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());
        Collections.sort(wordsList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> word1,
                               Map.Entry<String, Integer> word2) {
                return word2.getValue() - word1.getValue();
            }
        });
        return wordsList;
    }
}
