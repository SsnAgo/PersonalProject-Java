import java.io.IOException;

public class WordCount {
    String inputPath = "";
    String outputPath = "";
    int line = 0;
    int word = 0;
    int character = 0;
    
    //构造函数
    public WordCount(String inputPath, String outputPath)
    {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    //获取有效行数 字符数 和单词数
    public void init()
    {
        this.line = Lib.getLineCount(Lib.readFromFile(this.inputPath));
        this.chararacter = Lib.getCharCount(Lib.readFromFile(this.inputPath));
        this.word = Lib.getWordCount(Lib.readFromFile(this.inputPath));
    }
}
