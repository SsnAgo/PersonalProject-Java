import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;

public class Count {
	//定义三个函数，分别用来将字符数、单词数、有效行数、十个词频最高的单词写入文件
	static void countChar(FileInputStream fis) throws IOException 
	{
		int count_char=0;
	}
	void countWord(FileInputStream fis)
	{
		int count_word=0;
	}
	void countLine(FileInputStream fis) 
	{
		int count_line=0;
	}
	void countWordFrequency(FileInputStream fis)
	{
		
	}
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("C:\\Users\\ling\\Desktop\\新建文本文档.txt");
    	//创建类进行文件的读取
    	InputStreamReader read = new InputStreamReader(fis);    	
    	//可用于读取指定文件   
        BufferedReader in = new BufferedReader(read);  
        String str=null;//定义一个字符串类型变量str
        int count_char=0;//用于统计总字符数 
        int count_line=0;
        String regxSpace = "\\s*";
        Map<String,Integer> map = new HashMap<String,Integer>();
        while ((str = in.readLine())!= null) 
        {//readLine()方法, 用于读取一行,只要读取内容不为空就一直执行   	
        	count_char += str.length();  
        	//当不为空行时，行数加一
        	if(!str.matches(regxSpace))
        		count_line++;
        	//统计单词数并统计单词出现次数
        	String[]ss = str.split("\\s+|\\W");
        	for(String s:ss)
			{
        		if(s.matches("[a-z,A-Z]{4,}.*")) {
        		if(map.containsKey(s))
					map.put(s, map.get(s)+1);
				else
					map.put(s, 1);
        		}
			}
        }
        //输出所有单词数
        Set<String>keys = map.keySet();
        for(String key : keys)
        	System.out.println(key + "有：" + map.get(key) + "个.");
        System.out.print(count_line);
        System.out.print(count_char);
        fis.close();
	}
}
