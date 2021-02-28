import java.util.Random;
import java.util.*;
import java.io.*;
import java.util.HashMap;

public class Lib 
{
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
		if (ch >= '0' && ch <= '9')
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
	
	public void countWord(File file) //统计单词数
	{
		int countWords=0;
		
		try
		{
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
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
					else
					{
						str=""; //不形成单词，清空
					}
				}
				
				if (isSeparator(ch))
				{
					if (str.length()>=4) //形成单词，计数加一
					{
						countWords++;
						//System.out.println(str);
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
				//System.out.println(str);
			}
			
			
			bfr.close();
			fr.close();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		System.out.println("words: "+countWords);
	}
	
	public void countWordFrequency(File file) //统计单词出现频率
	{
		HashMap<String,Integer> has=new HashMap<String,Integer>(); //统计词频
		int countWords=0;
		
		try
		{
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
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
					else
					{
						str=""; //不形成单词，清空
					}
				}
				
				if (isSeparator(ch))
				{
					if (str.length()>=4) //形成单词，计数加一
					{
						countWords++;
						addWordToHash(has,str);
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
				addWordToHash(has,str);
			}			
			
			bfr.close();
			fr.close();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		outputWords(has); //将HashMap按需求输出
	}
	
	public void addWordToHash(HashMap<String,Integer> has,String str)
	{
		//全部置为小写
		str=str.toLowerCase();
		
		if (!has.containsKey(str)) //若尚无此单词
		{ 
			has.put(str,1);
		} 
		else //如果有，就将次数加1
		{
			has.put(str,has.get(str)+1);
		}

	}

	public void addWordToTree(TreeMap<String,Integer> tree,String str)
	{
		if (!tree.containsKey(str)) //若尚无此单词
		{ 
			tree.put(str,1);
		} 
		else //如果有，就将次数加1
		{
			tree.put(str,tree.get(str)+1);
		}

	}
	
	public void outputWords(HashMap<String,Integer> has) //按频率输出
	{
	 
		//遍历map
		Iterator iterator = has.keySet().iterator();
		int maxOccur=0;
		while(iterator.hasNext())
		{
			String word = (String) iterator.next();
			if (has.get(word)>maxOccur)
				maxOccur=has.get(word);
		}
		//System.out.println("最多出现："+maxOccur);	
		
		//构造初始化TreeMap数组
		TreeMap[] wordArray=new TreeMap[maxOccur+1];
        for (int i=0;i<=maxOccur;i++) 
        {
        	wordArray[i]=new TreeMap<String,Integer>();
        }
        
        //增改TreeMap数组
        iterator = has.keySet().iterator(); //重置迭代器
		while(iterator.hasNext())
		{
			String word=(String)iterator.next();
			addWordToTree(wordArray[has.get(word)],word);
		}
		
		//遍历TreeMap数组
		int num=1;
        for (int i=maxOccur;i>0;i--) 
        {
        	iterator=wordArray[i].keySet().iterator();
        	while(iterator.hasNext())
    		{
    			String word=(String)iterator.next();
    			if (num<=10)
    			{
    				System.out.println(word+": "+i);
    				num++;
    			}
    		}
        }
		
		
	}


}
