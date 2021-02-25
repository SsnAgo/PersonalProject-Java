import javafx.util.Pair;
import java.io.File;
import java.util.HashMap;

public class WordCount {
    private String inputPath;
    private String outputPath;
    public WordCount(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }
    public void analyseArticle() {
        String str = Lib.readFile(inputPath);
        String filterStr = Lib.filterChinese(str);
        String deletedStr = Lib.deleteChineseString(str);
        int chars = Lib.countChars(filterStr);
        int lines = Lib.countLines(new File(inputPath));
        Pair<HashMap<String,Integer>,Integer> pair = Lib.makeWordPair(deletedStr);
        Lib.outputToFile(chars,pair.getValue(),lines,outputPath);
    }
    public static void main(String[] args) {
        if (args.length<2) {
            System.out.println("参数不足两个，请重新运行并输入");
            return;
        }
        WordCount solver = new WordCount(args[0],args[1]);
        solver.analyseArticle();
    }
}
