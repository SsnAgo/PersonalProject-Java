import com.sun.deploy.util.ArrayUtil;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Lib {

    public static int countChar(String inputFile,String outputFile) throws IOException {
        FileReader fileReader = new FileReader(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile,true);
        int characters = 0;//文件中的字符数
        while(fileReader.read() != -1){
            characters++;
        }
        fileWriter.write("characters: " + characters);
        fileReader.close();
        fileWriter.close();
        return characters;
    }

    public static int countWord(String inputFile,String outputFile) throws IOException{
        FileReader fileReader = new FileReader(inputFile);
        FileWriter fileWriter = new FileWriter(outputFile,true);
        Map<String, Integer> map = new HashMap<>();
        char[] chars = new char[1024];//缓冲区数组
        int len;//字符数组大小
        while(fileReader.read(chars) != -1){
            len = chars.length;
            int countAlpha = 0;//字母数
            int wordLength = 3;//单词长度
            for(int i = 0; i < len; i++){
                if(isAlpha(chars[i]))
                    //如果是英文字母，就+1
                    countAlpha++;
                else{
                    if(countAlpha < 4)
                        //如果没有连续四个英文字母，计数清零
                        countAlpha = 0;
                }
                if(countAlpha >= 4){//如果有连续四个英文字母，合法单词诞生
                    if(isAlpha(chars[i]) || isNum(chars[i]))
                        wordLength++;
                    else{//遇到分隔符，单词确定
                        char[] ch = subBytes(chars,i - wordLength,wordLength);
                        String word = ch.toString();
                        if(map.containsKey(word)){
                            int value = map.get(word);
                            map.put(word,value+1);
                        }
                        else
                            map.put(word,1);
                        countAlpha = 0;
                        wordLength = 3;
                    }
                }
            }
        }
        int words = map.size();
        fileWriter.write("words: "+ words);
        fileReader.close();
        fileWriter.close();
        return words;
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

    public static boolean isSpace(char ch){
        if(ch == ' ')
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