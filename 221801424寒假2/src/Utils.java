import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供一个工具类
 */
public class Utils {

    /**
     * 定义返回参数
     * 1.单词数
     * 2.字符数
     * 3.行数
     * 4.key保存单词，value存储该单词出现的次数
     * 5.存储文件读入信息
     */
    private static int word_num = 0;
    private static int char_num = 0;
    private static int line_num = 0;
    private static HashMap<String, Integer> map = new HashMap<>();
    private static StringBuilder stringBuilder = new StringBuilder();


    /**
     * 1.根据正则表达式来统计字符数
     *
     * @param characters
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static int charNums(String characters) {
        // 通过正则表达式来匹配
        String regexs = "\\p{ASCII}";
        Pattern pattern = Pattern.compile(regexs);
        Matcher matcher = pattern.matcher(characters);
        while (matcher.find()) {
            // 找到一个字母，累加
            char_num++;
        }
        // 匹配完毕，返回结果
        return char_num;
    }

    /**
     * 2.根据正则表达式来统计单词数
     *
     * @param words
     * @return
     */
    @SuppressWarnings("JavaDoc")
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
     *
     * @param path
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static int lineNums(String path) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(new File("").getAbsolutePath() + "\\" + path);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            // 通过循环不断整行读取文件
            // 同时记录读取次数即可
            while ((line = bufferedReader.readLine()) != null) {
                //匹配任意非空白字符
                if (line.length() != 0 && !line.matches("\\s+")) {
                    line_num++;
                }
            }
        } catch (IOException e) {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            e.printStackTrace();
        }
        return line_num;
    }


    /**
     * 4.通过map来统计单词的出现次数
     *
     * @param words
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static List mapNums(String words) {
        //利用空格来分隔单词，并利用正则表达式来匹配正确的单词
        String[] temps = words.split("\\s+");
        String regexs = "^[a-zA-Z]{4,}.*";
        for (String i : temps) {
            if (i.matches(regexs)) {
                // map中不存在则新纪录
                if (!map.containsKey(i.toLowerCase())) {
                    map.put(i.toLowerCase(), 1);
                } else {
                    // 如果存在则num+1
                    int num = map.get(i.toLowerCase());
                    map.put(i.toLowerCase(), num + 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        // 构造匿名内部类
        // 首先根据频率比较，如果频率相同，比较字典顺序
        list.sort((Comparator<Map.Entry>) (o1, o2)
                -> ((Integer) o1.getValue()).compareTo((Integer) o2.getValue()) != 0
                ? ((Integer) o2.getValue()).compareTo((Integer) o1.getValue()) : ((String) o1.getKey()).compareTo((String) o2.getKey()));
        // 返回list 前十个 数据，也即出现的前十的高频词
        return list.size() < 10 ? list.subList(0, list.size()) : list.subList(0, 10);
    }

    /**
     * 5.用于读取文件所有字符
     *
     * @param path
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static StringBuilder readIn(String path) {
        //测试文件放在当前项目下
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(new File("").getAbsolutePath() + "\\" + path));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("文件不存在");
            e.printStackTrace();
        }
        return stringBuilder;
    }


    /**
     * 6.写入信息
     *
     * @param path
     * @param message
     */
    @SuppressWarnings("JavaDoc")
    public static void writeTo(String path, String message) {
        try {
            //生成的文件放在当前项目下
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(new File("").getAbsolutePath() + "\\" + path));
            bufferedWriter.write(message);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long getTime() {
        Date newDate = new Date();
        return newDate.getTime();
    }

//    /**
//     * 测试
//     *
//     * @param args
//     */
// @SuppressWarnings("JavaDoc")
// public static void main(String[] args) {
//        //System.out.println(args[0]);
//        System.out.println(Utils.wordNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
//        System.out.println(Utils.charNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
//        System.out.println(Utils.mapNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
//        System.out.println(Utils.lineNums(new File("").getAbsolutePath()+"\\1.txt"));
//        System.out.println(new File("").getAbsolutePath());
//        Utils.writeTo("2.txt","hello world");
//        }
}
