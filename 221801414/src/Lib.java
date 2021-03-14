package wordCount;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class Lib {
	private int charCount = 0;
	private int wordCount = 0;
	private int lineCount = 0;
	private static final int MAX_NUM = 10;
	private StringBuilder builder = new StringBuilder();
	//private static final String WORD_REGEX_RULE = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
	//private static final Pattern wordPattern = Pattern.compile(WORD_REGEX_RULE);
	private static final String LINE_REGEX_RULE = "(^|\n)\\s*\\S+";
	private static final Pattern linePattern = Pattern.compile(LINE_REGEX_RULE);
	private BufferedReader reader = null;
	private BufferedWriter writer = null;
	private String fileInPath = "";
	private String fileOutPath = "";
	private HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> hashMaps = new HashMap<String, Integer>();
	private ArrayList<HashMap.Entry<String, Integer>> List;
	
	public Lib(String fileIn, String fileOut) {
		fileInPath = fileIn;
		fileOutPath = fileOut;
	}
	/*
	 * 统计字符数和单词数
	 */
	public void countCharWord() throws IOException {
		int str;
        String buffer = "";
        reader=new BufferedReader(new FileReader(fileInPath));
		
        while((str=reader.read()) >= 0 && str <= 127 ) {
        	charCount++;
        	builder.append((char)str);
            if (Character.isLetterOrDigit(str)) {
            	buffer += (char)str;
            }
            else {
            	if (isWord(buffer)) {
            		wordCount++;
            		String lowerBuffer = buffer.toLowerCase();
            		if (hashMap.containsKey(lowerBuffer)) {
            			int occurs = hashMap.get(lowerBuffer);
            			hashMap.put(lowerBuffer, occurs+1);
            		}
            		else {
            			hashMap.put(lowerBuffer, 1);
            		}
            		lowerBuffer = "";
            	}
            	buffer = "";
            }
	    }
        reader.close();
    }
	/*
	 * 判断是否为单词
	 */
	public boolean isWord(String buffer) {
		if (buffer.length() >= 4)
	    {
			char buf[] = buffer.toCharArray();
			for (char temp : buf) {
				if (!Character.isLetterOrDigit(temp)) {
					return false;
				}
			}
			return true;
	    }
	    else
	    {
	        return false;
	    }
		/*if(buffer.matches(WORD_REGEX_RULE))
			return true;
		else
			return false;
		*/
	}
	
	/*
	 * 统计行数
	 */
	public void countLine() throws IOException {
		Matcher matcher = linePattern.matcher(builder);
		while (matcher.find()) {
			lineCount++;
	    }
	    
	}
	
	/*
	 * 将hashMap中的单词排序
	 */
	public void sortWordOccurs() {
	    hashMaps = hashMap.entrySet().stream()
	    		.sorted(Map.Entry.<String, Integer>comparingByValue()
	            .reversed().thenComparing(Map.Entry.comparingByKey())).limit(MAX_NUM)                      
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
	            		, (e1, e2) -> e1, LinkedHashMap::new));
	     List = new ArrayList <HashMap.Entry <String, Integer> > (hashMaps.entrySet());
	}
	
	/*
	 * 按要求打印内容至输出文件
	 */
	public void printFile() throws IOException {
		writer=new BufferedWriter(new FileWriter(fileOutPath));
		writer.write("characters: " + charCount + "\n");
		writer.write("words: " + wordCount + "\n");
		writer.write("lines: " + lineCount + "\n");
		
		for(HashMap.Entry<String, Integer> map : List) {
            writer.write(map.getKey() + ": " + map.getValue() + "\n");
        }
		writer.close(); 
	}
}