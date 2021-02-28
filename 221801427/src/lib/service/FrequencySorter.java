package lib.service;

import java.util.*;
import java.util.stream.Collectors;

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
    public static ArrayList<HashMap.Entry<String, Long>> sortFrequency(Map<String, Long> words)
    {
        ArrayList<HashMap.Entry<String, Long>> freqList = new ArrayList<HashMap.Entry<String, Long>>(
                getFreqMap(words).entrySet());

        return freqList;
    }
}
