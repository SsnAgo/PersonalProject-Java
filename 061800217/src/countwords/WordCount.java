package countwords;
import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class WordCount {

	public static void main(String args[]) throws IOException {
		
		if(args.length !=2) {
			System.out.print("参数输入有误！");
			return;
		}
		String file_in = args[0];
		String file_out = args[1];
		
		HashMap<String, Integer>word_freq = new HashMap<String,Integer>();
		int result = 0,word = 0,line = 0;
		String file_path = FileIO.readFile(file_in);		
		if(file_path.compareTo("error")!=0) {			
			try {
				result = DoCount.countCharacters(file_path);
				word = DoCount.wordNum(file_path,word_freq);
				line = DoCount.countLine(file_path);
			} catch (Exception e) {
				System.out.print("error");
				return;
				// TODO: handle exception
			}
		}else {
			System.out.print("文件不存在或类型错误！");
			return;
		}
		//输出
		String out_word ="characters:"+result
				+"\nwords:"+word
				+"\nline:"+line;
		boolean write_in = FileIO.writeFile(file_out, out_word);
		Map<String, Integer>out_words = DoCount.sortWords(word_freq);
		write_in = FileIO.writeFile("output.txt", out_words);
	}


}


