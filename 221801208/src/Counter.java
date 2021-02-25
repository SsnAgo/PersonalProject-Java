import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Counter {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private int countChar = 0;    //字符个数
    private int countWord = 0;    //单词总数
    private Map<String, Integer> map = new HashMap<>();
    private Set<Word> set = new TreeSet<>();

    class Word implements Comparable<Word>{

        String word;
        int frequency;    //单词出现次数

        Word(String s,int frqy){
            word = s;
            frequency = frqy;
        }

        @Override
        public int compareTo(Word w) {
            if(this.frequency > w.frequency)
                return -1;
            else if(this.frequency < w.frequency)
                return 1;
            else
                return this.word.compareTo(w.word);
        }
    }

    Counter(FileReader inputFile, FileWriter outputFile){
        reader = new BufferedReader(inputFile);
        writer = new BufferedWriter(outputFile);
    }

    //统计字符数
    public void countCharacters(){
        int c;
        try{
            while ((c = reader.read()) != -1){
                //ASCⅡ码在0到127之间的属于字符
                if(c >= 0 && c<= 127) {
                    countChar++;
                }
            }
        }
        catch (Exception e){
        }
    }

    //判断一个字符串是否为单词
    public boolean isWord(String curWord){
        //长度小于4不属于单词
        if(curWord.length() < 4)
            return false;
        //前四个字符为字母才属于单词
        for(int i = 0; i < 4; i++){
            if(!(curWord.charAt(i) >= 65 && curWord.charAt(i) <= 90) &&
                    !(curWord.charAt(i) >= 97 && curWord.charAt(i) <= 122)){
                return false;
            }
        }
        return true;
    }
}
