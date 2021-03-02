import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputeTool {

    private String TargetString;
    private ArrayList<String> Rows;
    private ArrayList<String> ValidRows;
    private HashMap<String, Integer> ValidWords;

    /**
     * @description      构造函数
     */
    public ComputeTool(String TargetString)
    {
        this.TargetString=TargetString;
        Rows =new ArrayList<String>(Arrays.asList(TargetString.split("\n")));
        ValidRows =new ArrayList<String>();

        for (String Row:Rows)
        {
            if(!(Row.trim().isEmpty()))
                ValidRows.add(Row);//将有效行加入集合
        }

    }


    /**
     * @description      统计文件的字符数
     */
    public int countCharNums()
    {
        return  TargetString.length();
    }


    /**
     * @description      统计文件的行数
     */
    public int countRowNums()
    {
        return  ValidRows.size();
    }

    /**
     * @description      统计文件的单词总数
     */
    public int countWordNums()
    {
        ValidWords = new HashMap<>();
        Pattern WordPattern = Pattern.compile("[a-zA-Z]{4}[a-zA-Z0-9]*");//使用正则表达式匹配单词
        String ValidWord;
        for(String ValidRow:ValidRows)
        {
            Matcher WordMatcher=WordPattern.matcher(ValidRow);
            while(WordMatcher.find())
            {
                ValidWord=WordMatcher.group();
                if(!ValidWords.containsKey(ValidWord))
                {
                    ValidWords.put(ValidWord,1);
                }
                else
                {
                    ValidWords.put(ValidWord,ValidWords.get(ValidWord)+1);
                }
            }
        }
        return ValidWords.size();
    }

}
