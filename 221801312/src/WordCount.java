import java.io.*;
import java.util.*;



public class WordCount{
    private final String inputPath;
    private final String outputPath;
    private int validLine;       //有效行
    private long validWord;       //有效单词
    private long characterNum;    //字符数
    private String wordsFrequency;//出现频率前十的单词
    private static Lib.FileUtil fileUtil;

    //构造函数
    public WordCount(String inputPath,String outputPath) {
        this.inputPath=inputPath;
        this.outputPath=outputPath;
    }

    //入口
    public static void main(String[] args) throws InterruptedException {
        //bigDateTest();
        if(args.length!=2){
            System.out.println("参数错误！");
            return ;
        }
        WordCount wordCount=new WordCount(args[0],args[1]);
        long startTime=System.currentTimeMillis();//获取程序开始时间
        fileUtil=new Lib.FileUtil(args[0]);
        Thread threadChars=new Thread(() -> {
            try {
                wordCount.characterNum=Lib.countChars(wordCount.inputPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threadChars.start();
        Thread threadWords=new Thread(() -> {
            try {
                wordCount.validWord=Lib.countWords(wordCount.inputPath);
                wordCount.validLine=Lib.lineNum;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threadWords.start();
        Thread threadFrequency=new Thread(() -> {
            try {
                wordCount.wordsFrequency=Lib.countWordFrequency(wordCount.inputPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threadFrequency.start();

        threadChars.join();
        threadFrequency.join();
        threadWords.join();

        long endTime=System.currentTimeMillis();   //获取程序结束时间
        //System.out.println("程序运行时间："+ (endTime-startTime)+"ms");

        StringBuilder content=new StringBuilder();                  //将计算结果写入文件
        content.append("characters: ").append(wordCount.characterNum).append('\n')
                .append("words: ").append(wordCount.validWord).append('\n')
                .append("lines: ").append(wordCount.validLine).append('\n')
                .append(wordCount.wordsFrequency);
        fileUtil.writeFile(content.toString(),wordCount.outputPath);
    }

    public static void bigDateTest() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            char c;
            for(int i=0;i<=50000000;i++){
                for(int j=0;j<(int)(Math.random()*10);j++){
                    c=(char)(int)(Math.random()*26+97);
                    stringBuilder.append(c);
                }
                stringBuilder.append((int)(Math.random()*1000)).append(',');
                if((int)(Math.random()*100)==50)
                    stringBuilder.append('\n');
            }
            String testContent = stringBuilder.toString();
            System.out.println(testContent.length());
            BufferedWriter out=new BufferedWriter(new FileWriter("input2.txt"));
            out.write(testContent);
            out.close();
    }
}