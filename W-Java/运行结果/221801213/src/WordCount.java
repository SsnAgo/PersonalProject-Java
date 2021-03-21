import java.util.Random;
import java.util.*;
import java.io.*;

public class WordCount
{
	
	public static void main(String args[])
	{
		if(args.length<2) 
		{
            System.out.println("参数不足2个");
        }
        else 
        {
        	Lib lib=new Lib();
        	
//			String input="D:\\Program Files\\github\\PersonalProject-Java\\221801213\\src\\input.txt";
//        	String output="D:\\Program Files\\github\\PersonalProject-Java\\221801213\\src\\output.txt";
        	
        	String input=args[0];
        	String output=args[1];
        	
        	File inputFile=new File(input);	
			File outputFile=new File(output);
			
			lib.process(inputFile, outputFile);

        }	
	}
	
}

