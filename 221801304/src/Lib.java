
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
        while((ch = fileReader.read()) != -1){
            chars.append(ch);
            if(isAlpha((char) ch))
                countAlpha++;
            else{
                if(countAlpha < 4)
                    countAlpha = 0;//如果没有连续四个英文字母，计数清零
            }
            if(countAlpha >= 4){//如果有连续四个英文字母，合法单词诞生
                if(isAlpha((char) ch) || isNum((char) ch))
                    wordLength++;
                else{//遇到分隔符，单词确定
                    int len = chars.length();
                    String word = chars.substring(len - wordLength, len - 1).toLowerCase(Locale.ROOT);
                    if(map.containsKey(word)){
                        int value = map.get(word);
                        map.put(word, value+1);
                    }
                    else
                        map.put(word, 1);
                    countAlpha = 0;
                    wordLength = 3;
                }
            }

        }
        fileReader.close();
        return map;
    }

    public static int countWords(String inputFile, String outputFile) throws IOException{
        Map<String, Integer> map = handleWords(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile, true);
        int words = map.size();
        fileWriter.write("\n");
        fileWriter.write("words: " + words);
        fileWriter.close();
        return words;
    }

//    public static int countLines(String inputFile, String outputFile) throws IOException{
//        FileReader fileReader = new FileReader(inputFile);
//        FileWriter fileWriter = new FileWriter(outputFile, true);
//        int lines = 0;
//
//    }
//
//    public static void printWords(String inputFile, String outputFile) throws IOException{
//        Map<String, Integer> map = handleWords(inputFile);
//        FileWriter fileWriter = new FileWriter(outputFile);
//
//    }

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

    public static char[] subBytes(char[] src, int begin, int count) {
        char[] bs = new char[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }
}