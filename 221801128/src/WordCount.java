
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCount {
	static String inputfile;
	static String outputfile;
	//static String[] words;
	static ArrayList<String> words = new ArrayList<String>();
	static ArrayList<Integer> value = new ArrayList<Integer>();
	static int count = 0;
	//static int[] value;
	static int num = 0;
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String[] strArr = input.split(" ");
		inputfile = strArr[0];
		outputfile = strArr[1];
		in.close();
		
		File dir = new File("");
		inputfile = dir.getCanonicalPath()+"\\"+inputfile;
		outputfile = dir.getCanonicalPath()+"\\"+outputfile;
		System.out.println(inputfile);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//words = new String[100];
		//value = new int[100];
		int readline;
		int len = 0;
		String word = "";
		int x = 0;//前一位是数字变1
		int numOfLine = 1;
//		while(br.readLine()!=null) {
//			numOfLine++;
//		}
		while((readline = br.read())!= -1) {
			char c = toLower((char)readline);
			if(!String.valueOf(c).matches("[\u4e00-\u9fa5]")) {
				num++;
			}
			if(c == '\n') {
				numOfLine++;
			}
			if(len<4) {
				if(c>='a' && c<='z') {
					if(x == 0) {
						word+=c;
						len+=1;
					}
					else {
						len = 0;
						word = "";
						x = 0;
					}
				}
				else if(c>='0' && c<='9'){
					len = 0;
					word = "";
					x = 1;
				}
				else {
					len = 0;
					word = "";
					x = 0;
				}
				
				
			}
			else if(len>=4){
				if(c>='a' && c<='z') {
					if(x == 0) {
						word+=c;
						len+=1;
					}
					else {
						towords(word);
						word = "";
						len = 0;
						x = 0;
					}
				}
				else if(c>='0' && c<='9') {
					if(x == 0) {
						x = 1;
						word = word+""+c;
						len+=1;
					}
					else {
						word = word+""+c;
						len+=1;
					}
				}
				else if(!((c>='a' && c<='z')||(c>=0 && c<=9))) {
					towords(word);
					word = "";
					len = 0;
					x = 0;
				}
			}
				
			
		}
		if(readline == -1) {
			towords(word);
		}
		br.close();
		System.out.println(num);//输出总字符数
		sortWords(words,value);//排序从大到小
		for(int i = 0;i<words.size();i++) {
			if(i>=10) {
				break;
			}
			System.out.println(words.get(i).toString()+""+":"+value.get(i));
		}

		Path path1 = Paths.get(outputfile);
		BufferedWriter writer = Files.newBufferedWriter(path1, StandardCharsets.UTF_8);
		writer.write("字符数:"+num+"\n");//写入总字符数
		writer.write("单词总数:"+words.size()+"\n");//写入单词总数
		writer.write("有效行数:"+numOfLine+"\n");//写入总行数      
		writer.write("单词的出现次数:"+"\n");
		for(int i = 0;i<words.size();i++) {//写入频率前十的单词及频率
			if(i>=10) {
				break;
			}
			writer.write(words.get(i).toString()+""+":"+value.get(i)+"\n");
		}
		writer.close();   
		
//		catch(IndexOutOfBoundsException e) {
//			e.printStackTrace();
//		}

	}
	public static char toLower(char ch){
		if(ch >= 'A' && ch <= 'Z'){
			return (char) ((ch-'A')+'a');
		} 
		return ch;
	}
	public static void towords(String word) {
		int m = 0;
		if(!word.equals(null)) {
			for(int i = 0;i<words.size();i++) {
				if(word.equals(words.get(i).toString())) {
					int y = (int)value.get(i) + 1;
					value.set(i, y);
					m = 1;
				}
			}
			if(m == 0) {
				words.add(word);
				value.add(1);
//				words[count] = word;
//				value[count] = 1;
			}
		}
		
	}
	
	public static void sortWords(ArrayList<String> words,ArrayList<Integer> value) {
		for(int i = 0;i<words.size()-1;i++) {
			for(int j = 1;j<words.size();j++) {
				if(value.get(i)<value.get(j)) {
					int x = value.get(i);
					int y = value.get(j);
					value.set(j, x);
					value.set(i, y);
					String word1 = words.get(i).toString();
					String word2 = words.get(j).toString();
					words.set(j, word1);
					words.set(i, word2);
				}
			}
		}
	}
}


