
import java.io.*;
import java.util.*;

public class Lib {

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

    public static Map<String, Integer> handleWords(String inputFile) throws IOException{
        FileReader fileReader = new FileReader(inputFile);
        Map<String, Integer> map = new HashMap<>();
        StringBuffer chars = new StringBuffer();
        int ch = 0;
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
            if(countAlpha >= 4){//如果有连续四个英文字母，合法单词诞生
                wordFlag = true;
                if(isAlpha((char) ch) || isNum((char) ch))
                    wordLength++;
                else{//遇到分隔符，单词确定
                    wordFlag = false;
                    int len = chars.length();
                    String word = chars.substring(len - wordLength - 1, len - 1).toLowerCase(Locale.ROOT);
                    if(map.containsKey(word)){
                        int value = map.get(word);
                        map.put(word, value + 1);
                    }
                    else
                        map.put(word, 1);
                    countAlpha = 0;
                    wordLength = 3;
                }
            }
        }
        if(wordFlag){//防止读到结束时正在截取的单词的丢失
            String word = chars.substring(chars.length() - wordLength, chars.length()).toLowerCase(Locale.ROOT);
            if(map.containsKey(word)){
                int value = map.get(word);
                map.put(word, value + 1);
            }
            else
                map.put(word, 1);
        }
        fileReader.close();
        return map;
    }

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

    public static int countLines(String inputFile, String outputFile) throws IOException{
        FileReader fileReader = new FileReader(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile, true);
        int lines = 0;
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while((str = bufferedReader.readLine() )!= null){
            if(!str.equals(""))
                lines++;
        }

        fileWriter.write("\n");
        fileWriter.write("lines: " + lines);
        fileReader.close();
        bufferedReader.close();
        fileWriter.close();
        return lines;
    }

    public static void printWords(String inputFile, String outputFile) throws IOException{
        Map<String, Integer> map = handleWords(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile, true);

        //自定义比较器
        Comparator<Map.Entry<String, Integer>> valCmp = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        };

        //将map转成List，map的一组key，value对应list一个存储空间
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, valCmp);

        int size;//最高词频，最高为10个
        if(list.size() < 10)
            size = list.size();
        else
            size = 10;
        for(int i = 0; i < size; i++) {
            fileWriter.write("\n" + list.get(i).getKey() + ": " + list.get(i).getValue());
        }
        fileWriter.close();
    }

    public static boolean isAlpha(char ch){
        if(((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')))
            return true;
        else
            return false;
    }

    public static boolean isNum(char ch){
        if(ch >= '0' && ch <= '9')
            return true;
        else
            return false;
    }
}