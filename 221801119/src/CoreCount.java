package word.count.pro;

import java.io.*;
import java.lang.String;

public class CoreCount {
	private File file;
	private int charCount;
	private int wordCount;
	private int lineCount;
	public CoreCount(String fileName) {
		this.file = new File(fileName);
		charCount = wordCount = charCount = 0;
	}
	public void Count() {
		CountChars();
		CountWords();
		CountLines();
	}
	public int getCharCount() {
		return charCount;
	}
	public int getWordCount() {
		return wordCount;
	}
	public int getLineCount() {
		return lineCount;
	}
	private void CountChars() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			try {
				while(reader.read() != -1) {
					charCount += 1;
				}
			} catch (IOException e) {
			}
		} catch (FileNotFoundException e) {
			System.out.print("Can not find file \"" + file.getName() + "\".");
		}
	}
    private void CountWords() {
		
	}
    private void CountLines() {
	
    }
}
