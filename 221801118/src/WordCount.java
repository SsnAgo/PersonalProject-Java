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
        lib.lineCount(path);    //lib行数属性已赋值
        lib.charCount(path);    //lib字符数属性已赋值
        lib.wordCount(path);    //lib单词数属性已赋值

        System.out.println(lib.sumLines);
        System.out.println(lib.sumChars);
        System.out.println(lib.sumWords);

    }
}
