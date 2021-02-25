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


}
