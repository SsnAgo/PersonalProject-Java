import java.io.*;

public class Lib
{
    int sumLines = 0;   /*总行数*/
    int sumChars = 0;   /*总字符数*/
    int sumWords = 0;   /*总单词数*/
    String resultStr = "";    /*文章拼接而成的字符串*/

    /*根据文件路径获得BufferReader*/
    public static BufferedReader getBufferedReader(String path)
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

    /*获得文章字符串*/
    public String readToString(String path)
    {
        String encoding = "UTF-8";
        File file = new File(path);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try
        {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            return new String(filecontent, encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            System.err.println(" OS不支持 " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    /*统计文章字符数*/
    public void charCount(String path)
    {
        this.resultStr = readToString(path);
        this.resultStr = this.resultStr.replace('\r', ' ');
        this.resultStr = this.resultStr.replace('\n', ' ');
        this.sumChars = this.resultStr.length();
    }

    /*统计文章行数*/
    public void lineCount(String path)
    {
        String temp;
        this.sumLines = 0;
        try
        {
            BufferedReader br = getBufferedReader(path);
            temp = br.readLine();
            while(temp != null)
            {
                if(temp.length() > 0)
                {
                    this.sumLines ++;    //统计行数
                }
                temp = br.readLine();
            }
        }
        catch (IOException e)
        {
            System.err.println("行数统计输出错误！");
            e.printStackTrace();
        }

    }

    /*统计文章单词数*/
    public  void wordCount(String path)
    {
        this.resultStr = readToString(path);
        this.resultStr = this.resultStr.replace('\r', ' ');
        this.resultStr = this.resultStr.replace('\n', ' ');
        this.resultStr = this.resultStr.replaceAll("[^A-Za-z0-9]", " ");
        this.resultStr = this.resultStr.toLowerCase();
        String[] words = this.resultStr.split(" ");    //分割获得所有单词
        int sum = words.length;
        int count = 0;
        for(int i = 0; i < sum; i++)
        {
            if(words[i].length() > 3)
            {
                int j;
                for(j = 0; j < 4; j++)
                {
                    char x = words[i].charAt(j);
                    if(x <= 'a' && x >= 'z')  break;
                }
                if(j == 4)  count++;
            }
        }
        this.sumWords = count;
    }

}
