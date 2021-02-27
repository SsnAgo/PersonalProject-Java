package lib.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrequencySorter
{
    public static final int MAX_SIZE = 10;

    /**
     * @param words
     * @return freqMap
     */
    public static Map<String, Long> getFreqMap(Map<String, Long> words)
    {
        return words.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(MAX_SIZE).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal, LinkedHashMap::new));
    }

    /**
     * @param content
     * @return freqList
     */
    public static ArrayList<HashMap.Entry<String, Long>> sortFrequency(String content)
    {
        List<String> lines = Arrays.asList(content);
        Map<String, Long> words = lines.stream().flatMap(w -> Stream.of(w.split("\\|"))).filter(w ->
        {
            int i = 0;
            char[] chars = w.toCharArray();
            if (w.length() >= 4)
            {
                for (; i < 4; i++)
                {
                    if (!Character.isLetter(chars[i]))
                    {
                        return false;
                    }
                }
            }
            else
            {
                return false;
            }
            return true;
        }).collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        ArrayList<HashMap.Entry<String, Long>> freqList = new ArrayList<HashMap.Entry<String, Long>>(
                getFreqMap(words).entrySet());

        return freqList;
    }
}
