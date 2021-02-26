package word.count.pro;

import java.io.*;
import java.util.*;
import java.lang.String;
import java.lang.Character;

/**
 * @author: mis-pud-in (221801119)
 * @createDate: 2021/2/25
 * @description: this includes core functions -- count characters, words and lines.
 */
public class CoreCount {
	private File file;
	private int charCount;
	private int wordCount;
	private int lineCount;
	private Map<String, Integer> wordsMap;
	public CoreCount(String fileName) {
		this.file = new File(fileName);
		charCount = wordCount = charCount = 0;
		wordsMap = new HashMap<String, Integer>();
	}
	public void count() {
		countChars();
		countWords();
		countLines();
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
	private void countChars() {
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
    private void countWords() {
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			try {
				String line, word;
				line = word = "";
				char ch;
				while((line = reader.readLine()) != null) {	
					if(! line.trim().equals("")) {
						line += "\n";
						for(int i = 0; i < line.length(); i++) {
							ch = line.charAt(i);
							if(Character.isLetterOrDigit(ch))
								word += ch;
							else {
								if(!"".equals(word)) {									
									if(isProperWord(word)) {
										wordCount += 1;
										word = word.toLowerCase();
										if(wordsMap.get(word) == null) {
											wordsMap.put(word, 1);
										}
										else {
											int value = wordsMap.get(word);
											wordsMap.put(word, ++value);
										}
									}
									word = "";
								}
							}
						}
					}
				}
			} catch (IOException e) {
			}
		} catch (FileNotFoundException e) {
			System.out.print("Can not find file \"" + file.getName() + "\".");
		}	
	}
    private void countLines() {
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			try {
				String line = null;
				while((line = reader.readLine()) != null) {	
					if(! line.trim().equals(""))
						lineCount += 1;
				}
			} catch (IOException e) {
			}
		} catch (FileNotFoundException e) {
			System.out.print("Can not find file \"" + file.getName() + "\".");
		}	
    }
    private boolean isProperWord(String word) {
    	if(word.length() < 4) {
    		return false;
    	}
    	for(int i = 0; i < 4; i++) {
    		if(! Character.isLetter(word.charAt(i))) {
    			return false;
    		}
    	}
    	return true;
    }
}
