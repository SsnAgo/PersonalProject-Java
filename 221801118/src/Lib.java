import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Lib
{
    /*根据文件路径获得BufferReader*/
    public static BufferedReader readFile(String path)
    {
        FileReader fr = null;
        try
        {
            fr = new FileReader(path);
        }
        catch (FileNotFoundException e)
        {
            System.err.println("错误，文件未找到！");
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        return br;
    }

    /*统计文章行数*/
    public void lineCount(BufferedReader br, WordCount wc)
    {
        wc.sumLines = 0;
        String temp = null;
        try
        {
            temp = br.readLine();
            while(temp != null)
            {
                wc.sumLines ++;
                temp = br.readLine();
            }
        }
        catch (IOException e)
        {
            System.err.println("行数统计输出错误！");
            e.printStackTrace();
        }

    }

    /*统计文章字符数*/
    public void charCount(BufferedReader br, WordCount wc) throws IOException
    {

    }

    /*统计文章单词数*/
    public  void wordCount(BufferedReader br, WordCount wc) throws IOException
    {

    }

}
