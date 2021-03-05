import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lib {
	public String readTextFile(String filePath) {			//文件的读取
		StringBuilder Text = new StringBuilder();
		try {
			File file=new File(filePath);
			if(file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),
						"UTF-8");
				BufferedReader bufferedReader = new BufferedReader(read);
				int x;
				while((x=bufferedReader.read()) != -1) {
					Text.append((char)x);
				}
				read.close();

			}else {
				System.out.println(filePath+"文档不存在！");
			}
		}catch (Exception e) {
			System.out.println("打开文档出错！");
			e.printStackTrace();
		}
		return Text.toString();
	}
	
	
	
	
	public void writeTextFile(int chars,int words,int lines,
			String times,String filePath) {	//把所有统计的结果输出
		String str = "charcters:" + chars + "\nwords:" + 
			words + "\nlines:"+lines + "\n" + times;
		FileOutputStream p;
		try {
			p = new FileOutputStream(filePath);
			p.write(str.getBytes());
			p.close();
		}catch (IOException e) {
			System.out.print("输出文件时出错！");
			e.printStackTrace();
		}
	}
	
	
	
	
	public static int countChars(String str) {	//统计字符数量
		int sum = 0;
		char[] cs = str.toCharArray();
		for(int i = 0;i < cs.length; i++ ) {
			if(cs[i] >= 0 && cs[i] < 128) sum++ ;
		}
		return sum;
	}

	
	
	
	public static int countWords(String str) {	//统计单词数量
		str=str.toLowerCase();
		String[] strArray=str.split("[^a-z0-9]+");	//把可能是单词的字符串切割出来
		
		int words = 0;
		for(int i = 0;i < strArray.length; i++ ) {
			if(strArray[i].length() < 4)continue;
			else {
				String temp=strArray[i].substring(0,4);	//检测是否符合规则
				if(temp.matches("[a-z]*")) {			
					words++;
				}
			}
		}
		return words;
	}
    
	
	
    
	public static int countLines(String str) {	//统计行数
		String[] LINE = str.split("\\s");	//把可能换行的地方分割出来
		int lines = LINE.length;
		for(int i = 0;i < LINE.length; i++ ) {	//减去空白行
			if(LINE[i].isEmpty()) lines-- ;
		}
		return lines;
	}
	
	
	
	
	public String countTimes(String str) {	//统计单词出现次数
		str = str.toLowerCase();
		String[] strArray = str.split("[^a-z0-9]+");
		List<String> temp = new ArrayList<String>();	//temp用于保存符合规则的字符串
		for(int i = 0;i < strArray.length; i++ ) {
			if(strArray[i].length() < 4)continue;
			else {
				String strtemp=strArray[i].substring(0,4);
				if(strtemp.matches("[a-z]*")) {
					temp.add(strArray[i]);
				}
			}
		}
		
		strArray=temp.toArray(new String[temp.size()]);
		Map<String,Integer> map = new HashMap<String,Integer>();
		for (int i = 0; i < strArray.length; i++ ) {	//把符合规则的单词存入map，key是单词，value是次数
			if(map.get(strArray[i]) == null) {	
    			map.put(strArray[i],1);
			}else{
				map.put(strArray[i],map.get(strArray[i]) + 1);
			}
		}
		
		Set<String> key = map.keySet();	//获取所有keyֵ
		StringBuilder finalstr=new StringBuilder();	//finalstr用于记录最后结果
		while(!map.isEmpty()) {	//循环比较最大value的单词记录到finalstr中
			int i = 0,maxvalue = 0;							
			String maxstr = " ";							
			for(String s:key) {
				int v = map.get(s);
				if(v > maxvalue) {
					maxvalue = v;
					maxstr =s ;
				}
				else if(v == maxvalue) {	//如果value的值相同就比较ASCII码
					if(maxstr.compareTo(s) > 0) {
						maxvalue = v;
						maxstr = s;
					}
				}
			}
			finalstr.append(maxstr + ":" + maxvalue + "\n");
			map.remove(maxstr);
			if(i == 9)break;	//如果finalstr中的单词数量已经有10个了就退出循环
		}
		return finalstr.toString();
	}
}