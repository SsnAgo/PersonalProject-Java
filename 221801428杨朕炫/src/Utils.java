import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    /**
     * get a List contains every line in the input file
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static List<String> getLineList(String filename) throws IOException {
        Path inputFilePath = Path.of(filename);
        Stream<String> linesStream = Files.lines(inputFilePath);
        return linesStream.collect(Collectors.toList());
    }

    /**
     * compare two String type values by dictionary order
     * @param str1
     * @param str2
     * @return
     */
    public static int compareStringByDict(String str1, String str2) {
        for (int i = 0, j = 0; i < str1.length() && j < str2.length(); i++, j++) {
            if (str1.charAt(i) >= str2.charAt(i)) {
                return -1;
            } else if (str1.charAt(i) < str2.charAt(i)) {
                return 1;
            }
        }
        if (str1.length() == str2.length()) {
            return 0;
        } else if (str1.length() > str2.length()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * sort Map by the count of words
     *
     * @param wordsToNumMap
     * @return
     */
    public static Map<String, Integer> sortMapByNum(Map<String, Integer> wordsToNumMap) {
        HashMap<String, Integer> sortedWordsToNumMap = new LinkedHashMap<>();

        wordsToNumMap.entrySet()
                .stream()
                .sorted((p1, p2) -> {
                    // 单词数不一样则按单词数目排序
                    if (p1.getValue() != p2.getValue()) {
                        return p2.getValue().compareTo(p1.getValue());
                    } else {
                        // 单词数一致则按字典顺序排序
                        return compareStringByDict(p2.getKey(),p1.getKey());
                    }
                })
                .collect(Collectors.toList()).forEach(ele -> sortedWordsToNumMap.put(ele.getKey(), ele.getValue()));
        return sortedWordsToNumMap;
    }


    /**
     * generate output string message
     * @param sortedWordsToNumMap
     * @return
     */
    public static StringBuilder generateOutputString(Map<String,Integer> sortedWordsToNumMap){
        StringBuilder outputStringBuilder = new StringBuilder();
        outputStringBuilder.append(String.format("characters: %s\nlines: %s\nwords: %s\n",
                CountResultHolder.charactersCount,
                CountResultHolder.linesCount,
                CountResultHolder.wordsCount));
        // 限制最多输出10个
        int i = 0;
        for (Map.Entry<String,Integer> entry :sortedWordsToNumMap.entrySet()){
            if (i++ < 10){
                CountResultHolder.wordToNumMap.put(entry.getKey(),entry.getValue());
                outputStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }else break;
        }
        return outputStringBuilder;
    }


}
