import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class WordCount {
	private static String textStr;
	private static String inputfile;
    private static String outputfile;
	public WordCount(String inputPath, String outputPath) throws IOException {
        this.inputfile = inputPath;
        this.outputfile = outputPath;
		textStr = readFile(inputPath);
    }

	/**
	 * 读取指定文件，返回字符串
	 */
	public String readFile(String file) throws IOException {
		BufferedReader br = null;
		StringBuilder str = new StringBuilder();
		int ch;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while (true) {
				assert br != null;
				if (!((ch = br.read()) != -1)) break;
				str.append((char)ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return str.toString();
	}

	/**
	 * 写入指定文件
	 */
	public static void writeIn(String outputfile) throws IOException {
		Path path1 = Paths.get(outputfile);
		BufferedWriter writer = null;
		StringBuilder str = new StringBuilder("characters: " + Lib.countChar(textStr) + "\n"
				+ "words: " + Lib.countWords(textStr) + "\n"
				+ "lines: " + Lib.countLines(textStr) + "\n");
		int cnt = 0;
		try {
			writer = Files.newBufferedWriter(path1, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<HashMap.Entry<String, Integer>> sortedList = Lib.getSortedList(Lib.hash);
		for(HashMap.Entry<String,Integer> entry:sortedList) {
			str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
			cnt++;
			if (cnt >= 10) break;
		}
		assert writer != null;
		writer.write(str.toString());
		writer.close();
	}

	/**
	 * 打印
	 * @throws IOException
	 */
//	public static void printall() throws IOException {
//		StringBuilder str = new StringBuilder("characters: " + Lib.countChar(textStr) + "\n"
//				+ "words: " + Lib.countWords(textStr) + "\n"
//				+ "lines: " + Lib.countLines(textStr) + "\n");
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
        WordCount wordcount = new WordCount(args[0],args[1]);
        
		File dir = new File(".");
		inputfile = dir.getCanonicalPath()+"\\"+inputfile;
		outputfile = dir.getCanonicalPath()+"\\"+outputfile;
		//System.out.println("读取文件的地址："+inputfile);


		//printall();
		//写入指定文件
		writeIn(outputfile);
	}
}


