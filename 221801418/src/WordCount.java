import java.io.IOException;

public class WordCount {
	public static void main(String argv[]) throws IOException {
		Lib lib=new Lib();
		String str=lib.readTextFile(argv[0]);
		if(!str.isEmpty()) {
			lib.writeTextFile(Lib.countChars(str),Lib.countWords(str),
					Lib.countLines(str),lib.countTimes(str),argv[1]);
		}
	}  
}
