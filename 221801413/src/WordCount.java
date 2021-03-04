import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCount
{
    public static void main(String[] args) {
        Lib lib= new Lib();
        String path="E://input.txt";
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        String word=lib.readFile(path);
        lib.creatWordMap(word,wordMap);

        String str = lib.readFile(path);
        System.out.println("wordsNum:"+lib.getWordNum(str));
        System.out.println("lines:"+lib.getLines(str));

        List<Map.Entry<String, Integer>> topTenWords = new ArrayList<Map.Entry<String, Integer>>();
        topTenWords = lib.sortWordMap(wordMap);

        for(Map.Entry<String,Integer> map : topTenWords) {
            System.out.println(map.getKey()+":"+map.getValue());
        }

    }
}
