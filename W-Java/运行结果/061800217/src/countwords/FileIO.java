package countwords;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileIO {
	public static String readFile(String file_path) throws IOException {
		String error = "error";
		File file = new File(file_path);
		boolean isfile = file.isFile();
		boolean exist = file.exists();
		if(isfile&&exist) {
			return file.getAbsolutePath();
		}
		return error;
	}
	public static boolean writeFile(String file_path,String out_word) throws IOException{
		File file_out = new File("output.txt");
		FileWriter writer = new FileWriter(file_out); 
		writer.write(out_word);
		writer.flush(); 
		writer.close();
		return true;
		
	}
	public static boolean writeFile(String file_path, Map<String, Integer>word_freq)throws IOException {
		File file_out = new File(file_path);
		FileWriter writer = new FileWriter(file_out,true); 
		int count = 0;
		//更高效的方法
		Set<Map.Entry<String, Integer>> mSet = word_freq.entrySet();
		for(Map.Entry<String, Integer> entry :mSet) {
			writer.write("\n");
			writer.write(entry.getKey()+": "+entry.getValue());
			if(++count == 10)break;
		}
		writer.flush(); 
		writer.close();
		return true;
		
	}
}
