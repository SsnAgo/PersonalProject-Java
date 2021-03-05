import java.io.*;
import java.util.Scanner;


public class WordCount
{
    public static void main(String[] args) throws IOException
    {
        Lib lib = new Lib();
        //测试文本的绝对路径" C:\\Users\\HUAWEI\\Desktop\\input.txt ";
        String path = null;    // 文件路径
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
        BufferedReader br = lib.getBufferedReader(path);
        lib.resultStr = lib.readToString(path).toString();    //获得文章字符串
        lib.lineCount(br);
        lib.charCount();


        System.out.println(lib.sumLines);
        System.out.println(lib.sumChars);

    }
}
