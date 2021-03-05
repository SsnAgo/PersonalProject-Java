import java.io.*;
import java.util.*;

public class Lib
{
    int sumLines = 0;   /*总行数*/
    int sumChars = 0;   /*总字符数*/
    int sumWords = 0;   /*总单词数*/
    String resultStr = "";    /*文章拼接而成的字符串*/
    Map<String,Integer> map = new HashMap<String, Integer>();    /*用于统计词频*/
    String sortResultStr = "";    /*用于生成最高频10个单词的字符串*/

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
        assert fr != null;
        return new BufferedReader(fr);
    }

    /*获得文章字符串*/
    public String readToString(String path)
    {
        String encoding = "UTF-8";
        File file = new File(path);
        long filelength = file.length();
        byte[] filecontent = new byte[(int) filelength];
        try
        {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
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
    public void wordCount(String path)
    {
        this.resultStr = readToString(path);
        this.resultStr = this.resultStr.replace('\r', ' ');
        this.resultStr = this.resultStr.replace('\n', ' ');
        this.resultStr = this.resultStr.replaceAll("[^A-Za-z0-9]", " ");
        this.resultStr = this.resultStr.toLowerCase();
        String[] words = this.resultStr.split(" ");    //分割获得所有单词

        this.sumWords = 0;
        for (String word : words)
        {
            if (word.length() > 3)
            {
                int j;
                for (j = 0; j < 4; j++)
                {
                    char x = word.charAt(j);
                    if (x <= 'a' || x >= 'z') break;
                }
                if (j == 4) this.sumWords++;    //此单词符合标准,计入总词数
            }
        }
    }

    /*单词类*/
    public class Word implements Comparable<Word>
    {
        String key;
        Integer value;

        public Word(String key, Integer value)
        {
            this.key = key;
            this.value = value;
        }
        @Override
        public int compareTo(Word word)
        {
            int x = word.value.intValue() - this.value.intValue();
            return (x == 0 ? this.key.compareTo(word.key) : x);
        }
    }

    /*统计词频信息*/
    public void wordFrequencyCount(String path)
    {
        this.resultStr = readToString(path);
        this.resultStr = this.resultStr.replace('\r', ' ');
        this.resultStr = this.resultStr.replace('\n', ' ');
        this.resultStr = this.resultStr.replaceAll("[^A-Za-z0-9]", " ");
        this.resultStr = this.resultStr.toLowerCase();
        String[] words = this.resultStr.split(" ");    //分割获得所有单词

        for(String word : words)
        {
            if (word.length() > 3)
            {
                int j;
                for (j = 0; j < 4; j++)
                {
                    char x = word.charAt(j);
                    if (x <= 'a' || x >= 'z') break;
                }
                if(j == 4)    //此单词符合标准
                {
                    int x = 0;
                    if(this.map.get(word) == null)  x++;
                    else  x = this.map.get(word).intValue() + 1;
                    this.map.put(word, x);

                }
            }
        }

        Set<Word> set = new TreeSet<Word>();
        for(String key : this.map.keySet())
        {
            set.add(new Word(key, this.map.get(key)));
        }
        int sum = 0;
        Iterator<Word> t = set.iterator();
        while (t.hasNext())
        {
            Word word = t.next();
            this.sortResultStr += word.key + ": " + word.value + "\n";
            sum++;
            if(sum == 10)  break;
        }
    }

    /*将结果文本输出到文件*/
    public static void outputFile(String result)
    {
        PrintWriter writer = null;    //指定路径、编码方式
        try
        {
            writer = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream("C:\\Users\\HUAWEI\\Desktop\\output.txt"), "utf-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        writer.print(result);    //写入内容不换行
        writer.close();
    }

}
