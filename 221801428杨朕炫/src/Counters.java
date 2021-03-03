import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Counters {
    /**
     * calculate the number of characters in input file
     *
     * @param filename input file name
     */
    public static void countCharacters(String filename) throws IOException, RuntimeException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filename);
            int c;
            boolean hasContent = false;
            while ((c = fileReader.read()) != -1) {
                CountResultHolder.charactersCount++;
                if (!hasContent) hasContent = true;
            }
            if (!hasContent) {
                throw new RuntimeException("No content found!");
            }
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * calculate the number of valid lines(expects lines that just contain space) in input file
     *
     * @param list line list
     */
    public static void countLines(List<String> list) {
        list.forEach(line -> {
            // 空行不算有效行
            if (!line.trim().equals("")) {
                CountResultHolder.linesCount++;
            }
        });
    }

    /**
     * calculate the count of words and put valid words into HashMap
     *
     * @param list line list
     * @return
     */
    public static Map<String, Integer> countWordsAndTransform(List<String> list) {

        Map<String, Integer> wordToNumMap = new HashMap<>();
        // 正则表达式匹配四个英文开头的有效单词
        Pattern pattern = Pattern.compile("[A-Za-z]{4}.*?");
        list.forEach(line -> {
            String[] strings = line.split("\\s+|\\t");
            for (String word : strings) {
                Matcher matcher = pattern.matcher(word);
                // 计数有效单词并将其加入映射集合
                if (matcher.matches()) {
                    CountResultHolder.wordsCount++;
                    wordToNumMap.merge(word, 1, Integer::sum);
                }
            }
        });
        return wordToNumMap;
    }
}
