import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
	public static void main(String[] args) throws IOException{
		File f1=new File("C:\\Users\\31139\\workspace\\WordCount\\src\\input.txt");
		FileWriter writer=new FileWriter("C:\\Users\\31139\\workspace\\WordCount\\src\\output.txt");
		//File f1=new File(args[0]);
		//FileWriter writer=new FileWriter(args[1]);
		
		//统计文件的字符数（对应输出第一行）：只需要统计Ascii码，汉字不需考虑,空格，水平制表符，换行符，均算字符
		try{
			int num=getCharNum(f1);
			writer.write("characters："+num);
			System.out.println("characters："+num);
		}
		catch(IOException exc){
			System.out.println("File error!");
		}
		
		//统计文件的单词总数（对应输出第二行）
		String[] linewords= {};
		try {
			String toLine=turnToLine(f1);
			linewords=splitLine(toLine);
			writer.write("words："+countWords(linewords));
			System.out.println("words："+countWords(linewords));
		}
		catch(IOException exc){
			System.out.println("File error!");
		}
		
		//统计文件的有效行数（对应输出第三行）：任何包含非空白字符的行，都需要统计。
		try{
			int line=getLine(f1);
			writer.write("lines："+line);
			System.out.println("lines："+line);
		}
		catch(IOException exc){
			System.out.println("File error!");
		}
		
		//统计文件中各单词的出现次数（对应输出接下来10行），最终只输出频率最高的10个。
		setFrequency(linewords,writer);

	}
	
	//实现统计文件的字符数的功能
	public static int getCharNum(File file) throws FileNotFoundException {
		InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file));
		int num=0;
		try {
			while(inputStreamReader.read()!=-1)
			{
				num++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	//实现统计文件行数的功能
	public static int getLine(File file){
		int line=0;
		try {
			if(file.exists()) {
				long fileLength=file.length();
				FileReader fileReader=new FileReader(file);
				LineNumberReader lineNumberReader=new LineNumberReader(fileReader);
				lineNumberReader.skip(fileLength);
				line=lineNumberReader.getLineNumber();
				lineNumberReader.close();
			}
			else
				System.out.println("file does not exist.");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return line;
	}
	
	//将文件转化为一个字符串 每行之间用空格分开
	public static String turnToLine(File file) {
		BufferedReader bufferedReader=null;
		try {
			bufferedReader=new BufferedReader(new FileReader(file));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		try {
			while((line = bufferedReader.readLine()) != null) {
				stringBuffer = stringBuffer.append(line+' ');
			}
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} 
		catch(IOException e1) {
			e1.printStackTrace();
		}
		String sb=stringBuffer.toString().toLowerCase();
		return sb;
	}
		
	//分隔每个单词
	public static String[] splitLine(String str){
		String[] linewords=str.split("\\W+");
		return linewords;
	}
		
	//判断是否为合法单词，统计合法单词个数
	public static int countWords(String[] linewords) {
		int cnt=0;
		Pattern pattern = Pattern.compile("[a-zA-Z]{4}([a-zA-Z0-9])*");
		for(int i=0;i<linewords.length;i++) {
			Matcher matcher = pattern.matcher(linewords[i]);
			if(matcher.find()) {
				//System.out.println(matcher.group());
			 	cnt++;
			}
		}
		return cnt;
	}
	
	//用hashmap统计词频
	public static void setFrequency(String[] linewords,FileWriter writer) {
		Map<String,Integer> hashMap=new HashMap<String,Integer>();
		Set<String> wordSet=hashMap.keySet();
		Pattern pattern = Pattern.compile("[a-zA-Z]{4}([a-zA-Z0-9])*");
		for(int i=0;i<linewords.length;i++) {
			Matcher matcher = pattern.matcher(linewords[i]);
			if(matcher.find()) {
				String word=matcher.group();
				//如果已经有这个单词了，
				if(wordSet.contains(word)) {
					Integer number=hashMap.get(word);
					number++;
					hashMap.put(word, number);
				}
				else {
					hashMap.put(word, 1);
				}
			}
		}
		//排序
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if(o2.getValue()!=o1.getValue()) {
		       		return o2.getValue().compareTo(o1.getValue());//value不同逆序排序
		       	}
		       	else
		       		return -o2.getKey().compareTo(o1.getKey());//value相同按照key字典序正序排序
			}
		});
		//输出前十个单词
		if(list.size()<=10) {
			for (int i = 0; i < list.size(); i++) {
				try {
					writer.write(list.get(i).getKey()+": "+list.get(i).getValue());
					System.out.println(list.get(i).getKey()+": "+list.get(i).getValue());
				}
				catch(IOException exc){
					System.out.println("File error!");
				}
			}   
		}
		else {
			for (int i=0;i<10;i++) {
				try {
					writer.write(list.get(i).getKey()+": "+list.get(i).getValue());
					System.out.println(list.get(i).getKey()+": "+list.get(i).getValue());
				}
				catch(IOException exc){
					System.out.println("File error!");
				}
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
