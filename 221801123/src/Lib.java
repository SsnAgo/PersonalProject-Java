import sun.nio.ch.IOUtil;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统计工具
 */
public class Lib {

	//存放单词与其对应出现的次数
	static HashMap<String, Integer> mp = null;

	/**
	 * 统计总字符数
	 */
	static int countCharNum(String str){
		return str.length();
	}

	/**
	 * 统计空白行数
	 */
	static int countValidLineNum(String str) {
		int lineNum = 0;
		Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");
		Matcher matcher = linePattern.matcher(str);
		while (matcher.find()) {
			lineNum++;
		}
		return lineNum;
	}

	/**
	 * 统计单词数,并统计单词对应个数
	 */
	static int countWordNum(String str) {
		//单词数
		int wordNum = 0;
		//全部默认为小写
		str = str.toLowerCase();
		String[] words = str.split("[^a-z0-9]+");
		mp = new HashMap<>();
		for (String word : words) {
			if (word.matches("[a-z]{4,}[a-z0-9]*")) {
				wordNum++;
				if (mp.containsKey(word)){
					int n = mp.get(word);
					mp.put(word, n+1);
				}
				else {
					mp.put(word, 1);
				}
			}
		}
		return wordNum;
	}

	/**
	 * 对单词频率进行排序
	 */
	static List<HashMap.Entry<String, Integer>> sortWordMap(){
		List<HashMap.Entry<String, Integer>> wordsList = new ArrayList<>(mp.entrySet());
		Collections.sort(wordsList, new Comparator<HashMap.Entry<String, Integer>>() {
			@Override
			public int compare(HashMap.Entry<String, Integer> word1, HashMap.Entry<String, Integer> word2) {
				if(word1.getValue().equals(word2.getValue())) {
					return word1.getKey().compareTo(word2.getKey());
				} else {
					return word2.getValue() - word1.getValue();
				}
			}
		});
		return wordsList;
	}
}
