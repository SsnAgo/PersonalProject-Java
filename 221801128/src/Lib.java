import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Lib {
	
	static int readline;
	static int len = 0;
	static String word = "";
	static int x = 0;//前一位是数字变1
	static int num = 0;
	static ArrayList<String> words = new ArrayList<String>();
	static ArrayList<Integer> value = new ArrayList<Integer>();
		
	public static char toLower(char ch){//转化为小写字母
		if(ch >= 'A' && ch <= 'Z'){
			return (char) ((ch-'A')+'a');
		} 
		return ch;
	}
	
	public static void towords(String word) {//将单词加入数组
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
			}
		}	
	}
	
	public static void sortWords() {//按频率从大到小排序
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
	
	public static int countWords() {//统计总单词数
		int countnum = 0;
		for(int i = 0;i<value.size();i++) {
			countnum+=value.get(i);
		}
		return countnum;
	}
	
	   public static int countLines(File file) {//统计有效行数
	        int countline = 0;
	        try {
	            Scanner in = new Scanner(file);
	            while (in.hasNextLine()) {
	                String str = in.nextLine();
	                if (!str.equals("")) {
	                    countline++;
	                }
	            }
	            in.close();
	        } catch (FileNotFoundException e) {
	            System.out.println("读取失败");
	            e.printStackTrace();
	        }
	        return countline;
	    }
	   
	  public static void solve(BufferedReader br) throws IOException {
		  while((readline = br.read())!= -1) {
				char c =Lib.toLower((char)readline);
				if(!String.valueOf(c).matches("[\u4e00-\u9fa5]")) {
					num++;
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
							Lib.towords(word);
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
						Lib.towords(word);
						word = "";
						len = 0;
						x = 0;
					}
				}
			}
			if(readline == -1 && len>=4) {
				Lib.towords(word);
			}
	  }
		
	 public static void printall(String inputfile) {
		 
			System.out.println("字符数:"+num);//输出总字符数
			System.out.println("单词总数:"+Lib.countWords());
			System.out.println("有效行数:"+Lib.countLines(new File(inputfile)));
			System.out.println("单词的出现次数:（前十）");
			
			for(int i = 0;i<Lib.words.size();i++) {
				if(i>=10) {
					break;
				}
				System.out.println(Lib.words.get(i).toString()+""+":"+Lib.value.get(i));
			}
			
	 }
	 
	 public static void writeIn(String inputfile,String outputfile) throws IOException {
		 Path path1 = Paths.get(outputfile);
		BufferedWriter writer = null;
		try {
			writer = Files.newBufferedWriter(path1, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write("字符数:"+Lib.num+"\n");//写入总字符数
		writer.write("单词总数:"+Lib.countWords()+"\n");//写入单词总数
		writer.write("有效行数:"+Lib.countLines(new File(inputfile))+"\n");//写入总行数      
		writer.write("单词的出现次数:（前十）"+"\n");
		for(int i = 0;i<Lib.words.size();i++) {//写入频率前十的单词及频率
			if(i>=10) {
				break;
			}
			writer.write(Lib.words.get(i).toString()+""+":"+Lib.value.get(i)+"\n");
		}
		writer.close();
	 }
}
