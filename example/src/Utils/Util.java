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
    private static int line_num = 0;
    /**
     * 1.根据正则表达式来统计字符数
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
     * 2.根据正则表达式来统计单词数
     * @param words
     * @return
     */
    public static int wordNums(String words) {
        // 构造正则表达式，去根据空格拆分整篇文章
        // temp字符串数组将保存所有的单词

        String[] temps = words.split("\\s+");

        // 构造题意：以字母开头且长度大于4的单词
        String regexs = "^[a-zA-Z]{4,}.*";

        // 循环遍历这个数组，利用正则表达式去匹配
        for (String i : temps) {
            if (i.matches(regexs)) {
                // 匹配成功，计数加1
                word_num++;
            }
        }
        return word_num;
    }


    /**
     * 3.根据整行读取实现获取行数
     * @param path
     * @return
     */
    public static int linesNumber(String path) {

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            // 通过循环不断整行读取文件
            // 同时记录读取次数即可
            while ((line = bufferedReader.readLine().strip(' ')) != null) {
                //匹配任意非空白字符
                if (line.length() != 0 && !line.matches("\\s+")) {
                    line_num++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line_num;
    }
}
