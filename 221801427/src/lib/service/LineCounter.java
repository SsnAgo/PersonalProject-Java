package lib.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineCounter
{
    private static String Valid_Line_REGEX = "(^|\n)\\s*\\S+";
    private static Pattern ValidLinePattern = Pattern.compile(Valid_Line_REGEX);

    /**
     * @param content
     * @return 有效行数
     */
    public static int countLine(String content)
    {
        int cnt = 0;
        Matcher matcher;

        matcher = ValidLinePattern.matcher(content);
        while (matcher.find())
        {
            cnt++;
        }
        return cnt;
    }
}
