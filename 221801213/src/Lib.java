import java.util.Random;
import java.util.*;
import java.io.*;

public class Lib 
{
	public int countChar(File file)	//统计字符数
	{
		try 
		{		
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			char ch;
			int i = 0; //记录字符数
			while((ch = (char) bfr.read()) != (char)-1) //按字符读取文本内容
			{
					i++; //累计字符数
			}
			bfr.close();
			fr.close();		
			return i;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void showChar(File file) //展示所有字符
	{
		try 
		{		
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			char ch;
			while((ch = (char) bfr.read()) != (char)-1)//按字符读取文本内容
			{
					System.out.print(ch);
					System.out.print('|');
			}
			bfr.close();
			fr.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public int countLine(File file) //统计行数
	{
		try 
		{		
			FileReader fr = new FileReader(file);
			BufferedReader bufr = new BufferedReader(fr);
			int i = 0;	//行数
			while(bufr.readLine() != null)
			{
				i++;//累计行数
			}
			bufr.close();
			fr.close();		
			return i;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int countWord(File file) //统计单词数
	{
		try
		{
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			int countStart=0,countEnd=0; //单词字符记录
			int countWords=0;
			String str="";
			

			while((ch = (char) bfr.read()) != (char)-1)//按字符读取文本内容
			{						
				if (isLetter(ch)) //遇到字母就计入
				{
					str+=ch;	
				}
				
				if (isNumber(ch))	
				{
					if (str.length()>=4) //有4个字母开头时才开始记录数字
						str+=ch;
				}
				
				if (isSeparator(ch))
				{
					if (str.length()>=4) //形成单词，计数加一
					{
						countWords++;
						System.out.println(str);
						str="";
					}
					else
					{
						str=""; //不形成单词，清空
					}
				}
				
			}
			
			if(str.length()>=4) //统计最后一个单词
			{
				countWords++;
				System.out.println(str);
			}
			
			
			bfr.close();
			fr.close();
			
			return countWords;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println();
		
		return 0;
	}

	public boolean isLetter(char ch) //判断是不是字母
	{
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
		{
			return true;
		}
		return false;
	}
	
	public boolean isNumber(char ch) //判断是不是数字
	{
		if (ch >= '1' && ch <= '9')
		{
			return true;
		}
		return false;
	}
	
	public boolean isSeparator(char ch) //判断是不是分隔符
	{
		if ( ch == ' ' || (!isLetter(ch) && !isNumber(ch)) )
		{
			return true;
		}
		return false;
	}
}
