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
    private void analyseArticle() {
        String str = Lib.readFile(inputPath);
        String filterStr2 = Lib.filterChinese(str);
        String filterStr = str.toLowerCase().replaceAll(Lib.FLITER_REGEX," ");
        int chars = Lib.countChars(filterStr2);
        int lines = Lib.countLines(new File(inputPath));
        Pair<HashMap<String,Integer>,Integer> pair = Lib.makeWordPair(filterStr);
        Lib.outputToFile(chars,pair.getValue(),lines,outputPath);
    }
    public static void main(String[] args) {
        if (args.length<2) {
            System.out.println("参数不足两个，请重新运行并输入");
            return;
        }
        WordCount solver = new WordCount(args[0],args[1]);
        solver.analyseArticle();//        System.out.println(str.toLowerCase().replaceAll(Lib.FLITER_REGEX," "));
    }
}
