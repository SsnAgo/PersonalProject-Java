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
		this.lines = Lib.getLineCount(this.inputPath);
		this.characters = Lib.getCharactersCount(this.inputPath);
		this.words = Lib.getWordsCount(this.inputPath);
	}
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("参数不足两个，请重新运行");
		}
		else {
			WordCount wordCount = new WordCount(args[0], args[1]);
			wordCount.init();
			Lib.writeToFile(wordCount.characters, wordCount.words, wordCount.lines, 
					wordCount.outputPath);
		}
	}
}