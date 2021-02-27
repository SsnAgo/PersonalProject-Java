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
		//get filename from users
		Scanner input = new Scanner(System.in);
		String inputStr = input.nextLine();
		input.close();
        String[] files = inputStr.split(" "); 
        String inputFile = files[0];
        String outputFile = files[1];
		//main function
        CoreCount coreCount = new CoreCount(inputFile);
        coreCount.count();
        /*
        System.out.print("characters: " + coreCount.getCharCount() + "\n");
        System.out.print("words: " + coreCount.getWordCount() + "\n");
        System.out.print("lines: " + coreCount.getLineCount() + "\n");
        Iterator<Map.Entry<String,Integer>> iterator = coreCount.getWordsList().iterator();
        while (iterator.hasNext()) {
        	Map.Entry<String,Integer> entry = (Map.Entry<String,Integer>) iterator.next();
        	System.out.print(entry.getKey().toString() + ": " + entry.getValue().toString() + "\n");
        }*/
        //create output file
		File file = new File(outputFile);
		if (file.exists()) {
			System.out.print("File \"" + outputFile + "\" already exist.");
		}
		try {
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.append("characters: " + coreCount.getCharCount() + "\n");
			writer.append("words: " + coreCount.getWordCount() + "\n");
			writer.append("lines: " + coreCount.getLineCount() + "\n");
			int wordsNum = 0;
			Iterator<Map.Entry<String,Integer>> iterator = coreCount.getWordsList().iterator();
	        while (iterator.hasNext() && wordsNum < 10) {
	        	Map.Entry<String,Integer> entry = (Map.Entry<String,Integer>) iterator.next();
	        	writer.append(entry.getKey().toString() + ": " + entry.getValue().toString() + "\n");
	        	wordsNum += 1;
	        }
	        writer.close();
		} catch (IOException e) {
		}
	} 
}

