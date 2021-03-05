import java.io.*;

public class WordCount {

	/*//统计字符'\n'
	int lineNum = 0;*/

	/**
	 * 读取指定文件，返回对应字符串形式
	 */
	public  String readFile(String infile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(infile));
		StringBuilder builder = new StringBuilder();
		int ch;
		//lineNum = 0;
		while ((ch = reader.read()) != -1) {
			/*if (ch == 10)
				lineNum++;*/
			builder.append((char)ch);
		}
		reader.close();
		return builder.toString();
	}

	/**
	 * 测试的输出函数java WordCount input.txt output.txt
	 */
	public void printResult (String infile) throws IOException {
		String str = readFile(infile);
		System.out.println("字符数: "+Lib.countCharNum(str));
		System.out.println("有效行数: "+Lib.countValidLineNum(str));
		System.out.println("单词数: "+Lib.countWordNum(str));
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("2 parameters needed");
			return;
		}
		new WordCount().printResult(args[0]);
	}
}