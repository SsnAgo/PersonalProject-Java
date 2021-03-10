import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 统计工具类
 */
class Lib {
    /**
     * @description 存放单词与其对应出现的次数
     */
    static private HashMap<String, Integer> wordMap = null;

    /**
     * @param str 文件字符串
     * @return 字符数
     * @description 统计字符数
     */
    static int countCharNum(String str) {
        return str.length();
    }

    /**
     * @param str 文件字符串
     * @return 有效行数
     * @description 统计空白行数
     */
    static int countValidLineNum(String str) {
        int lineNum = 0;
        Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");
        Matcher matcher = linePattern.matcher(str);

        while (matcher.find())
            lineNum++;

        return lineNum;
    }

    /**
     * @param str 文件字符串
     * @return 单词总数
     * @description 统计单词总数, 并统计单词对应个数
     */
    static int countWordNum(String str) {
        int wordNum = 0;
        str = str.toLowerCase();
        String[] words = str.split("[^a-z0-9]+");
        wordMap = new HashMap<>();

        for (String word : words) {
            if (word.matches("[a-z]{4,}[a-z0-9]*")) {
                wordNum++;
                if (wordMap.containsKey(word)) {
                    int n = wordMap.get(word);
                    wordMap.put(word, n + 1);
                } else {
                    wordMap.put(word, 1);
                }
            }
        }

        return wordNum;
    }

    /**
     * @return 排序后的wordMapList
     * @description 对wordMap中的单词频率排序
     */
    static List<HashMap.Entry<String, Integer>> sortWordMap() {
        List<HashMap.Entry<String, Integer>> wordMapList = new ArrayList<>(wordMap.entrySet());

        Collections.sort(wordMapList, new Comparator<HashMap.Entry<String, Integer>>() {
            @Override
            public int compare(HashMap.Entry<String, Integer> word1, HashMap.Entry<String, Integer> word2) {
                if (word1.getValue().equals(word2.getValue())) {
                    return word1.getKey().compareTo(word2.getKey());
                } else {
                    return word2.getValue() - word1.getValue();
                }
            }
        });

        return wordMapList;
    }
}