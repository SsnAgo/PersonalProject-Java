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
		lineNum = 0;
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
		System.out.println("字符数: "+Lib.countCharNum(readFile(infile)));
		System.out.println("有效行数: "+Lib.countBlankLineNum(readFile(infile)));
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("2 parameters needed");
			return;
		}
		new WordCount().printResult(args[0]);
	}
}