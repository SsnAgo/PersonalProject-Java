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
		//testcountChar();
		
	}
    public static void testcountChar() 
    {
    	Lib lib=new Lib();
    	File testInputFile=new File("D:\\Program Files\\github\\PersonalProject-Java\\221801213\\src\\testInput.txt");
    	File testOutputFile=new File("D:\\Program Files\\github\\PersonalProject-Java\\221801213\\src\\testOutput.txt");
    	
        String str="word\nfile\nFile\nwindows98\nwindows2000\n123file\r\nfil\n\n";
        int loopTimes=1;
        String testStr="";
        for(int i=0;i<loopTimes;i++) 
        {
            testStr+=str;
        }
        
        lib.clearText(testInputFile);
        lib.writeToText(testInputFile,testStr);
        
        lib.countChar(testInputFile);
    }
}

