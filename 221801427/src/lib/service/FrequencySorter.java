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
    public static ArrayList<HashMap.Entry<String, Integer>> sortFrequency(HashMap<String, Integer> words)
    {
        words = words.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(MAX_SIZE)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal, LinkedHashMap::new));
        
        ArrayList<HashMap.Entry<String, Integer>> freqList = new ArrayList<HashMap.Entry<String, Integer>>(
                words.entrySet());

        return freqList;
    }
}
