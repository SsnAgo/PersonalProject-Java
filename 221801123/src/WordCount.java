import java.io.*;

public class WordCount {

	/**
	 * 读取指定文件，返回对应字符串形式
	 */
	public static String readFile(String infile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(infile));
		StringBuilder builder = new StringBuilder();
		int ch;
		while ((ch = reader.read()) != -1) {
			builder.append((char)ch);
		}
		reader.close();
		return builder.toString();
	}

	/**
	 * 测试的输出函数java WordCount input.txt output.txt
	 */
	public static void printResult (String infile) throws IOException {
		System.out.println("字符数: "+Lib.countCharNum(readFile(infile)));
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("2 parameters needed");
			return;
		}
		printResult(args[0]);
	}
}