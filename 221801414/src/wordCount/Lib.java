package wordCount;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class Lib {
	private int charCount=0;
	private int wordCount=0;
	private int lineCount=0;
	private static String wordMatch = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
	private static String lineMatch = "(^|\n)\\s*\\S+";
	private BufferedReader reader=null;
	private BufferedWriter writer=null;
	private String fileInPath="";
	private String fileOutPath="";
	private HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	Lib(String fileIn,String fileOut){
		fileInPath=fileIn;
		fileOutPath=fileOut;
	}
	public void countCharWord() throws IOException {
		int str;
        String buffer="";
        reader=new BufferedReader(new FileReader(fileInPath));
        while((str=reader.read())>=0 && str<=127 ) {
        	charCount++;
            if(Character.isLetterOrDigit(str)) {
            	buffer+=(char)str;
            }
            else {
            	if(isWord(buffer)) {
            		wordCount++; 
            		String buf = buffer.toLowerCase();
            		if (hashMap.containsKey(buf)) {
            			int occurs=hashMap.get(buf);
            			hashMap.put(buf, occurs+1);
            		}
            		else {
            			hashMap.put(buf, 1);
            		}
            		buf="";
            	}
            	buffer="";
            }
	    }
        reader.close();
    }
	public boolean isWord(String buffer) {
		if(buffer.matches(wordMatch))
			return true;
		else
			return false;
	}

	public void countLine() throws IOException {
		int str;
		StringBuilder builder = new StringBuilder();
		try {
			reader=new BufferedReader(new FileReader(fileInPath));
			while ((str=reader.read())!=-1)
			{
				builder.append((char)str);
			}
			Pattern pattern = Pattern.compile(lineMatch);
			Matcher matcher = pattern.matcher(builder);
			while (matcher.find()){
	        lineCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			reader.close();
		}
	}
}
