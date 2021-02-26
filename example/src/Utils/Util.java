package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供一个工具类
 */
public class Util {

    /**
     * 定义返回参数
     */
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

    /**
     * 根据正则表达式来统计单词数
     *
     * @param words
     * @return
     */
    public static int wordNums(String words) {
        // 构造正则表达式，去根据空格拆分整篇文章
        // temp字符串数组将保存所有的单词
        
        String[] temp = words.split("\\s+");

        // 根据题意
        // 构造正则表达式筛选规则：以字母开头且长度大于4的单词
        String regexs = "^[a-zA-Z]{4,}.*";

        // 循环遍历这个数组，利用正则表达式去匹配
        for (String i : temp) {
            if (i.matches(regexs)) {
                // 匹配成功，计数加1
                word_num++;
            }
        }
        return word_num;
    }

}
