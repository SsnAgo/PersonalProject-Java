import java.io.IOException;

public class WordCount {
	String inputPath = "";
	String outputPath = "";
	public WordCount(String inputPath, String outputPath) {
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}
/*	public void run() {
		
	}*/
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("参数不足两个，请重新运行");
		}
		else {
			WordCount wordcount = new WordCount(args[0], args[1]);
			Lib.writeToFile(Lib.getCharactersCount(wordcount.inputPath), 
					Lib.getWordsCount(wordcount.inputPath), 
					Lib.getLineCount(wordcount.inputPath), wordcount.outputPath);
		}
	}
}