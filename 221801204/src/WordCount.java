import java.io.*;
/* 主程序，可以从命令行接收参数 */
public class WordCount
{
    public static void main(String[] args) throws IOException
    {
        String inputFileName = args[0];
        String outputFileName = args[1];
        Lib lib=new Lib(inputFileName,outputFileName);
        lib.getCharNum();
    }
}
