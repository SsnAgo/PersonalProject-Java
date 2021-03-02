import java.io.*;
import java.util.*;
import java.util.regex.*;

/* 功能类 包含其它自定义函数 */
public class Lib
{
    private String inFileName;
    public  String outFileName;

    public Lib(String inFileName,String outFileName)
    {
        this.inFileName = inFileName;
        this.outFileName = outFileName;
    }

    public String getInFileName()
    {
        return this.inFileName;
    }

    public String getOutFileName()
    {
        return this.outFileName;
    }

    public void setInFileName(String in)
    {
        this.inFileName = in;
    }

    public void setOutFileName(String out)
    {
        this.outFileName = out;
    }

    /* 获取输入文件的Reader */
    public Reader getFileReader()
    {
        File file = new File(inFileName);
        Reader reader = null;
        try
        {
            reader = new InputStreamReader(new FileInputStream(file));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("This file doesn't exist.");
            e.printStackTrace();
        }
        return reader;
    }

    /* 获取输出文件的BufferedWriter */
    public BufferedWriter getFileWriter() throws IOException
    {
        File file = new File(outFileName);
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"utf-8"));
    }

    /* 统计文件的字符数，只需要统计Ascii码，汉字不需考虑，空格，水平制表符，换行符，均算字符 */
    public int getCharNum() throws IOException
    {
        try (Reader reader = getFileReader(); Writer writer = getFileWriter())
        {
            int charNum = 0, temp;
            while ((temp = reader.read()) != -1)
            {
                charNum++;
            }
            writer.write("characters:" + charNum + '\n');
            return charNum;
        }
    }

    /* 统计文件的单词总数，单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。*/
    public int getWordNum() throws IOException
    {

    }
}
