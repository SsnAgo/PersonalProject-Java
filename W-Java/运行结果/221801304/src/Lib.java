
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Lib {
    //获得输入流
    public static BufferedReader getReader(String inputFile) throws FileNotFoundException {
        return new BufferedReader(new FileReader(inputFile));
    }

    //获得输出流
    public static BufferedWriter getWriter(String outputFile) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8));
    }

    //标准化输出到文件
    public static String writeToFile(String outputFile, int characters, int words, int lines, String freq)
            throws IOException {
        BufferedWriter writer = getWriter(outputFile);
        StringBuilder str = new StringBuilder();
        str.append("characters: ").append(characters).append("\n")//字符数
                .append("words: ").append(words).append("\n")//单词数
                .append("lines: ").append(lines).append("\n")//有效行数
                .append(freq);//词频最高前十个的单词及其词频

        writer.write(String.valueOf(str));
        writer.close();
        return String.valueOf(str);
    }

    //获取流中字符串
    public static String getStr(String inputFile) throws IOException {
        BufferedReader reader = getReader(inputFile);
        StringBuilder stringBuffer = new StringBuilder();
        int ch;
        while((ch = reader.read()) != -1){
            stringBuffer.append((char) ch);
        }
        reader.close();
        return String.valueOf(stringBuffer);
    }

    //统计字符数
    public static int countChars(String str) {
        return str.length();
    }

    //统计单词并填充入map
    public static Map<String, Integer> handleWords(String str) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder chars = new StringBuilder();
        int i = 0;
        int ch;//每次读取到的字符
        int countAlpha = 0;//字母数
        int wordLength = 3;//单词长度
        boolean wordFlag = false;//是否成单词
        while(i < str.length()){
            ch = str.charAt(i++);
            chars.append((char) ch);//每一次拼接一个字符
            if(isAlpha((char) ch))
                countAlpha++;
            else{
                if(countAlpha < 4)
                    countAlpha = 0;//如果没有连续四个英文字母，计数清零
            }
            if(countAlpha >= 4){//有连续四个英文字母
                wordFlag = true;
                int len = chars.length();
                if(isAlpha((char) ch) || isNum((char) ch))
                    wordLength++;
                else{//遇到分隔符
                    wordFlag = false;
                    insertMap(map, chars, wordLength, len);//填充map
                    countAlpha = 0;
                    wordLength = 3;
                }
            }
        }
        if(wordFlag){//防止读到结束时正在截取的单词的丢失
            int len = chars.length() + 1;
            insertMap(map, chars, wordLength, len);
        }
        return map;
    }

    //判断单词前是否为分隔符或者空格（因为要复用所以提取出来），是则填充map
    public static void insertMap(Map<String, Integer> map, StringBuilder chars, int wordLength, int len){
        String word = chars.substring(len - wordLength - 1, len - 1).toLowerCase(Locale.ROOT);
        if(word.length() < len - 1 && isNum(chars.charAt(len - wordLength - 2))){
            //单词前有分隔符或无字符才算是单词
        }else if(map.containsKey(word)){
            int value = map.get(word);
            map.put(word, value + 1);
        }else
            map.put(word, 1);
    }

    //从map提取数据计算并返回单词数
    public static int countWords(Map<String, Integer> map) {
        int words = 0;
        for(int value : map.values()){
            words += value;
        }
        return words;
    }

    //统计有效行数
    public static int countLines(String inputFile) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        int lines = 0;
        String str;
        while((str = reader.readLine() )!= null){
            if(!str.equals("")) {
                int len = str.length();
                for (int i = 0; i < len; i++) {
                    char ch = str.charAt(i);
                    if (!isBlank(ch)) {
                        lines++;
                        break;
                    }
                }
            }
        }
        return lines;
    }

    //从map提取词频最多的十个单词并返回字符串
    public static String printWords(Map<String, Integer> map) {

        //自定义比较器
        Comparator<Map.Entry<String, Integer>> valCmp = (o1, o2) -> {
            if(o1.getValue().equals(o2.getValue())){
                return o1.getKey().compareTo(o2.getKey());//词频相同按照字典序排序
            }else
                return o2.getValue() - o1.getValue();//词频高的在前
        };

        //将map转成List，map的一组key，value对应list一个存储空间
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(valCmp);

        int size = Math.min(list.size(), 10);//最高词频，最高为10个
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < size; i++) {
            str.append(list.get(i).getKey()).append(": ").append(list.get(i).getValue()).append("\n");
        }
        return String.valueOf(str);
    }

    //判断是否是字母
    public static boolean isAlpha(char ch){
        return ((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z'));
    }

    //判断是否是数字
    public static boolean isNum(char ch){
        return ch >= '0' && ch <= '9';
    }

    //判断是否是空白符
    public static boolean isBlank(char ch){
        return ch == '\n' || ch == '\t' || ch == '\r' || ch == 32;
    }
}