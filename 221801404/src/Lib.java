import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib
{
    static Map<String,Integer> wordsMap=new HashMap<>();
    //获取单词数
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
    static int getWordsNum(String str){

        int WordsNum=0;
        String[] temp=str.split("\\s+");
        String regexs="^[a-zA-Z]{4,}.*";

        for(int i=0;i<temp.length;i++)
        {
            if(temp[i].matches(regexs)){
                WordsNum++;
                String insertKey=temp[i].toLowerCase();
                if (wordsMap.containsKey(insertKey)){
                    int j=wordsMap.get(insertKey);
                    wordsMap.put(insertKey,j+1);
                }else {
                    wordsMap.put(insertKey,1);
                }
            }
        }

        return WordsNum;
    }


    //对单词进行排序
}