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
		
		File file = new File("test.txt");	
		Lib lib=new Lib();
		
		System.out.println("Lines:"+lib.countLine(file));	//读取行数
		System.out.println("Chars:"+lib.countChar(file));	//读取字符数
		System.out.println("Words:"+lib.countWord(file));	//读取单词数
		

	}
}

