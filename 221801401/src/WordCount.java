import java.io.IOException;

public class WordCount {
	public static void main(String[] args) throws IOException {
		int lineCount;
		int charactersCount;
		int wordsCount;
		System.out.println(Lib.getLineCount("D://test.txt"));
		System.out.println(Lib.getCharactersCount("D://test.txt"));
		System.out.println(Lib.getWordsCount("D://test.txt"));
		Lib.sortHashmap();
	}
}