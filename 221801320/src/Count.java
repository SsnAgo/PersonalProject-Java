package wordcount;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Count {
	
	public static String output = "";
	
	public static boolean isValidChar(int temp) {
		if((temp >= 97 && temp <= 122) || (temp >= 65 && temp <= 90) || (temp >= 48 && temp <= 57)) {
            return true;
        } else {
            return false;
        }
	}
	
    public static boolean isNum(int temp) {
        if (temp >= 48 && temp <= 57) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidChars(char[] chars) {
        if (chars.length >= 4 && !isNum(chars[0]) && !isNum(chars[1]) && !isNum(chars[2]) && !isNum(chars[3])) {
            return true;
        } else {
            return false;
        }
    }
	
	public static void charactersCount(String inputFile)throws IOException{
		Reader reader =FileIO.openInputFile(inputFile);
		int numCount = 0;
		int temp;
		
		while((temp = reader.read()) != -1) {
			numCount++;
		}
		
		output.concat("characters: " + numCount + '\n');
		System.out.println("1"+Count.output);
		reader.close();
		
	}
	
	public static void wordsCount(String inputFile)throws IOException{
		Reader reader = FileIO.openInputFile(inputFile);
		int temp;
		int numCount = 0;
		String word = null;
		
		while((temp = reader.read()) != -1) {
			if(isValidChar(temp)) {
			word += (char)temp;
			temp =reader.read();
			}
			else
			{
				temp = reader.read();
			}
			
			char[] chars = word.toCharArray();
			if(isValidChars(chars)) {
				numCount++;
			}
			word = "" + (char)temp;
		}
		output.concat("words: " + numCount + '\n');
		reader.close();
	}
	
	
	

	
	
}
