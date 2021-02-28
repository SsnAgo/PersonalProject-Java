import java.io.*;
import java.util.Scanner;

public class WordCount{

    public String inputPath;
    public String outputPath;
    public int validLine;       //有效行

    //构造函数
    public WordCount(String inputPath,String outputPath) throws IOException {
        this.inputPath=inputPath;
        this.outputPath=outputPath;

        //bigDateTest();
    }

    //入口
    public static void main(String[] args) throws IOException {
        if(args.length!=2){
            System.out.println("参数错误！");
            return ;
        }



        WordCount wordCount=new WordCount(args[0],args[1]);
        long startTime=System.currentTimeMillis();//获取程序开始时间
        File inputFile=new File(wordCount.inputPath);
        BufferedReader reader=null;

        reader=new BufferedReader(new FileReader(inputFile));
        String tempString = null;
        int line = 1;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            // 显示行号
            //System.out.println("line " + line + ": " + tempString);
            if(tempString.trim().isEmpty())         //如果是空行，直接跳过进入下一行
                return ;
            line++;
        }
        reader.close();

        long endTime=System.currentTimeMillis();;   //获取程序结束时间
        System.out.println("共"+line+"行");
        System.out.println("程序运行时间："+ (endTime-startTime)+"ms");


    }

    public void statisticsLine(String line){
        if(line.trim().isEmpty())       //如果是空行，直接跳过进入下一行
            return ;
        validLine++;                    //不是空行就是有效行

        int wordFlag=0;
        for(int i=0;i<line.length();i++){
            if(!Character.isLetter(line.charAt(i))){
                if(!(wordFlag>=3 && Character.isDigit(line.charAt(i)))){
                    //遇到分隔符，将wordFlag置零
                    if(wordFlag>=3)
                        wordFlag=0;
                }
                wordFlag++;
            }
            else
                wordFlag++;
        }
    }

    public void bigDateTest() throws IOException {
        BufferedWriter out=new BufferedWriter(new FileWriter(outputPath));

        for(int j=0;j<100000;j++){
            for(int i=0;i<10000;i++){
                out.write("a");
            }
            out.write("\n");
            for(int i=0;i<10000;i++){
                out.write("b");
            }
            out.write("\n");
            for(int i=0;i<10000;i++){
                out.write("c");
            }
            out.write("\n");
        }
        out.close();
    }
}