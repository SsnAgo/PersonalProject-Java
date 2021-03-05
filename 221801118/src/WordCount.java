import java.util.Scanner;


public class WordCount
{
    public static void main(String[] args)
    {
        Lib lib = new Lib();
        //测试文本的绝对路径" C:\\Users\\HUAWEI\\Desktop\\input.txt ";
        String path;    // 文件路径
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
        lib.lineCount(path);    //lib行数属性已赋值
        lib.charCount(path);    //lib字符数属性已赋值
        lib.wordCount(path);    //lib单词数属性已赋值
        lib.wordFrequencyCount(path);    //lib词频map统计完成

        System.out.println(lib.sumLines);
        System.out.println(lib.sumChars);
        System.out.println(lib.sumWords);
        System.out.println(lib.sortResultStr);

    }
}
