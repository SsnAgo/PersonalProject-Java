import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lib {
	static Map<String,Integer> wordsMap = new TreeMap<>();//单词和频率的映射表	

	/*
	 * 功能：统计文件字符数
	 * 输入：File文件指针
	 * 输出：该文件中含有的字符数
	 */
	 static int statisticsCharacters(File file) {
		int characterNum = 0;
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader in = new BufferedReader(read);
			String str = null;
			while ((str = in.readLine()) != null){
				for (int i = 0;i<str.length();i++) {
					int characterAscill=str.charAt(i);
					if (characterAscill >=0 && characterAscill <= 127) {
						characterNum++;
					}
				}
				characterNum++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return characterNum;
	}
	 
	 /*
	  * 功能：通过正则表达式对单词进行捕获
	  * 输入：File文件指针
	  * 输出：该文件中含有的单词数
	*/
	 static int statisticsWords(File file) {
		int wordNum = 0;
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader in = new BufferedReader(read);
			String str = null;
			while ((str = in.readLine()) != null){
				
				//通过正则表达式对每一行的字符串进行匹配查找，若存在符合条件的单词则加入wordsMap
				String ragex = "[a-zA-Z]{4,}[a-zA-Z0-9]+";
				Pattern p = Pattern.compile(ragex);
				Matcher m = p.matcher(str);
				while (m.find()) {
					String s = m.group();
					s = s.toLowerCase();
					if (wordsMap.containsKey(s)) {
						int num = wordsMap.get(s);
						wordsMap.put(s, num + 1);
					}else {
						wordsMap.put(s, 1);
					}
					wordNum++;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wordNum;
	 }
}
