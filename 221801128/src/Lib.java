import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Lib {
	static HashMap<String, Integer> hash=new HashMap<String,Integer>();
	/**
	 * 统计单词并存储
	 */
	public static int countWords(String str) {
		int cnt = 0;
		Pattern pattern = Pattern.compile("^[a-z]{4}[a-z0-9]*");
		String[] wordStr = str.split("[^a-zA-Z0-9]");
		Matcher matcher = null;
		for (String word : wordStr) {
			word = word.toLowerCase();
			matcher = pattern.matcher(word);
			if (!word.equals("") && matcher.find()) {
				cnt++;
				if(hash.containsKey(word)) {
					hash.put(word, hash.get(word) + 1);
				}
				else {
					hash.put(word, 1);
				}
			}
		}
		return cnt;
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
			  String[] strLine = str.split("\r\n|\n");
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
			Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
				public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
					if(o1.getValue().equals(o2.getValue()))
						return o1.getKey().compareTo(o2.getKey());
					return o2.getValue().compareTo(o1.getValue());
				}
			});
			return list;
		}
}
