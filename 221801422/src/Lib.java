import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lib
{
    Lib lib = new Lib();
    //单词正则表达式
    private static String WORD_RE = "[A-Za-z]";
    //分隔符正则表达式
    private static String SEPARATOR_RE = "[^A-Za-z0-9]";
    //有效行数正则表达式
    private static String LINE_RE = "(^|\n)\\s*\\S+";
    private static String DIR = System.getProperty("user.dir");
    private static Map<String, Integer> wordsMap = new HashMap<String, Integer>();

    /**
     * 文件读取数据
     * @param filePath
     * @return 
     */
    public static String readFromFile(String filePath) 
    {
        int temp;
        BufferedReader br = null;
        StringBuilder builder = null;
        try
        {
            br = new BufferedReader(new FileReader(filePath));
            builder = new StringBuilder();
            //用read()可以按字符读入数据
            while((temp = br.read()) != -1)
            {
                builder.append((char)temp);
            }
        }
        //文件不存在或目录不存在
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e) 
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                br.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    /**
     * 统计有效行数
     * @param str
     * @return count
     */
    public static int getLineCount(String str)
    {
        int count = 0;
        Matcher matcher = Pattern.compile(LINE_RE).matcher(str);
        while(matcher.find())
        {
            count++;
        }
        return count;
    }

    /**
     * 统计字符数 判断是否为ASCII码
     * @param str
     * @return 
     */
    public static int getCharCount(String str)
    {
        int count = 0;
        char[] ch = str.toCharArray();
        for(int i = 0; i < ch.length; i++)
        {
            if(ch[i] >= 0 && ch[i] <= 127)
            {
                count++;
            }
        }
        return count;
    }
}
