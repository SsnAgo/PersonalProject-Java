package lib.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter
{
    /**
     * @param content
     * @return µ¥´Ê×ÜÊý
     */
    public static int countWord(String content)
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
                        return false;
                }
            }
            else
            {
                return false;
            }
            return true;
        })
        .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        return words.size();
    }
}
