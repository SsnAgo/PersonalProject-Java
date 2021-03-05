import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统计工具
 */
public class Lib {

	/**
	 * 统计总字符数
	 */
	public static int countCharNum(String str){
		return str.length();
	}

	/**
	 * 统计空白行数
	 */
	public static int countBlankLineNum(String str) {
		int lineNum = 0;
		Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");
		Matcher matcher = linePattern.matcher(str);
		while (matcher.find()) {
			lineNum++;
		}
		return lineNum;
	}
}
