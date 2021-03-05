import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class WordCount {

	/**
	 * 读取指定文件，返回对应字符串形式
	 */
	public  String readFile(String infile) throws IOException {
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
	/*public void printResult (String infile) throws IOException {
		String str = readFile(infile);
		System.out.println("字符数: " + Lib.countCharNum(str));
		System.out.println("有效行数: " + Lib.countValidLineNum(str));
		System.out.println("单词数: " + Lib.countWordNum(str));
		List<HashMap.Entry<String, Integer>> wordsList = Lib.sortWordMap();
		int count = 0;
		for (HashMap.Entry s : wordsList) {
			count++;
			System.out.println(s.getKey()+": " + s.getValue());
			if (count >= 10)
				break;
		}
	}*/

	/**
	 * 写文件
	 */
	public void writeFile(String infile, String outfile) throws IOException {
		String content = readFile(infile);
		StringBuilder str = new StringBuilder("characters: " + Lib.countCharNum(content) + "\n"
				+ "words: " + Lib.countWordNum(content) + "\n"
				+ "lines: " + Lib.countValidLineNum(content) + "\n");
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(outfile), StandardCharsets.UTF_8);
		List<HashMap.Entry<String, Integer>> wordsList = Lib.sortWordMap();
		int count = 0;
		for (HashMap.Entry entry: wordsList) {
			count++;
			str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
			if (count >= 10)
				break;
		}
		writer.write(str.toString());
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("2 parameters needed");
			return;
		}
		new WordCount().writeFile(args[0],args[1]);
	}
}