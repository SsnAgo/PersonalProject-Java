import java.io.InputStream;

public interface WordReader {
	public String nextWord();

	public boolean setInputStream(InputStream is);

	public long getLineNumber();

	public long getWordNumber();

	public long getCharNumber();

	public void clear();
}
