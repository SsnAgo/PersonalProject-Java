import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputeTool {

    private String TargetString;
    private ArrayList<String> Rows;
    private ArrayList<String> ValidRows;
    private ConcurrentHashMap<String, Integer> ValidWords;
    public int RowNums;
    public int CharNums;
    public int WordNums;

    /**
     * @description      构造函数
     */
    public ComputeTool(String TargetString)
    {
        this.TargetString=TargetString;
    }


    /**
     * @description      统计文件的字符数
     */
    private int countCharNums()
    {

        return  TargetString.length();
    }


    /**
     * @description      统计文件的行数
     */
    private int countRowNums()
    {
        Rows =new ArrayList<String>(Arrays.asList(TargetString.split("\n")));
        ValidRows =new ArrayList<String>();

        for (String Row:Rows)
        {
            if(!(Row.trim().isEmpty()))
                ValidRows.add(Row);//将有效行加入集合
        }
        return  ValidRows.size();
    }

    /**
     * @description      统计文件的单词总数
     */
    private int countWordNums()
    {
        ValidWords = new ConcurrentHashMap<>();
        Pattern WordPattern = Pattern.compile("[a-zA-Z]{4}[a-zA-Z0-9]*");//使用正则表达式匹配单词
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(ValidRows.size()));


        int i=0;
        for(String ValidRow:ValidRows)
        {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Matcher WordMatcher=WordPattern.matcher(ValidRow);
                    while(WordMatcher.find())
                    {
                        String ValidWord;
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
            });
        }
        try {
            executor.shutdown();
            executor.awaitTermination(100,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("out of time!");
            e.printStackTrace();
        }
        return ValidWords.size();
    }
    /**
     * @description      获取所有单词的集合并且根据出现频率对其排序
     */
    public ArrayList<Map.Entry<String, Integer>> getTOPWords(int k)
    {
        PriorityQueue<Map.Entry<String, Integer>> Queue=new PriorityQueue<>((O1, O2) -> {
            if(O2.getValue() - O1.getValue()!=0)
            {
                return O2.getValue() - O1.getValue();
            }
            else
            {
                return  O1.getKey().compareTo(O2.getKey());
            }

        });

        for(Map.Entry<String, Integer> Entry:ValidWords.entrySet())
        {
            Queue.add(Entry);
        }
        ArrayList<Map.Entry<String, Integer>> TopList=new ArrayList<>();
        if(k>Queue.size())//当单词总数小于K时 K更新为目前单词的总数
        {
            k=Queue.size();
        }
        for(int i=0;i<k;i++)
        {
            TopList.add(Queue.poll());
        }
        return  TopList;
    }
    /**
     * @description      进行计算并且将结果赋给对应属性
     */
    public void compute()
    {
        CharNums=countCharNums();
        RowNums=countRowNums();
        WordNums=countWordNums();
    }

}
