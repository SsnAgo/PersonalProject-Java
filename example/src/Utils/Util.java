package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供一个工具类
 */
public class Util {


    private static int word_num = 0;
    private static int char_num = 0;

    /**
     * 根据正则表达式来统计字符数
     *
     * @param character
     * @return
     */
    public static int charNums(String character) {
        // 编写正则表达式查询规则
        String regexs = "\\p{ASCII}";
        Pattern pattern = Pattern.compile(regexs);
        Matcher matcher = pattern.matcher(character);
        // 定义一个Integer去存储字母数目
        while (matcher.find()) {
            // 找到一个字母，累加
            char_num++;
        }
        // 匹配完毕，返回结果
        return char_num;
    }

    
}
