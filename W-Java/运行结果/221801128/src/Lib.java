import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Lib {
	static HashMap<String, Integer> hash=new HashMap<String,Integer>();//存储键值对数据的结构
	/**
	 * 统计单词并存储
	 */
	public static int countWords(String str) {
		int wordcnt = 0;//单词数
		String[] wordStr = str.split("[^a-zA-Z0-9]");//利用分隔符分割字符串
		Pattern pattern = Pattern.compile("^[a-z]{4}[a-z0-9]*");//由于判断是否符合有效单词的规定
		Matcher matcher = null;
		for (String word : wordStr) {
			word = word.toLowerCase();
			matcher = pattern.matcher(word);
			if (!word.equals("") && matcher.find()) {
				wordcnt++;
				if(hash.containsKey(word)) {
					hash.put(word, hash.get(word) + 1);
				}
				else {
					hash.put(word, 1);
				}
			}
		}
		return wordcnt;
	}
		/**
		 * 统计总字符数
		 */
		public static int countChar(String str) throws IOException {
			return str.length();
		}
		/**
		 * 统计有效行数
		 * @throws IOException 
		 */
		  public static int countLines(String str) throws IOException {
			  int lines = 0;
			  String[] strLine = str.split("\r\n|\n");//依据换行符分割字符串
			  for (String ch : strLine) {
				  if (!ch.replaceAll("\r|\n", "").trim().equals("")) {
					  lines++;
				  }
			  }
			  return lines;
		  }

		/**
		 * 按词频以及字典序排序
		 */
		public static List<HashMap.Entry<String, Integer>> getSortedList(HashMap<String, Integer> wordMap) {
			List<HashMap.Entry<String, Integer>> list = new ArrayList<HashMap.Entry<String, Integer>>(wordMap.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {//排序规则
				public int compare(Map.Entry<String,Integer> hash1, Map.Entry<String,Integer> hash2){
					if(hash1.getValue().equals(hash2.getValue()))
						return hash1.getKey().compareTo(hash2.getKey());
					return hash2.getValue().compareTo(hash1.getValue());
				}
			});
			return list;
		}
}
