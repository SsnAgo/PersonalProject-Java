import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class WordCount {

	/**
	 * 文件处理工具类
	 */
	static class IOUtil{
		/**
		 * 读取指定文件，返回对应字符串形式
		 */
		static String readFile(String infile) throws IOException {
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
		 * 写文件
		 */
		static void writeFile(String result, String outfile) throws IOException {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(outfile), StandardCharsets.UTF_8);
			writer.write(result.toString());
			writer.close();
		}
	}

	/**
	 * 输出内容硬编码
	 */
	private String getResult(String content){
		StringBuilder result = new StringBuilder("characters: " + Lib.countCharNum(content) + "\n"
				+ "words: " + Lib.countWordNum(content) + "\n"
				+ "lines: " + Lib.countValidLineNum(content) + "\n");
		List<HashMap.Entry<String, Integer>> wordsList = Lib.sortWordMap();
		int count = 0;
		for (HashMap.Entry entry: wordsList) {
			count++;
			result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
			if (count >= 10)
				break;
		}
		return result.toString();
	}

	/**
	 * 统计
	 */
	private void process(String infile, String outfile) throws IOException {
		String content = IOUtil.readFile(infile);
		IOUtil.writeFile(getResult(content),outfile);

	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("2 parameters needed");
			return;
		} else if (args.length > 2)
			System.out.println("choose tow font paths");

		new WordCount().process(args[0],args[1]);
	}
}