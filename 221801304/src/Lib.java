
import java.io.*;
import java.util.*;

public class Lib {

    //统计字符数
    public static int countChars(String inputFile, String outputFile) throws IOException {
        FileReader fileReader = new FileReader(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile, true);
        int characters = 0;//文件中的字符数
        while(fileReader.read() != -1){
            characters++;
        }
        fileWriter.write("characters: " + characters);
        fileReader.close();
        fileWriter.close();
        return characters;
    }

    //统计单词并填充入map
    public static Map<String, Integer> handleWords(String inputFile) throws IOException{
        FileReader fileReader = new FileReader(inputFile);
        Map<String, Integer> map = new HashMap<>();
        StringBuffer chars = new StringBuffer();
        int ch;//每次读取到的字符
        int countAlpha = 0;//字母数
        int wordLength = 3;//单词长度
        boolean wordFlag = false;//是否成单词
        while((ch = fileReader.read()) != -1){
            chars.append((char) ch);
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
                    insertMap(map, chars, wordLength, len);
                    countAlpha = 0;
                    wordLength = 3;
                }
            }
        }
        if(wordFlag){//防止读到结束时正在截取的单词的丢失
            int len = chars.length() + 1;
            insertMap(map, chars, wordLength, len);
        }
        fileReader.close();
        return map;
    }

    //判断单词前是否为分隔符或者空格（因为要复用所以提取出来），是则填充map
    public static void insertMap(Map<String, Integer> map, StringBuffer chars, int wordLength, int len){
        String word = chars.substring(len - wordLength - 1, len - 1).toLowerCase(Locale.ROOT);
        if(word.length() < len - 1 && isNum(chars.charAt(len - wordLength - 2))){
            //单词前有分隔符或无字符才算是单词
        }else if(map.containsKey(word)){
            int value = map.get(word);
            map.put(word, value + 1);
        }else
            map.put(word, 1);
    }

    //从map提取数据计算并打印单词数
    public static int countWords(String inputFile, String outputFile) throws IOException{
        Map<String, Integer> map = handleWords(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile, true);
        int words = 0;
        for(int value : map.values()){
            words += value;
        }
        fileWriter.write("\n");
        fileWriter.write("words: " + words);
        fileWriter.close();
        return words;
    }

    //统计有效行数
    public static int countLines(String inputFile, String outputFile) throws IOException{
        FileReader fileReader = new FileReader(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile, true);
        int lines = 0;
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while((str = bufferedReader.readLine() )!= null){
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

        fileWriter.write("\n");
        fileWriter.write("lines: " + lines);
        fileReader.close();
        bufferedReader.close();
        fileWriter.close();
        return lines;
    }

    //从map提取词频最多的十个单词并打印
    public static void printWords(String inputFile, String outputFile) throws IOException{
        Map<String, Integer> map = handleWords(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile, true);

        //自定义比较器
        Comparator<Map.Entry<String, Integer>> valCmp = (o1, o2) -> o2.getValue() - o1.getValue();

        //将map转成List，map的一组key，value对应list一个存储空间
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(valCmp);

        int size = Math.min(list.size(), 10);//最高词频，最高为10个
        for(int i = 0; i < size; i++) {
            fileWriter.write("\n" + list.get(i).getKey() + ": " + list.get(i).getValue());
        }
        fileWriter.close();
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