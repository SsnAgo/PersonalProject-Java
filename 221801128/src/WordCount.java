import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class WordCount {
	private static String inputfile;
    private static String outputfile;

	/**
	 * 读取指定文件，返回字符串
	 */
	public static String readFile(String file) throws IOException {
		BufferedReader br = null;
		StringBuilder str = new StringBuilder();
		int ch;
		br = new BufferedReader(new FileReader(file));
		while (true) {
			assert br != null;
			if (!((ch = br.read()) != -1)) break;
			str.append((char)ch);
		}
		br.close();
		return str.toString();
	}

	/**
	 * 写入指定文件
	 */
	public static void writeIn(String inputfile,String outputfile) throws IOException {
		BufferedWriter writer = null;
		StringBuilder str = new StringBuilder("characters: " + Lib.countChar(readFile(inputfile)) + "\n"
				+ "words: " + Lib.countWords(readFile(inputfile)) + "\n"
				+ "lines: " + Lib.countLines(readFile(inputfile)) + "\n");
		int cnt = 0;
		try {
			writer = Files.newBufferedWriter(Paths.get(outputfile), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<HashMap.Entry<String, Integer>> sortedList = Lib.getSortedList(Lib.hash);
		for(HashMap.Entry<String,Integer> entry:sortedList) {
			str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
			cnt++;
			if (cnt >= 10)
				break;
		}
		writer.write(str.toString());
		writer.close();
	}

	/**
	 * 打印
	 * @throws IOException
	 */
//	public static void printall() throws IOException {
//		StringBuilder str = new StringBuilder("characters: " + Lib.countChar(readFile(inputfile)) + "\n"
//				+ "words: " + Lib.countWords(readFile(inputfile)) + "\n"
//				+ "lines: " + Lib.countLines(readFile(inputfile)) + "\n");
//		int cnt = 0;
//		List<HashMap.Entry<String, Integer>> sortedList = Lib.getSortedList(Lib.hash);
//		for(HashMap.Entry<String,Integer> entry:sortedList) {
//			str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
//			cnt++;
//			if (cnt >= 10) break;
//		}
//		System.out.print(str.toString());
//	}
	public static void main(String[] args) throws IOException {
		if (args.length<2) {
            System.out.println("需要两个参数，请重新输入！");
            return;
        }
		File dir = new File(".");
		inputfile = dir.getCanonicalPath()+"\\"+args[0];
		outputfile = dir.getCanonicalPath()+"\\"+args[1];
		//System.out.println("读取文件的地址："+inputfile);
		//printall();
		//写入指定文件
		writeIn(inputfile,outputfile);
	}
}


