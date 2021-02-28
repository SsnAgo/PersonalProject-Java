package lib.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringAnalyser
{
    private static String INVALID_WORD_REGEX = "[^0-9A-Za-z]";

    /**
     * @param content
     * @return words
     */
    public static Map<String, Long> analyseString(String content)
    {
        List<String> list = Arrays.asList(content);
        Map<String, Long> words = list.stream().flatMap(w -> Stream.of(w.split(INVALID_WORD_REGEX))).filter(w ->
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
        return words;
    }
}
