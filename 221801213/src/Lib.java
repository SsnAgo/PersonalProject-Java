import java.util.Random;
import java.util.*;
import java.io.*;
import java.util.HashMap;

public class Lib 
{
	HashMap<String,Integer> has=new HashMap<String,Integer>(); //统计词频
	
	public int countChar(File file)	//统计字符数
	{
		int i=0; //记录字符数
		try 
		{		
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
			while((ch=(char)bfr.read()) != (char)-1) //按字符读取文本内容
			{
				if(ch>=0 && ch<=127) 
				{
		               i++; //累计字符数
		        }	
			}
			
			bfr.close();
			fr.close();		
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	public int countLine(File file) //统计行数
	{
		int i=0;
		try 
		{		
			FileReader fr=new FileReader(file);
			BufferedReader bufr=new BufferedReader(fr);
				
			String str;
			while((str=bufr.readLine())!=null)
			{
				if (!str.equals("")) 
					i++;
			}
			bufr.close();
			fr.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	public int countWord(File file) //统计单词出现频率
	{
		
		int countWords=0;
		
		try
		{
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
			String str="";
			while((ch=(char)bfr.read()) != (char)-1)//按字符读取文本内容
			{						
				str+=ch;
			}
			
			String[] strs=str.split("[^a-zA-Z0-9]"); //将文本内容按分隔符分开成若干字符串
			
	        for(int i=0;i<strs.length;i++) 
	        {
	            if(strs[i].matches("[a-zA-Z]{4,}[a-zA-Z0-9]*")) //依次判断字符串是否是单词
	            {
	            	addWordToHash(has,strs[i]); //是单词则加入统计用的hashMap
	            	countWords++; //计数加一
	            }
	        }
			bfr.close();
			fr.close();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return countWords;
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
	
	public void outputWords(HashMap<String,Integer> has,File output) //按频率输出
	{
	 
		//遍历map
		Iterator iterator=has.keySet().iterator();
		int maxOccur=0;
		while(iterator.hasNext())
		{
			String word=(String) iterator.next();
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
        iterator=has.keySet().iterator(); //重置迭代器
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
    			if (num<10)
    			{
    				System.out.println(word+": "+i);
    				writeToText(output,word+": "+i+"\n");
    				num++;
    			}
    		}
        }
		
		
	}
	
	public void writeToText(File file,String str) 
	{
		try 
		{	   
			FileWriter writer=new FileWriter(file, true);
			writer.write(str);
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void clearText(File file) //clear output.txt
	{
		try 
		{	   
			FileWriter writer=new FileWriter(file);
			writer.write("");
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void process(File input,File output)
	{
		clearText(output);
		
		int countChar1=countChar(input);
		int countWord1=countWord(input);
		int countLine1=countLine(input);
		
		System.out.println("characters: "+countChar1);
		System.out.println("words: "+countWord1);
		System.out.println("lines: "+countLine1);
		
		writeToText(output,"characters: "+countChar1+"\n");
		writeToText(output,"words: "+countWord1+"\n");
		writeToText(output,"lines: "+countLine1+"\n");
		
		outputWords(has,output);
	}


}
