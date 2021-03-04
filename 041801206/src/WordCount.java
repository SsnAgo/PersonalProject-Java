import Lib.LineAndCharacterCounter;
import Lib.Output;
import Lib.WordCounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordCount {
   public static void main(String args[]) throws IOException {
      try {
         System.out.println("=====> Input examples: java WordCount C:\\Users\\cmy\\Desktop\\input.txt C:\\Users\\cmy\\Desktop\\output.txt");
         BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
         String cmd;
         cmd = scanner.readLine();
         if(cmd.substring(0,14).equals("java WordCount") || cmd.length()<=14)
         {
            String twoFile=cmd.substring(15,cmd.length());
            String inPutPath=twoFile.substring(0,twoFile.indexOf(" "));
            String outPutPath=twoFile.substring(twoFile.indexOf(" ")+1,twoFile.length());
            File in=new File(inPutPath);
            File out=new File(outPutPath);
            if(in.exists() && out.exists()){
               System.out.println("开始计算");
               long start=System.currentTimeMillis();
               LineAndCharacterCounter lineAndCharacterCounter = new LineAndCharacterCounter(inPutPath);
               lineAndCharacterCounter.start();
               WordCounter wordCounter= new WordCounter(inPutPath);

               Output output=new Output(lineAndCharacterCounter.getCharNum(),
               lineAndCharacterCounter.getLineNum(),
               wordCounter.getWordNum(),
               wordCounter.getEmptyLineNum(),
               wordCounter.getMaplist(),
               outPutPath
               );

                output.show();
                output.writeToOutPut();
                System.out.println("计算完成！");
                System.out.println("字符行数统计耗时："+lineAndCharacterCounter.getUseTime());
                System.out.println("单词统计耗时："+wordCounter.getUseTime());
                System.out.println("总耗时："+ (System.currentTimeMillis()-start));
            }
            else {
               System.out.println("file does not exist");
            }
         }
         else
         {
            System.out.println("Input error");
         }
      }catch (Exception e){
          e.printStackTrace();
      }







   }
}
