import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore.Entry;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Count {
	static int count_char=0;//字符数
	static int count_word=0;//单词数
	static int count_line=0;//有效行数
	static int count_Word_Frequency=10;//输出词频最高的单词数
	static Map<String,Integer> map = new HashMap<String,Integer>();//记录单词出现的次数
	static String input;
	static String output;
	//计算十个词频最高的单词并写入文件
	static void countWordFrequency() throws FileNotFoundException
	{
		List<String> result = new ArrayList<>();
        List<Map.Entry<String,Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>()
        {	//对两个value进行比较
        	public int compare(Map.Entry<String,Integer>e1,Map.Entry<String,Integer>e2)
        	{
                int re = e2.getValue().compareTo(e1.getValue());
                if(re!=0)
                	return re;
                else
                	return e1.getKey().compareTo(e2.getKey());
            }
        });
        //将比较后的结构加入result中
        for(int i=0;i<map.size();i++)
            result.add(list.get(i).getKey());
        //将所有所需数据输出到文件中
        File file = new File(output);		
		try (PrintWriter output = new PrintWriter(file);) {
			output.println("characters:"+count_char);
			output.println("words:"+map.size());
			output.println("line:"+count_line);
			for(int i=0;i<count_Word_Frequency;i++)
				output.println(list.get(i).getKey()+":"+list.get(i).getValue());
		}
	}
	public static void main(String[] args) throws Exception {
		//用户输入要读取和输出的文件路径
		Scanner doc = new Scanner(System.in);	
		input = doc.nextLine();
		output = doc.nextLine();	
		File file = new File(output);		
		if(!file.exists())  
	    {  
	        try {  
	            file.createNewFile();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    } 
		FileInputStream fis = new FileInputStream(input);
    	//创建类进行文件的读取
    	InputStreamReader read = new InputStreamReader(fis);    	
    	//可用于读取指定文件   
        BufferedReader in = new BufferedReader(read);  
        String str=null;//定义一个字符串类型变量str
        String regxSpace = "\\s*";//正则表示空格、空行等
        //readLine()方法, 用于读取一行,只要读取内容不为空就一直执行   
        while ((str = in.readLine())!= null) 
        {	
        	//每行字符数相加
        	count_char += str.length();  
        	//当不为空行时，行数加一
        	if(!str.matches(regxSpace))
        		count_line++;
        	//统计单词数并统计单词出现次数
        	String[]ss = str.split("\\s+|\\W");//表示空格、空行或者非数字字母的正则进行分隔
        	for(String s:ss)
			{
        		if(s.matches("[a-z,A-Z]{4,}.*")) //判断分割后的字符串是否是四个字母开头的单词
        		{//map统计这些单词
	        		if(map.containsKey(s.toLowerCase()))  //toLowerCase忽视大小写      			
						map.put(s.toLowerCase(), map.get(s.toLowerCase())+1);
					else
						map.put(s.toLowerCase(), 1);
        		}
			}
        }      
        countWordFrequency();      
        //关闭fis
        fis.close();
	}
	
}

