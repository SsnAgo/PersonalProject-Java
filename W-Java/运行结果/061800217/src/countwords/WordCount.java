package countwords;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordCount {
	public static void main(String args[]) throws IOException {
		if(args.length !=2) {
			System.out.print("参数输入有误！");
			return;
		}
		String file_in = args[0];
		String file_out = args[1];
		
		String word_str = "";
		HashMap<String, Integer>word_freq = new HashMap<String,Integer>();
		Map<String, Integer>word_map = new HashMap<>();
		int result = 0,word = 0,line = 0;
		String file_path = FileIO.readFile(file_in);		
		if(file_path.compareTo("error")!=0) {			
			try {								
				word_map = DoCount.countCharacters(file_path);
				Set<String> keys = word_map.keySet();
				for(Object key:keys) {
					result = word_map.get(key);
					word_str = (String) key;
				}
				word = DoCount.wordNum(file_path,word_str,word_freq);
				line = DoCount.countLine(file_path);
			} catch (Exception e) {
				System.out.print("error1");
				return;
				// TODO: handle exception
			}
		}else {
			System.out.print("文件不存在或类型错误！");
			return;
		}
		//输出
		String out_word ="characters: " + result
				+ "\nwords: " + word
				+ "\nlines: " + line;
		boolean write_in = FileIO.writeFile(file_out, out_word);
		Map<String, Integer>out_words = DoCount.sortWords(word_freq);
		write_in = FileIO.writeFile("output.txt", out_words);
	}
}


