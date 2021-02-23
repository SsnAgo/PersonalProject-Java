import javafx.util.Pair;

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
        Pair<HashMap<String,Integer>,Integer> pair = Lib.makeWordPair(deletedStr);
        System.out.println("Chars:"+chars);
        System.out.println("Words:"+pair.getValue());
        System.out.println("Lines:"+lines);
        Lib.outputSortedResult();
        Lib.outputToFile(chars,pair.getValue(),lines);
    }
}
