package wordcount;

import java.io.*;

public class FileIO {

	public static Reader openInputFile(String fileName) {
		
		String ERROR_MESSAGE = "文件路径不存在";
		
		File file = new File(fileName);
		Reader reader = null;
		
		try {
			reader = new InputStreamReader(new FileInputStream(file));
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(ERROR_MESSAGE);
		}
		return reader;
	}
	
	public static void writeToFile(String fileName ,String str) throws IOException {
		File file = new File(fileName);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

		bw.write(str);
	}

}
