import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib
{
    private int wordNum;
    private HashMap<String,Integer> map;
    //获取单词数
    public int getWordNum()
    {
        return wordNum;
    }
    //从文件中读取数据
    public static String readFile(String filePath)
    {
        int flag;
        StringBuffer content = new StringBuffer();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            //将字符一个个读入content
            while((flag=br.read()) != -1)
            {
                content.append((char)flag);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

    //获取文件中的字符数
    public static int getCharacterNum(String str)
    {
        int charactersNum = 0;
        char[] a = str.toCharArray();
        for(int i = 0;i < str.length();i++)
        {
            if(a[i] >= 0&&a[i] <= 127)
            {
                charactersNum++;
            }
            else
            {
                continue;
            }
        }
        return charactersNum;
    }
    //正则表达式统计行数
    public static int getLinesNum(String str)
    {
        int linesNum = 0;
        Matcher matcher = Pattern.compile("(^|\n)\\s*\\S+").matcher(str);
        while(matcher.find())
        {
            linesNum++;
        }
        return linesNum;
    }
    //统计单词
    public void getWordNum(String string)
    {
        String temp;
        int wordNum = 0;
        String[] content = string.split("[^a-zA-Z0-9]");
        for (String element:content)
        {
            temp = element.toLowerCase();
            if (temp.matches("[a-zA-Z]{4}[a-zA-Z0-9]*"))
            {
                map.merge(temp, 1, Integer::sum);
                wordNum++;
            }
        }
        System.out.println(wordNum);
    }
}