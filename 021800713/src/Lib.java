import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Lib {

    //读取输入文件
    public static Reader openInputFile(String fileName) {
    }

    //获得输出流
    public static BufferedWriter openOutputFile(String fileName) throws IOException {
    }

    //判断有效字符（A-Z,a-z,0-9）
    public static boolean isValidChar(int temp) {
        if ((temp >= 97 && temp <= 122) || (temp >= 65 && temp <= 90) || (temp >= 48 && temp <= 57)) {
            return true;
        } else {
            return false;
        }
    }
    //判断是否为有效的单词
    public static boolean isValidChars(char[] chars) {
    }

    //统计字符数
    public static int charactersCount(String inputFile, String outputFile) throws IOException {
    }

    //统计单词总数,至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写
    public static int wordsCount(String inputFile, String outputFile) throws IOException {

    }

    //统计行数(任何包含非空白字符的行)
    public static int linesCount(String inputFile, String outputFile) throws IOException {

    }

    //统计单词的出现次数,最终只输出频率最高的10个。
    public static Map wordNum(String inputFile, String outputFile) throws IOException {

    }

    //打印出频率前十的单词
    public static void printWords(Map<String, Integer> map, Writer writer) throws IOException {
    }

    

}
