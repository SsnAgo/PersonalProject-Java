import java.io.*;
import java.util.Scanner;

public class WordCount{

    public String inputPath;
    public String outputPath;
    public int validLine;       //有效行
    public int validWord;       //有效单词

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
            System.out.println("line " + line + ": " + tempString);
            line++;
            wordCount.statisticsLine(tempString);
        }
        reader.close();

        long endTime=System.currentTimeMillis();;   //获取程序结束时间
        System.out.println("共"+wordCount.validLine+"行");
        System.out.println("程序运行时间："+ (endTime-startTime)+"ms");
        System.out.println("单词："+wordCount.validWord);

    }

    public void statisticsLine(String line){
        if(line.trim().isEmpty())       //如果是空行，直接跳过进入下一行
            return ;
        validLine++;                    //不是空行就是有效行

        int wordFlag=0;
//        for(int i=0;i<line.length();i++){
//            if(!Character.isLetter(line.charAt(i))){
//                if(!(wordFlag>=3 && Character.isDigit(line.charAt(i))) || i==line.length()-1){
//                    //遇到分隔符，将wordFlag置零
//                    if(wordFlag>=3){    //如果wordFlag>=3说明分隔符前的是一个单词
//                        validWord++;
//                    }
//                    wordFlag=0;
//                }
//                wordFlag++;
//            }
//            else
//                wordFlag++;
//        }

        for(int i=0;i<line.length();i++){
            if(Character.isLetter(line.charAt(i))){
                wordFlag++;                             //如果是字母，将wordFlag++;
            }
            else if(Character.isDigit(line.charAt(i)) && wordFlag>=4){
                wordFlag++;                             //当wordFlag>=4时，判定为一个单词，若后续为数字，将wordFlag继续++
            }
            else if(wordFlag<4){                        //当wordFlag<4且遇到字符为数字时，说明不可能为单词，将wordFlag置零
                wordFlag=0;
            }
            if(!Character.isLetterOrDigit(line.charAt(i)) || i==line.length()-1){
                if(wordFlag>=4)                             //当遇到分隔符或检索到行尾时
                    validWord++;                            //若有wordFlag>=4，判定为单词有效，将有效单词++
                wordFlag=0;                                 //不论是否为有效单词，都将wordFlag置零
            }
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