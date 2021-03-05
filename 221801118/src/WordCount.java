import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
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
        BufferedReader br = null;
        br = lib.readFile(path);

        lib.lineCount(br);
        System.out.println("文本一共有" + lib.sumLines + "行");

    }



}
