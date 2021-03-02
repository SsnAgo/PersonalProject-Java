import java.io.FileNotFoundException;

/**
 * 功能：统计用户搜索记录
 * 作者：张福荣
 * 学号：221801315
 * 邮箱：784536133@qq.com
 * 创建时间：2021/2/28 15:20
 * 最后修改时间：2021/2/28 20:21
 */
public class WordCount {
    public static void main(String[] args) {
        //获取文件路径，没有输入文件路径和输出文件路径则报错，并退出程序
        if (args.length != 2) {
            System.out.println("Please enter 2 files' path!!!");
            System.exit(-1);
        }
        String inFilePath = args[0];
        String outFilePath = args[1];

        try {
            Lib.checkFileValid(inFilePath, outFilePath);
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }

        Lib.writeToOutFile(inFilePath, outFilePath);
    }
}