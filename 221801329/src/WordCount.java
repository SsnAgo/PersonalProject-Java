import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class WordCount {
    public static void main(String[] args) throws IOException {

        String str = Lib.readFile(Lib.DIR+"\\src\\input.txt");
        String filterStr = Lib.filterChinese(str);
        String deletedStr = Lib.deleteChineseString(str);
        int chars = Lib.countChars(filterStr);
        int lines = Lib.countLines(new File(Lib.DIR+"\\src\\input.txt"));
        HashMap<String,Integer> wordMap = Lib.makeWordMap(deletedStr);
        System.out.println(wordMap);
        //System.out.println("MapSize:"+wordMap.size());
        //int words = Lib.countWords(deletedStr);
        System.out.println("Chars:"+chars);
        System.out.println("Lines:"+lines);

        //System.out.println("Words:"+words);
    }
}
