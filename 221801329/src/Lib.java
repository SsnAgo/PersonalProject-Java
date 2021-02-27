import javafx.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    private static String FLITER_REGEX = "[^a-zA-Z0-9]";
    private static String WORD_REGEX = "[a-z]{4}[a-z0-9]*";
    public static int countChars(String str) {
        int count = 0;
        char[] c = str.toCharArray();
        for (int i = 0;i<c.length;i++) {
            if (c[i]<=127) {
                //if (c[i]==9 || c[i]==10 || c[i]==13) System.out.println((int)c[i]);
                count++;
            }
        }
        return count;
    }
    public static int countWords(String line,HashMap<String,Integer> wordMap) {
        line = line.replaceAll(FLITER_REGEX," ").toLowerCase();
        int cnt = 0;
        StringTokenizer tmpWords = new StringTokenizer(line);
        while (tmpWords.hasMoreTokens()) {
            String word = tmpWords.nextToken();
            if (Pattern.matches(WORD_REGEX, word)) {
                cnt++;
                if (wordMap.containsKey(word)) {
                    wordMap.put(word, wordMap.get(word) + 1);
                } else {
                    wordMap.put(word, 1);
                }
            }
        }
        return cnt;
    }
    public static List<HashMap.Entry<String, Integer>> getSortedList(HashMap<String,Integer> wordMap) {
        List<HashMap.Entry<String, Integer>> wordList = new ArrayList<>(wordMap.entrySet());
        Comparator<Map.Entry<String, Integer>> cmp = (o1, o2) -> {
            if(o1.getValue().equals(o2.getValue()))
                return o1.getKey().compareTo(o2.getKey());
            return o2.getValue()-o1.getValue();
        };
        wordList.sort(cmp);
        return wordList;
    }
}