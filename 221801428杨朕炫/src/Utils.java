import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    /**
     * 获取输入文件的行字符串集合
     *
     * @param filename 输入文件名
     * @return
     * @throws IOException
     */
    public static List<String> getLineList(String filename) throws IOException {
        Path inputFilePath = Path.of(filename);
        Stream<String> linesStream = Files.lines(inputFilePath);
        return linesStream.collect(Collectors.toList());
    }

    /**
     * 根据字典顺序排序两个字符串
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int compareStringByDict(String str1, String str2) {
        for (int i = 0, j = 0; i < str1.length() && j < str2.length(); i++, j++) {
            if (str1.charAt(i) >= str2.charAt(i)) {
                return 1;
            } else if (str1.charAt(i) < str2.charAt(i)) {
                return -1;
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
     * 根据value或字典顺序排序映射表
     *
     * @param wordsToNumMap 单词-单词个数映射表
     * @return
     */
    public static Map<String, Integer> sortMapByNum(Map<String, Integer> wordsToNumMap) {
        HashMap<String, Integer> sortedWordsToNumMap = new LinkedHashMap<>();
        wordsToNumMap.entrySet()
                .stream()
                .sorted((p1, p2) -> {
                    if (p1 == null && p2 == null) {
                        return 0;
                    }
                    if (p1 == null) {
                        return 1;
                    }
                    if (p2 == null) {
                        return -1;
                    }
                    // 单词数不一样则按单词数目排序
                    if (!p1.getValue().equals(p2.getValue())) {
                        return p2.getValue().compareTo(p1.getValue());
                    } else {
                        return compareStringByDict(p1.getKey().trim(), p2.getKey().trim());
                    }
                })
                .collect(Collectors.toList())
                .forEach(
                        ele ->
                                sortedWordsToNumMap.put(ele.getKey(), ele.getValue())
                );
        return sortedWordsToNumMap;
    }


    /**
     * 生成输出数据
     *
     * @param sortedWordsToNumMap 已排序的映射表
     * @return
     */
    public static StringBuilder generateOutputString(Map<String, Integer> sortedWordsToNumMap) {
        StringBuilder outputStringBuilder = new StringBuilder();
        outputStringBuilder.append(String.format("characters: %s\nwords: %s\nlines: %s\n",
                CountResultHolder.getCharactersCount(),
                CountResultHolder.getWordsCount(),
                CountResultHolder.getLinesCount()));
        // 限制最多输出10个
        int i = 0;
        for (Map.Entry<String, Integer> entry : sortedWordsToNumMap.entrySet()) {
            if (i++ < 10) {
                CountResultHolder.putIntoMap(entry.getKey(), entry.getValue());
                outputStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            } else break;
        }
        return outputStringBuilder;
    }


}
