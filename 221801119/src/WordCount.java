package word.count.pro;

import java.io.*;
import java.util.*;

import java.lang.String;

/**
 * @author: mis-pud-in (221801119)
 * @createDate: 2021/2/25
 * @description: this includes the main function.
 */
public class WordCount {
	public static void main(String[] args) {
		//get filename from users and process input string
		String inputFile = args[0];
        String outputFile = args[1];
		//main function
        CoreCount coreCount = new CoreCount(inputFile);
        coreCount.count();
        //create output file
		File file = new File(outputFile);
		try {
			file.createNewFile();
			//BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()), "utf-8"));
			writer.append("characters: " + coreCount.getCharCount() + "\n");
			writer.append("words: " + coreCount.getWordCount() + "\n");
			writer.append("lines: " + coreCount.getLineCount() + "\n");
			int wordsNum = 0;
			Iterator<Map.Entry<String, Long>> iterator = coreCount.getWordsList().iterator();
			Map.Entry<String, Long> entry = null;
	        while (iterator.hasNext() == true && wordsNum < 10) {
	        	entry = (Map.Entry<String, Long>) iterator.next();
	        	writer.append(entry.getKey().toString() + ": " + entry.getValue().toString() + "\n");
	        	wordsNum += 1;
	        }
	        writer.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}

