import java.io.*;

/**
 * 功能：WordCount中main方法要调用的函数
 * 作者：张福荣
 * 学号：221801315
 * 邮箱：784536133@qq.com
 * 创建时间：2021/2/28 15:22
 * 最后修改时间：2021/2/28 20:21
 */
public class Lib {
    /* 检测所给文件路径是否有效，输入文件不存在则抛出异常，输出文件不存在则创建
       输入参数：输入文件路径inFilePath，输出文件路径outFilePath
       返回值：空
       异常：输入文件不存在异常FileNotFoundException*/
    public static void checkFileValid(String inFilePath, String outFilePath) throws FileNotFoundException {
        File inFile = new File(inFilePath);
        //当输入文件不存在时，打印提示信息，抛出异常
        if (!inFile.exists()) {
            System.out.println("File " + inFilePath + " doesn't exist, program will exit.");
            throw new FileNotFoundException();
        }

        File outFile = new File(outFilePath);
        //当输出文件不存在时，创建文件
        if (!outFile.exists()) {
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* 统计输入文件中的字符总数
       输入参数：文件读取流in
       返回值：字符总数count */
    public static int countTotalChar(Reader in) {
        int count = 0;    //记录字符总数
        try {
            int temp = in.read();  //记录读取的字符
            //读取文件，直到文件结束
            while (temp != -1) {
                if (temp >= 0 && temp <= 127)
                    ++count;
                temp = in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}
