import java.util.Random;
import java.util.*;
import java.io.*;

public class WordCount
{
	public WordCount()
	{
	}
	
	public static void main(String args[])
	{
		
		Lib lib=new Lib();
		
		String input="D:\\Program Files\\github\\PersonalProject-Java\\221801213\\src\\input.txt";
		String output="D:\\Program Files\\github\\PersonalProject-Java\\221801213\\src\\output.txt";
        
		File inputFile=new File(input);	
		File outputFile=new File(output);
		lib.process(inputFile, outputFile);
		
	}
}

