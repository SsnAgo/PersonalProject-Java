import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputeTool {

    private String TargetString;
    private ArrayList<String> ValidRows;
    private ConcurrentHashMap<String, Integer> ValidWords;
    public   ArrayList<Map.Entry<String, Integer>> TopList;
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
        ValidRows =new ArrayList<String>();
        String TempRow;
        StringTokenizer Tokenizer=new StringTokenizer(TargetString , "\n",false);
        while(Tokenizer.hasMoreTokens()){
            TempRow=Tokenizer.nextToken().trim();
            if(!TempRow.isEmpty())
            {
                ValidRows.add(TempRow);
            }
        }
        return  ValidRows.size();
    }

    /**
     * @description      统计文件的单词种类总数
     */
    private int countWordTypeNums()
    {
        ValidWords = new ConcurrentHashMap<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 8, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(ValidRows.size()));

        int i=0;
        int QuestSize=ValidRows.size();
        //将任务分为八份 分给八个线程 每个线程处理总行数的1/8
        if(QuestSize>=8) {
            for (i = 0; i < 8; i ++) {
                int Start=i*(int) Math.floor(QuestSize/8);
                int End;
                if(i<7)
                {
                    End=Start+QuestSize/8;
                }
                else
                {
                    End=QuestSize;
                }
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Start+" "+End);
                        for (int j = Start; j<End; j++) {
                            Pattern WordPattern = Pattern.compile("(^[a-zA-Z]{4}[a-zA-Z0-9]*)");//使用正则表达式匹配单词
                            for(String Word:ValidRows.get(j).split("[^a-zA-z0-9]"))
                                {
                                    Matcher WordMatcher = WordPattern.matcher(Word);
                                    if(WordMatcher.find())
                                    {
                                        String ValidWord;
                                        ValidWord=WordMatcher.group(0).toLowerCase();
                                        if(ValidWords.putIfAbsent(ValidWord,1)!=null)
                                        {
                                            ValidWords.computeIfPresent(ValidWord, (k, v) -> v + 1);
                                        }
                                    }
                                }
                        }
                        }
                    }
                );
            }
        }
        else//行数小于8的情况 每一行都分一个线程
        {
            for(String ValidRow:ValidRows)
            {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Pattern WordPattern = Pattern.compile("(^[a-zA-Z]{4}[a-zA-Z0-9]*)");//使用正则表达式匹配单词
                        for(String Word:ValidRow.split("[^a-zA-Z0-9]"))
                        {
                            Matcher WordMatcher = WordPattern.matcher(Word);
                            if(WordMatcher.find())
                            {
                                String ValidWord;
                                ValidWord=WordMatcher.group(0).toLowerCase();
                                if(ValidWords.putIfAbsent(ValidWord,1)!=null)
                                {
                                    ValidWords.computeIfPresent(ValidWord, (k, v) -> v + 1);
                                }
                            }
                        }
                    }
                    }
                );
            }
        }
        try {
            executor.shutdown();
            while (!executor.awaitTermination(10, TimeUnit.MINUTES)) {
            }
        } catch (InterruptedException e) {
            System.out.println("out of time!");
            e.printStackTrace();
        }

        return ValidWords.size();
    }
    /**
     * @description      获取所有单词的集合并且根据出现频率对其排序
     */
    private int CountWordNums(int k)
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
            WordNums+=Entry.getValue();//统计单词出现次数
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
        this.TopList=TopList;
        return  WordNums;
    }
    /**
     * @description      进行计算并且将结果赋给对应属性
     */
    public void compute()
    {
        CharNums=countCharNums();
        RowNums=countRowNums();
        countWordTypeNums();
        WordNums=CountWordNums(10);
    }

}
