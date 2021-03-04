package lib.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineCounter
{
    private static final String VALID_LINE_REGEX = "(^|\n)\\s*\\S+";
    private static final Pattern VALID_LINE_PATTERN = Pattern.compile(VALID_LINE_REGEX);

    /**
     * @param content
     * @return 有效行数
     */
    public static int countLine(String content)
    {
        int cnt = 0;
        Matcher matcher;

        matcher = VALID_LINE_PATTERN.matcher(content);
        while (matcher.find())
        {
            cnt++;
        }
        return cnt;
    }
}
