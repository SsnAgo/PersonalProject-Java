import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Lib
{
    int sumLines = 0;   /*总行数*/
    int sumChars = 0;   /*总字符数*/
    int sumWords = 0;   /*总单词数*/
    String resultStr = "";    /*文章拼接而成的字符串*/

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

    /*统计文章行数并将文章拼接成一个字符串*/
    public void lineCount(BufferedReader br)
    {
        String temp;
        this.sumLines = 0;
        try
        {
            temp = br.readLine();
            while(temp != null)
            {
                if(temp.length() > 0)
                {
                    this.sumLines ++;
                    this.resultStr += temp;
                }
                temp = br.readLine();
                if(temp != null)
                    this.resultStr += " ";
            }
        }
        catch (IOException e)
        {
            System.err.println("行数统计输出错误！");
            e.printStackTrace();
        }

    }

    /*统计文章字符数*/
    public void charCount(BufferedReader br) throws IOException
    {
        System.out.println(this.resultStr);
        System.out.println(this.resultStr.length());

        //byte[] bytes = br.toString().getBytes();
        //String temp = br.toString();

        //this.sumChars = bytes.length;
    }

    /*统计文章单词数*/
    public  void wordCount(BufferedReader br) throws IOException
    {

    }

}
