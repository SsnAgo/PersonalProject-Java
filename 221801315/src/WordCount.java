import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 功能：统计用户搜索记录
 * 作者：张**
 * 学号：221801315
 * 邮箱：****@***.com
 * 创建时间：2021/2/28 15:20
 * 最后修改时间：2021/3/5 3:04
 */
public class WordCount {
    public static void main(String[] args) {
        String inFilePath;
        String outFilePath;
        //获取文件路径，没有输入文件路径和输出文件路径则报错，并退出程序
        if (args.length != 2) {
            inFilePath = "";
            outFilePath = "";
        } else {
            inFilePath = args[0];
            outFilePath = args[1];
        }
        while (inFilePath.equals("") || outFilePath.equals("")) {
            System.out.println("Please enter 2 files' path!!!");
            System.out.print("InputFile:");

            Scanner in = new Scanner(System.in);
            inFilePath = in.nextLine();
            System.out.print("outputFile:");
            outFilePath = in.nextLine();
        }

        inFilePath = Lib.checkFileValid(inFilePath, outFilePath);
        Lib.writeToOutFile(inFilePath, outFilePath);
    }
}