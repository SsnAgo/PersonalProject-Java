import Lib.LineAndCharacterCounter;
import Lib.Output;
import Lib.WordCounter;

public class WordCount {
   public static void main(String args[]){
      long start=System.currentTimeMillis();
      String path="C:\\Users\\cmy\\Desktop\\test.txt";
      String outPutPath="";
      LineAndCharacterCounter lineAndCharacterCounter = new LineAndCharacterCounter(path);
      lineAndCharacterCounter.start();
      WordCounter wordCounter= new WordCounter(path);
      Output output=new Output(lineAndCharacterCounter.getCharNum(),
               lineAndCharacterCounter.getLineNum(),
               wordCounter.getWordNum(),
               wordCounter.getMaplist(),
               outPutPath
               );
      output.show();

      System.out.println("字符行数统计耗时："+lineAndCharacterCounter.getUseTime());
      System.out.println("单词统计耗时："+wordCounter.getUseTime());
      System.out.println("总耗时："+ (System.currentTimeMillis()-start));



   }
}
