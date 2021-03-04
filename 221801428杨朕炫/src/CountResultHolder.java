import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  保存统计值，添加了安全访问的方法
 */
public class CountResultHolder {
    private static int charactersCount = 0;
    private  static int linesCount = 0;
    private static int wordsCount = 0;
    private static Map<String,Integer> wordToNumMap = new LinkedHashMap<>();

    public static void increaseCharactersCount(){
        charactersCount ++;
    }

    public static void increaseLinesCount(){
        linesCount ++;
    }
    public static void increaseWordsCount(){
        wordsCount ++;
    }
    public static void putIntoMap(String key, Integer value){
        wordToNumMap.put(key,value);
    }

    public static int getCharactersCount() {
        return charactersCount;
    }

    public static int getLinesCount() {
        return linesCount;
    }

    public static int getWordsCount() {
        return wordsCount;
    }
}
