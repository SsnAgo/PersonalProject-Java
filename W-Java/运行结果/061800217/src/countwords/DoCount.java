package countwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import java.util.TreeMap;
public class DoCount {
	//统计行数
	public static int countLine(String path) throws IOException{
		String string;
		int line = 0;
		FileReader fReader = new FileReader(path);
		BufferedReader bReader = new BufferedReader(fReader);
		while((string = bReader.readLine())!=null)
		{	
			++line; 
		}
		fReader.close();
		bReader.close();
		return line;
	}
	//统计文件的字符数	
	public static Map<String, Integer> countCharacters (String path) throws IOException {
		int num = 0;
		int len = 0;
		Map<String, Integer>result = new HashMap<String, Integer>();
		char[] a = new char[50000];
		FileReader fReader = new FileReader(path);
		BufferedReader bReader = new BufferedReader(fReader);	
		while((len = bReader.read(a))!=-1)
		{
				num = len;
		}
		String str = String.valueOf(a);
		fReader.close();
		bReader.close();
		result.put(str, num);
		return result;	
	}
	//统计单词
	public static int wordNum(String path,String word_str,HashMap<String, Integer> word_freq)throws IOException{
		int total = 0;
		String[] word_split;
		word_split = word_str.toLowerCase().split("\\s+");
		
		String rule = ".*[a-z]{4}+.*" ;
		for(String str:word_split) {
			boolean is_match = Pattern.matches(rule, str);
			if(is_match) {
				total++;
				if(word_freq.get(str) == null) {
					word_freq.put(str, 1);
				}
				else {
					int old_value = word_freq.get(str);
					old_value++;
					word_freq.replace(str, old_value);
				}
			}
		}		
		total = word_freq.size();
		return total;
	}
	//排序
public static Map<String, Integer> sortWords(HashMap<String, Integer>word_freq) {
	Map<String,Integer>sort_mapMap=word_freq.entrySet()
            .stream()
            .sorted(Collections
                    .reverseOrder(new Comparator<Entry<String, Integer>>(){
                    	@Override
                    	public int compare(Map.Entry<String,Integer> o1
                    			, Map.Entry<String,Integer> o2) {
                    		if (o1.getValue()<o2.getValue()) {
								return -1;
							}else if(o1.getValue() > o2.getValue()) {
								return 1;
							}else {
								String a = o1.getKey();
								String b = o2.getKey();
								if(strcmp(a,b) > 0)
								{
									return -1;
								}else if(strcmp(b, a) < 0) {
									return 1;
								}else {
									return 0;
								}
							}
                    	}

						private int strcmp(String a, String b) {
							char a_char[] = a.toCharArray();
							char b_char[] = b.toCharArray();
							int flag = 0;
							int n = a_char.length;
							if (a_char.length > b_char.length) {
								n = b_char.length;
							}
							for (int i = 0; i < n; i++) {
								if(a_char[i] == b_char[i]) {
									continue;
								}else if (a_char[i] < b_char[i]) {
									flag =  -1;
									break;
								}else {
									flag = 1;
									break;
								}
							}
							return flag;
						}
                    }))
            .collect(Collectors
                    .toMap(Map.Entry::getKey,
                    		Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));
	return sort_mapMap;
	}

}

