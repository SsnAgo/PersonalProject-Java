import java.util.Random;
import java.util.*;
import java.io.*;
import java.util.HashMap;

public class Lib 
{
	HashMap<String,Integer> hashMap=new HashMap<String,Integer>(); //统计词频
	
	public int countChar(File file)	//统计字符数
	{
		int i=0; //记录字符数
		try 
		{		
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
			while((ch=(char)bfr.read())!=(char)-1) //按字符读取文本内容
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
		int count=0;
		try 
		{		
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
				
			String str;
			while((str=bfr.readLine())!=null)
			{
				char[] c=str.toCharArray();
				for(int i=0;i<c.length;i++) 
				{
					if (c[i]!='\n' && c[i]!='\r' && c[i]!='\t') 
					{
						count++;
						break;
					}
				}
			}
			bfr.close();
			fr.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
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
			    	addWordToHash(hashMap,strs[i]); //是单词则加入统计用的hashMap
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
	
	public void addWordToHash(HashMap<String,Integer> hash,String str) //把字符串加入一个HashMap
	{
		//全部置为小写
		str=str.toLowerCase();
		
		if (!hash.containsKey(str)) //若尚无此单词
		{ 
			hash.put(str,1);
		} 
		else //如果有，就将次数加1
		{
			hash.put(str,hash.get(str)+1);
		}
	
	}
	
	public void addWordToTree(TreeMap<String,Integer> tree,String str) //把字符串加入一个TreeMap
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
	
	public String countWordFrequency(HashMap<String,Integer> hash) //按频率输出
	{
		String result="";
		//遍历map
		Iterator iterator=hash.keySet().iterator();
		int maxOccur=0;
		while(iterator.hasNext())
		{
			String word=(String) iterator.next();
			if (hash.get(word)>maxOccur)
				maxOccur=hash.get(word);
		}
		//System.out.println("最多出现："+maxOccur);	
		
		//构造初始化TreeMap数组
		TreeMap[] wordArray=new TreeMap[maxOccur+1];
		for (int i=0;i<=maxOccur;i++) 
		{
			wordArray[i]=new TreeMap<String,Integer>();
		}
		
		//增改TreeMap数组
		iterator=hash.keySet().iterator(); //重置迭代器
		while(iterator.hasNext())
		{
			String word=(String)iterator.next();
			addWordToTree(wordArray[hash.get(word)],word);
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
					//System.out.println(word+": "+i);
					result+=word+": "+i+"\n";
					num++;
				}
			}
		}
		
		return result;
	}
	
	public void writeToText(File file,String str) //追加写入 output.txt
	{
		try 
		{	   
			FileWriter fw=new FileWriter(file,true); //第二个参数true则为append方式打开文件
			BufferedWriter bfw=new BufferedWriter(fw);
			bfw.write(str);
				
			bfw.close();
			fw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void clearText(File file) //清空 output.txt
	{
		try 
		{	   
			FileWriter writer=new FileWriter(file); //没有第二个参数true则为写方式打开
			writer.write("");
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void process(File input,File output) //汇总输出
	{
		//输出前清空原有内容
		clearText(output);	
		
		//统计文本
		int countChar1=countChar(input);
		int countWord1=countWord(input);
		int countLine1=countLine(input);
		String countWordFrequency1=countWordFrequency(hashMap); 
		
		//合成结果
		String result="";
		result+="characters: "+countChar1+"\n";
		result+="words: "+countWord1+"\n";
		result+="lines: "+countLine1+"\n";
		result+=countWordFrequency1;
		
		//输出结果
		writeToText(output,result);
		
	}

}
