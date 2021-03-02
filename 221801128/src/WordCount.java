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
	public String readFile(String fileName) throws IOException {
		BufferedReader reader = null;
		StringBuilder str = new StringBuilder();
		int ch = 0;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while ((ch = reader.read()) != -1) {
				str.append((char)ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return str.toString();
	}

	/**
	 * 写入指定文件
	 */
	public static void writeIn(String inputfile,String outputfile) throws IOException {
		Path path1 = Paths.get(outputfile);
		BufferedWriter writer = null;
		StringBuilder str = new StringBuilder("characters: " + Lib.countChar(inputfile) + "\n"
				+ "words: " + Lib.countWords(textStr) + "\n"
				+ "lines: " + Lib.countLines(inputfile) + "\n");
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
		writer.write(str.toString());
		writer.close();
	}

	/**
	 * 打印
	 * @throws IOException
	 */
//	public static void printall(String inputfile) throws IOException {
//		StringBuilder str = new StringBuilder("characters: " + Lib.countChar(inputfile) + "\n"
//				+ "words: " + Lib.countWords(textStr) + "\n"
//				+ "lines: " + Lib.countLines(inputfile) + "\n");
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


		//printall(inputfile);
		//写入指定文件
		writeIn(inputfile, outputfile);
	}
}


