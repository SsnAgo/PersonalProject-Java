import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCount
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        @SuppressWarnings("unchecked")
        Lib lib= new Lib();
        String inPut = "input.txt";
        String outPut = "output.txt";
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        String word=lib.readFile(inPut);
        lib.creatWordMap(word,wordMap);

        String str = lib.readFile(inPut);
        System.out.println("wordsNum:"+lib.getWordNum(str));
        System.out.println("lines:"+lib.getLines(str));

        List<Map.Entry<String, Integer>> topTenWords = new ArrayList<Map.Entry<String, Integer>>();
        topTenWords = lib.sortWordMap(wordMap);

        for(Map.Entry<String,Integer> map : topTenWords) {
                System.out.println(map.getKey()+":"+map.getValue());
        }

        String outMessage = lib.outMessage(lib.getWordNum(str),lib.getLines(str),lib.getCharactersNum(str), topTenWords);
        lib.writeFile(outMessage,outPut);
    }
}
