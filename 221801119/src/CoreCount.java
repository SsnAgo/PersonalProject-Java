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
	private Long charCount;
	private Long wordCount;
	private Long lineCount;
	private Map<String, Long> wordsMap;
	private List<Map.Entry<String, Long>> wordsList;
	
	public CoreCount(String fileName) {
		this.file = new File(fileName);
		wordCount = lineCount = charCount = 0L;
		wordsMap = new HashMap<String, Long>();
		wordsList = null;
	}

	public Long getCharCount() {
		return charCount;
	}
	public Long getWordCount() {
		return wordCount;
	}
	public Long getLineCount() {
		return lineCount;
	}
	public Map<String, Long> getWordsMap() {
		return wordsMap;
	}
	public List<Map.Entry<String, Long>> getWordsList() {
		return wordsList;
	}
	
	public void count() {
		countChars();
		countWords();
		sortWordsMap();
	}
	
	public void countChars() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			charCount = 0L;
			try {
				while (reader.read() != -1) {
					charCount += 1;
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
    public void countWords() {
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			wordCount = lineCount = 0L;
			try {
				String line, word;
				line = word = "";
				char ch;
				Long value;
				while ((line = reader.readLine()) != null) {	
					if (! line.trim().equals("")) {
						lineCount += 1;
						line += "\n";
						int len = line.length();
						for (int i = 0; i < len; i++) {
							ch = line.charAt(i);
							if (Character.isLetterOrDigit(ch))
								word += ch;
							else {
								if (!"".equals(word)) {									
									if (isProperWord(word)) {
										wordCount += 1;
										word = word.toLowerCase();
										if (wordsMap.get(word) == null) {
											wordsMap.put(word, 1L);
										}
										else {
											value = wordsMap.get(word);
											wordsMap.put(word, ++value);
										}
									} // end if
									word = "";
								} // end if
							}
						} // end for
					} // end if
				} // end while
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}

    public void sortWordsMap() {
		wordsList = new ArrayList<Map.Entry<String, Long>>(wordsMap.entrySet());
		Collections.sort(wordsList, new Comparator<Map.Entry<String, Long>>() {
			public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
				if (o1.getValue() == o2.getValue()) {
					return o1.getKey().compareTo(o2.getKey());
				}
				else {
					return (int)(o2.getValue() - o1.getValue());
				}
			}		
		});
    }
    
    private boolean isProperWord(String word) {
    	if (word.length() < 4) {
    		return false;
    	}
    	for (int i = 0; i < 4; i++) {
    		if(! Character.isLetter(word.charAt(i))) {
    			return false;
    		}
    	}
    	return true;
    }
}
