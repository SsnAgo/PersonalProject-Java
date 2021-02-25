import java.io.IOException;

public class WordCount {
	public static void main(String[] args) throws IOException {
		System.out.println(Lib.getLineCount("D://test.txt"));
		System.out.println(Lib.DIR);
		System.out.println(Lib.getCharactersCount("D://test.txt"));
		System.out.println(Lib.getWordsCount("D://test.txt"));
	}
}