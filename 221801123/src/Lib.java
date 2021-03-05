import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统计工具
 */
public class Lib {

	//存放单词与其对应出现的次数
	static HashMap<String, Integer> mp = new HashMap<>();

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
	 * 统计单词数
	 */
	static int countWordNum(String str) {
		int wordNum = 0;//单词数
		str = str.toLowerCase();//全部默认为小写
		Pattern wordPattern = Pattern.compile("[a-z]{4}[a-z0-9]*");
		Matcher matcher = wordPattern.matcher(str);
		while (matcher.find()) {
			wordNum++;
			String g = matcher.group();
			if(null == mp.get(g)){
				mp.put(g, 1);
			} else {
				int n = mp.get(g);
				mp.put(g, n + 1);
			}
		}
		return wordNum;
	}
}
