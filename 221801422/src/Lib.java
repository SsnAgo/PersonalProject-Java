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
        //匹配正则表达式
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

    /**
     * 统计单词数 在读取单词的过程中 就存储了单词和出现的次数
     * @param str
     * @return
     */
    public static int getWordCount(String str)
    {
        //count为单词数
        int count = 0;
        //用split拆开字符串 strs字符串数组将保留拆开的单词
        String[] strs = str.split(SEPARATOR_RE);
        for(int i = 0; i < strs.length; i++)
        {
            //匹配成功单词数加1
            if(strs[i].matches(WORD_RE))
            {
                //单词不区分大小写 将单词存到Map中供排序使用
                String temp = strs[i].toLowerCase();
                if(wordsMap.containsKey(temp))
                {
                    //通过get函数获取当前单词的已出现次数
                    int num = wordsMap.get(temp);
                    wordsMap.put(temp, 1 + num);
                }
                //如果没有符合该单词的 将该单词的已出现次数定为1
                else
                {
                    wordsMap.put(temp, 1);
                }
                count++;
            }
        }
        return count;
    }

    /**
     * 比较器实现排序
     * @return
     */
    public static List<Map.Entry<String, Integer>> sortHashmap()
    {
        List<Map.Entry<String, Integer>> list;
        list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());
        //通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> m1, Entry<String, Integer> m2)
            {
                //按照字典序和value排序
                if(m1.getValue().equals(m2.getValue()))
                {
                    return m1.getKey().compareTo(m2.getKey());
                }
                else return m2.getValue()-m1.getValue();
            }
        });
        return list;
    }

    /**
     * 数据输出到文件
     * @param characters
     * @param words
     * @param lines
     * @param filePath
     */
    public static void writeToFile(int characters, int words, int lines, String filePath)
    {
        String str = "characters: " + characters + "\nwords: " + words + "\nlines: " + lines + "\n";
        List<Map.Entry<String, Integer>> list = sortHashmap();
        int i = 0;
        for(Map.Entry<String, Integer> map : list) 
        {
            if(i < 10)
            {
                str += map.getKey() + ": " + map.getValue() + "\n";
                i++;
            }
            else break;
        }

        //输出流
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;

        try
        {
            fos = new FileOutputStream(filePath);
            //指定编码表UTF-8
            writer = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(str);
            bw.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
                fos.close();
                writer.close();
                bw.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
