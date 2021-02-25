package word.count.pro;

import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.lang.String;
    
public class WordCount {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String inputStr = input.nextLine();
		input.close();
        String[] files = inputStr.split(" "); 
        String inputFile = files[0];
        String outputFile = files[1];
		File file = new File(outputFile);
		if (file.exists()) {
			System.out.print("File " + outputFile + " does not exist.");
		}
		/*
		try {
			file.createNewFile();
		} catch (IOException ex){
			
		}*/
    }
}
