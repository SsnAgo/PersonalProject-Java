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
		
		File file = new File("input.txt");	
		Lib lib=new Lib();
		
		System.out.println("characters: "+lib.countChar(file));	//读取字符数
		lib.countWord(file);	//读取单词数
		System.out.println("lines: "+lib.countLine(file));	//读取行数
		lib.countWordFrequency(file);	//读取单词频率
		

	}
}

