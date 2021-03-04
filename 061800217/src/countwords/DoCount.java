package countwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DoCount {
	//统计行数
	public static int countLine(String path) throws IOException{
		String string;
		int line = 0;
		FileReader fReader = new FileReader(path);
		BufferedReader bReader = new BufferedReader(fReader);
		while((string = bReader.readLine())!=null)
		{	
			line++; 
		}
		fReader.close();
		bReader.close();
		return line;
	}
	//统计文件的字符数	
	public static int countCharacters (String path) throws IOException {
		int num = 0;
		int len = 0;
		char[] a = new char[500];
		String str;
		FileReader fReader = new FileReader(path);
		BufferedReader bReader = new BufferedReader(fReader);	
		while((len = bReader.read(a))!=-1)
		{
				num = len;
		}
		fReader.close();
		bReader.close();
		return num;	
	}
	//统计单词
	public static int wordNum(String path,HashMap<String, Integer> word_freq)throws IOException{
		int total = 0;
		String string;
		ArrayList array = new ArrayList();
		FileReader fReader = new FileReader(path);
		BufferedReader bReader = new BufferedReader(fReader);
		while((string = bReader.readLine())!=null)
		{
			String[] str1 = string.toLowerCase().split("\\s+"); // 按空格分割
			array.add(str1);
		}
		fReader.close();
		bReader.close();		
		String rule = ".*[a-z]+.*" ;
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(rule);
		Iterator iterator = array.iterator();
		while (iterator.hasNext()) {
			String[] s = (String[])iterator.next();
			for(int y = 0;y<=s.length-1;y++) {
				java.util.regex.Matcher m = p.matcher(s[y]);
				if(m.matches()) {
					total++;
					if(word_freq.get(s[y]) == null) {
						word_freq.put(s[y], 1);
					}
					else {
						int old_value = word_freq.get(s[y]);
						old_value++;
						word_freq.replace(s[y], old_value);
					}
				}
			}
		}
		total = word_freq.size();
		return total;
	}
	//排序
public static Map<String, Integer> sortWords(HashMap<String, Integer>word_freq) {
	HashMap<String, Integer> word_result = new HashMap<>();
	Map<String,Integer>sort_mapMap=word_freq.entrySet()
            .stream()
            .sorted(Collections
                    .reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors
                    .toMap(Map.Entry::getKey,
                    		Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));
	return sort_mapMap;
	}
}
