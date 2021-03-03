import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.regex.*;

/* 功能类 包含其它自定义函数 */
public class Lib
{
    private String inFileName;
    private String outFileName;
    private String fileContent;//存放输入文件内容的字符串

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

    /* 获取输入文件的BufferedReader */
    public BufferedReader getFileReader()
    {
        File file = new File(inFileName);
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
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
    public void getCharNum() throws IOException
    {
        try (Reader reader = getFileReader(); Writer writer = getFileWriter())
        {
            int charNum = 0, temp;
            while ((temp = reader.read()) != -1)
            {
                charNum++;
            }
            writer.write("characters:" + charNum + '\n');
        }
    }

    /* 读取文件内容转换为字符串 */
    public void TurnFileToString() throws IOException
    {
         StringBuilder sb = new StringBuilder();
         try (BufferedReader reader = getFileReader())
         {
             String line;
             while ((line = reader.readLine()) != null)
             {
                 sb.append(line);
             }
             fileContent = sb.toString().toLowerCase();//将内容都转换成小写，方便后面统计各单词数量
         }
    }

    /* 判断一个字符串是否为单词 */
    public boolean isWord(String str)
    {
        Pattern pattern = Pattern.compile("[a-z]{4}([a-zA-Z0-9])*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches())
        {
            return true;
        }
        return false;
    }

    /* 统计文件的单词总数，单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。*/
    public void getWordNum() throws IOException
    {
        int wordNum = 0;
        TurnFileToString();
        Writer writer = getFileWriter();
        String[] wordArray = fileContent.split("[^0-9a-zA-Z]+");
        for (int i = 0;i < wordArray.length;i++)
        {
            if (isWord(wordArray[i]))
            {
                wordNum++;
            }
        }
        writer.write("words: " + wordNum + "\n");
        writer.close();
    }

    /* 统计文件的各单词的出现次数，输出频率最高的10个 */
    public void getTopWords()
    {
        Map<String, Integer> wordMap = new HashMap<>();
        Set<String> wordSet = wordMap.keySet();

    }


}
