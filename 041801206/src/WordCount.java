import Lib.CharacterCounter;
import Lib.LineAndCharacterCounter;
import Lib.WordCounter;

public class WordCount {
   public static void main(String args[]){
      String path="C:\\Users\\cmy\\Desktop\\test.txt";
//      new CharacterCounter(path,3,6,1);
//      LineAndCharacterCounter lineAndCharacterCounter = new LineAndCharacterCounter(path);
//      lineAndCharacterCounter.start();

       new WordCounter(path);

   }
}
