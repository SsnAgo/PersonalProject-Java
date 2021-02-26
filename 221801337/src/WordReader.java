import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public interface WordReader {
	public String nextWord();
	public boolean setInputStream(InputStream is);
	public long getLineNumber();
	public long getWordNumber();
	public long getCharNumber();
	public void clear();
}
