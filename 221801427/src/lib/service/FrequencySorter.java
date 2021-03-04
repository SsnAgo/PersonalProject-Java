package lib.service;

import java.util.*;
import java.util.stream.Collectors;

public class FrequencySorter
{
    public static final int MAX_SIZE = 10;

    /**
     * @param words
     * @return freqList
     */
    public static ArrayList<HashMap.Entry<String, Long>> sortFrequency(Map<String, Long> words)
    {
        words = words.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(MAX_SIZE).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal, LinkedHashMap::new));
        
        ArrayList<HashMap.Entry<String, Long>> freqList = new ArrayList<HashMap.Entry<String, Long>>(
                words.entrySet());

        return freqList;
    }
}
