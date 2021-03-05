import java.util.Scanner;


public class WordCount
{
    public static void main(String[] args)
    {
        Lib lib = new Lib();
        //测试文本的绝对路径" C:\\Users\\HUAWEI\\Desktop\\input.txt ";
        String path;    // 文件路径
        String result;    //最终输出
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
        lib.lineCount(path);    //lib行数属性已赋值
        lib.charCount(path);    //lib字符数属性已赋值
        lib.wordCount(path);    //lib单词数属性已赋值
        lib.wordFrequencyCount(path);    //lib词频map统计完成

        result = "characters: " + lib.sumChars + "\n";
        result += "words: " + lib.sumWords + "\n";
        result += "lines: " + lib.sumLines + "\n";
        result += lib.sortResultStr;

        System.out.println(result);

    }
}
