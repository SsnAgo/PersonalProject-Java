import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  save the counting result of valid characters ,lines and words
 */
public class CountResultHolder {
    public static int charactersCount = 0;
    public static int linesCount = 0;
    public static int wordsCount = 0;
    public static Map<String,Integer> wordToNumMap = new LinkedHashMap<>();
}
