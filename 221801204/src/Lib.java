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
    private ArrayList<String> wordList;//存放文件中筛选出的有效单词

    public Lib(String inFileName,String outFileName)
    {
        this.inFileName = inFileName;
        this.outFileName = outFileName;
        wordList = new ArrayList<>();
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

    /* 总函数，可一键完成所有功能 */
    public void getAll() throws IOException
    {
        getCharNum();
        getWordNum();
        getLineNum();
        getTopWords();
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
            writer.write("characters: " + charNum + '\n');
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
                 sb.append(" ");
             }
             fileContent = sb.toString().toLowerCase();//将内容都转换成小写，方便后面统计各单词数量
         }
    }

    /* 判断一个字符串是否为单词 */
    public boolean isWord(String str)
    {
        Pattern pattern = Pattern.compile("[a-z]{4}([a-z0-9])*");
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
        try(Writer writer = getFileWriter())
        {
            String[] toJudgeWords = fileContent.split("[^0-9a-zA-Z]+");//存放分隔开后的各个字符串，等待判断是否为单词
            for (int i = 0; i < toJudgeWords.length; i++) {
                if (isWord(toJudgeWords[i])) {
                    wordList.add(toJudgeWords[i]);//将符合条件的单词放入单词列表中，便于接下来统计词频
                    wordNum++;
                }
            }
            writer.write("words: " + wordNum + "\n");
        }
    }

    /* 统计文件的各单词的出现次数，输出频率最高的10个 */
    public void getTopWords() throws IOException
    {
        Map<String, Integer> wordMap = new HashMap<>();
        Set<String> wordSet = wordMap.keySet();
        for (int i = 0;i < wordList.size();i++)
        {
            String str = wordList.get(i);
            if (wordSet.contains(str))//假如该单词在map里已录入,直接更新它对应的value,否则录入map
            {
                int num = wordMap.get(str) + 1;
                wordMap.put(str,num);
            }
            else wordMap.put(str,1);
        }
        /* 录入之后排序，频率相同的单词，优先输出字典序靠前的单词 */
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2)
            {
                if (e1.getValue().equals(e2.getValue()))
                {
                    return e1.getKey().compareTo(e2.getKey());
                }
                else return e2.getValue().compareTo(e1.getValue());
            }
        });
        /* 输出 */
        try (Writer writer = getFileWriter())
        {
            int cnt = 0;
            for (Map.Entry<String, Integer> entry : entryList)
            {
                if (cnt == 10) break;
                String word = entry.getKey();
                Integer number = entry.getValue();
                writer.write(word + ": " + number + "\n");
                cnt++;
            }
        }
    }

    /* 统计文件的有效行数，任何包含非空白字符的行，都需要统计，空白字符包括空格，\r,\t,\n */
    public void getLineNum() throws IOException
    {
        try(BufferedReader reader = getFileReader();Writer writer = getFileWriter())
        {
            int lineNum = 0;
            String temp;
            while ((temp = reader.readLine()) != null)
            {
                if (!temp.trim().equals(""))
                {
                    lineNum++;
                }
            }
            writer.write("lines: " + lineNum + "\n");
        }
    }


}
