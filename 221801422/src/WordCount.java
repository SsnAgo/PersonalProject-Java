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
        this.character = Lib.getCharCount(Lib.readFromFile(this.inputPath));
        this.word = Lib.getWordCount(Lib.readFromFile(this.inputPath));
    }

    public void runWordCount()
    {
        //数据输出到文件
        Lib.writeToFile(this.character, this.word, this.line, this.outputPath);
    }

    public static void main(String[] args)
    {
        //命令行参数判断
        if(args.length < 2)
        {
            System.out.println("参数不足, 请重新运行");
        }
        else
        {
            //记录时间戳
            long start = System.currentTimeMillis();
            //两个文件路径
            WordCount wordCount = new WordCount(args[0], args[1]);
            wordCount.init();
            wordCount.runWordCount();
            long end = System.currentTimeMillis();
            //获取代码执行时长
            System.out.println("time costs: " + (end - start) + "ms");
        }
    }
}
