import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Lib {

    private int characterNum;
    private int wordNum;
    private int lineNum;
    private final String inputFile;
    private final String outputFile;
    private Map<String, Integer> map;
    public List<Map.Entry<String, Integer>> wordsRank;

    private final String WordPattern = "^[A-Za-z]{4}[A-Za-z0-9]*";
    private final String LinePattern = "[\\s\\S]*\\S+[\\s\\S]*";

    /**
     * Lib构造函数
     * @param inputFile 输入文件地址
     * @param outputFile 输出文件地址
     */
    public Lib(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        characterNum = 0;
        wordNum = 0;
        lineNum = 0;
    }

    public int getCharactersNum() {
        return characterNum;
    }

    public int getWordNum() {
        return wordNum;
    }

    public int getLineNum() {
        return lineNum;
    }

    /**
     *调用各功能的函数
     * @throws IOException
     */
    public void handleFile() throws IOException {
    String file = readFile();
    countCharacter(file);
    countLine(file);
    countWord(file);
    sortWords();
    setOutputFile();
}

    /**
     * 将文件读取进字符串
     * @return 文件内容字符串
     * @throws IOException
     */
    public String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        StringBuilder builder = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            builder.append((char) c);
        }
        reader.close();
        return builder.toString();
    }

    /**
     * 计算字符数
     * @param str 文件字符串
     */
    public void countCharacter(String str) {
        characterNum = str.length();
    }

    /**
    * 计算有效行数
    * @param str 文件字符串
    */
    public void countLine(String str) {
    String[] list = str.split("\n");
        for (String s: list) {
            if (isLine(s)) {
                lineNum++;
            }
        }
    }

    /**
     * 判断是否是有效行
     * @param str 分割后各字符串
     */
    public boolean isLine(String str) {
        return Pattern.matches(LinePattern, str);
    }

    /**
     * 计算单词数
     * @param str 文件字符串
     */
    public void countWord(String str) {
        map = new TreeMap<>();
        String[] list = str.split("[^A-Za-z0-9]");
        for (String s : list) {
            //判断字符串是否为空
            if (s.length() != 0 && isWord(s)) {
                wordNum++;
                String lowerCase = s.toLowerCase();
                if (map.get(lowerCase) == null) {
                    map.put(lowerCase, 1);
                }
                else {
                    int cnt = map.get(lowerCase);
                    map.put(lowerCase, cnt+1);
                }
            }
        }
    }

    /**
     * 判断是否是单词
     * @param str 分割后各字符串
     */
    public boolean isWord(String str) {
        return Pattern.matches(WordPattern, str);
    }

    /**
     * 对amp进行排序
     */
    public void sortWords() {
        wordsRank = new ArrayList<>(map.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator = (o1, o2) -> o2.getValue()- o1.getValue();
        wordsRank.sort(comparator);
    }

    /**
     * 写入输出文件
     */
    public void setOutputFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write("characters: " + characterNum + "\n");
        writer.write("words: " + wordNum + "\n");
        writer.write("lines: " + lineNum + "\n");
        wordsRank.forEach(item -> {
            try {
                writer.write(item.getKey() + ": " + item.getValue() + "\n");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
    }
}