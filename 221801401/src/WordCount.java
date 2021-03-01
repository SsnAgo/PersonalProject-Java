import java.io.IOException;

public class WordCount {
    String inputPath = "";
    String outputPath = "";
    int lines = 0;
    int words = 0;
    int characters = 0;
    
    //构造函数
    public WordCount(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        }
    
    //变量的初始化
    public void init() {
        this.lines = Lib.getLineCount(Lib.readFromFile(this.inputPath));
        this.characters = Lib.getCharactersCount(Lib.readFromFile(this.inputPath));
        this.words = Lib.getWordsCount(Lib.readFromFile(this.inputPath));
        }
    
    public void runWordCount() {
        Lib.writeToFile(this.characters, this.words, this.lines, this.outputPath);
    }
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("参数不足，请重新运行");
            }
        else {
            long start = System.currentTimeMillis();
            WordCount wordCount = new WordCount(args[0], args[1]);
            wordCount.init();
            wordCount.runWordCount();
            long end = System.currentTimeMillis();
            System.out.println("time costs: " + (end - start) + "ms");
            }
        }
}