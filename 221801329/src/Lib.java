import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Lib {
    private static String WORD_REGEX = "[a-z]{4}[a-z0-9]*";
    private static String FLITER_REGEX = "[^a-zA-Z0-9]";
    //TODO:文件路径可以优化
    public static String DIR = System.getProperty("user.dir");
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
    * TODO:正则表达式判断有问题 需要进一步修改
    * @description 统计单词个数
    * @param wordStr
    * @return count
    * */
    public static int countWords(String wordStr) {
        int count = 0;
        wordStr = wordStr.toLowerCase().replaceAll(FLITER_REGEX," ");
        StringTokenizer words = new StringTokenizer(wordStr);
        while (words.hasMoreTokens()) {
            String word = words.nextToken();
            if (Pattern.matches(WORD_REGEX, word)) {
                count++;
            }
        }
        return count;
    }
}
