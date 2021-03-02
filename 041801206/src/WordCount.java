import Lib.CharacterCounter;
import Lib.LineAndCharacterCounter;

public class WordCount {
   public static void main(String args[]){
      String path="C:\\Users\\cmy\\Desktop\\test.txt";
//       new CharacterCounter(path,6,2048,5);
      LineAndCharacterCounter lineAndCharacterCounter = new LineAndCharacterCounter(path);
      lineAndCharacterCounter.start();

   }
}
