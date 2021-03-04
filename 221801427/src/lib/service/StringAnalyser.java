package lib.service;

import java.util.*;
import java.util.regex.Pattern;

public class StringAnalyser
{
    private static final String INVALID_WORD_REGEX = "[^0-9A-Za-z]";
    private static final String VALID_WORD_REGEX = "[a-z]{4}[0-9a-z]*";

    /**
     * @param content
     * @return words
     */
    public static HashMap<String, Integer> analyseString(String content)
    {        
        int cnt;
        String word;
        
        HashMap<String, Integer> words = new HashMap<String, Integer>();
        StringTokenizer tokenizer = new StringTokenizer(content.replaceAll(INVALID_WORD_REGEX, " "));
        
        while (tokenizer.hasMoreTokens())
        {
            word = tokenizer.nextToken(" ");
            if (Pattern.matches(VALID_WORD_REGEX, word))
            {
                if (words.containsKey(word))
                {
                    cnt = words.get(word);
                    words.put(word, cnt + 1);
                }
                else
                {
                    words.put(word, 1);
                }
            }
        }
        return words;
    }
}
