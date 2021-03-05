import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;


public class WordCount
{

    int sumLines = 0;   /*总行数*/
    int sumChars = 0;   /*总字符数*/
    int sumWords = 0;   /*总单词数*/


    public static void main(String[] args) throws IOException
    {
        WordCount wc = new WordCount();
        Lib lib = new Lib();
        //测试文本的绝对路径" C:\\Users\\HUAWEI\\Desktop\\test.txt ";
        String path = null;    // 文件路径
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
        BufferedReader br = null;
        br = lib.readFile(path);

        lib.lineCount(br, wc);
        System.out.println("文本一共有" + wc.sumLines + "行");

    }



}
