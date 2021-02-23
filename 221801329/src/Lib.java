import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    private static String WORD_REGEX = "[a-z]{4}[a-z0-9]*";
    private static String FLITER_REGEX = "[^a-zA-Z0-9]";
    private static String CHINESE_REGEX = "[\\u4e00-\\u9fa5]";
    //TODO:文件路径可以优化
    public static String DIR = System.getProperty("user.dir");
    public static HashMap<String,Integer> wordMap = new HashMap<>();
    /*
    * @description 将文件读到 StringBuffer中
    * @param stringBuffer filePath
    * @return
    * */
    public static void readToBuffer(StringBuffer stringBuffer,String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        while (line != null) {
            stringBuffer.append(line);
            stringBuffer.append("\n");
            line = reader.readLine();
        }
        reader.close();
        is.close();
    }
    /*
    * @description 将读取过文件的stringBuffer转换为string
    * @param filePath
    * @return String
    * */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString();
    }
    /*
    * @description 判断字符串中是否有中文
    * @param str
    * @return true or false
    * */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile(CHINESE_REGEX);
        Matcher m = p.matcher(str);
        return m.find();
    }
    /*
    * @description 判断字符是否为中文字符
    * @param c
    * @return true of false
    * */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }
    /*
    * @description 过滤字符串中的中文字符
    * @param str
    * @return String
    * */
    public static String filterChinese(String str) {
        String result = str;
        boolean flag = isContainChinese(str);
        if (flag) {
            StringBuffer sb = new StringBuffer();
            boolean flag2 = false;
            char chinese = 0;
            char[] charArray = str.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                chinese = charArray[i];
                flag2 = isChinese(chinese);
                if (!flag2) {
                    sb.append(chinese);
                }
            }
            result = sb.toString();
        }
        return result;
    }
    /*
    * @description 用于删除字符串中含有中文字符的单词
    * @param str
    * @return String
    * */
    public static String deleteChineseString(String str) {
        StringBuffer sb = new StringBuffer();
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()){
            String word = stringTokenizer.nextToken();
            if (!isContainChinese(word)){
                sb.append(word);
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    /*
    * @description 统计文件行数
    * @param file
    * @return count
    * */
    public static int countLines(File file) throws FileNotFoundException {
        int count = 0;
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (!"".equals(str)) {
                count++;
            }
        }
        return count;
    }
    /*
    * TODO:单词个数统计
    * @description 将合法单词存入map中
    * @param wordStr
    * @return map
    * */
    public static HashMap<String,Integer> makeWordMap(String str) {
        int count = 0;
        str = str.toLowerCase().replaceAll(FLITER_REGEX," ");
        StringTokenizer words = new StringTokenizer(str);
        while (words.hasMoreTokens()) {
            String word = words.nextToken();
            if (Pattern.matches(WORD_REGEX, word) && !isContainChinese(word)) {
                count++;
                if (wordMap.containsKey(word)) {
                    wordMap.put(word,wordMap.get(word)+1);
                } else {
                    wordMap.put(word, 1);
                }
            }
        }
        wordMap.put("_cnt",count);
        return wordMap;
    }
    /*
    * @description 判断字符串中的合法ASCII码数量
    * @param str
    * @return count
    * */
    public static int countChars(String str) {
        int count = 0;
        char[] c = str.toCharArray();
        for (int i = 0;i<c.length;i++) {
            if ((32 <= c[i] && c[i] <= 126) || c[i] == 9 || c[i] == 10) {
                count++;
            }
        }
        return count;
    }
}
