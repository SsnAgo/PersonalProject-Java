import java.io.*;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class WordCount{
    public String inputPath;
    public String outputPath;
    public int validLine;       //有效行
    public long validWord;       //有效单词
    private long characterNum;    //字符数
    public Map<String,Long> mapWord=new Hashtable<>();//用于存单词
    private static Lib.FileUtil fileUtil;


    //构造函数
    public WordCount(String inputPath,String outputPath) throws IOException {
        this.inputPath=inputPath;
        this.outputPath=outputPath;

    }

    //入口
    public static void main(String[] args) throws IOException, InterruptedException {
        //bigDateTest();
        if(args.length!=2){
            System.out.println("参数错误！");
            return ;
        }

        WordCount wordCount=new WordCount(args[0],args[1]);
        long startTime=System.currentTimeMillis();//获取程序开始时间

        fileUtil=new Lib.FileUtil(args[0]);
        BufferedReader reader=fileUtil.getReader();

        Thread threadChars=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wordCount.characterNum=Lib.countChars(fileUtil.getReader());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        threadChars.start();

        Thread threadWords=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wordCount.validWord=Lib.countWords(fileUtil.getReader());
                    wordCount.validLine=Lib.lineNum;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        threadWords.start();

        Thread threadFrequency=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wordCount.mapWord=Lib.countWordFrequency(fileUtil.getReader());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        threadFrequency.start();
//        wordCount.characterNum=Lib.countChars(fileUtil.getReader());
//        wordCount.validWord=Lib.countWords(fileUtil.getReader());
//        wordCount.validLine=Lib.lineNum;
//        wordCount.mapWord=Lib.countWordFrequency(fileUtil.getReader());
//        while (true){
//            if(Thread.activeCount()==1)
//                break;
//        }

        threadChars.join();
        threadFrequency.join();
        threadWords.join();

        long endTime=System.currentTimeMillis();;   //获取程序结束时间
        System.out.println("程序运行时间："+ (endTime-startTime)+"ms");
        System.out.println("单词："+wordCount.validWord);
        System.out.println("共"+wordCount.validLine+"行");
        System.out.println("字符数："+wordCount.characterNum);

        Set<Map.Entry<String, Long>> entrySet= new HashSet<>();
        entrySet=wordCount.mapWord.entrySet();
        Iterator<Map.Entry<String, Long>> iter=entrySet.iterator();
        int l=0;                                                        //限制输出10次
        while (iter.hasNext() && l<10){
            Map.Entry<String, Long> entry=iter.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
            l++;
        }
        reader.close();

    }


    public static void bigDateTest() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            //for(int k=0;k<100;k++){
                for (int i = 0; i < 100000; i++) {
                    stringBuilder.append("aaaa").append(i).append(",");
                }
                stringBuilder.append('\n');
                for (int i = 0; i < 100000; i++) {
                    stringBuilder.append("bbbb").append(i).append(",");
                }
                stringBuilder.append('\n');
                for (int i = 0; i < 10000; i++) {
                    for (int j = 0; j < 100; j++) {
                        stringBuilder.append("maxmax").append(j).append(",");
                    }
                    stringBuilder.append('\n');
                }
            //}
            String testContent = stringBuilder.toString();
            System.out.println(testContent.length());
            BufferedWriter out=new BufferedWriter(new FileWriter("input2.txt"));
            out.write(testContent);
            out.close();
    }
}