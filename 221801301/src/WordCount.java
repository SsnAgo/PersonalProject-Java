import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class WordCount {
	public static void main(String[] args) throws IOException{
		File f1=new File(args[0]);
		FileWriter writer=new FileWriter(args[1]);
		
		//统计文件的有效行数（对应输出第三行）：任何包含非空白字符的行，都需要统计。
		try{
			int line=getLine(f1);
			writer.write("lines："+line);
			writer.close();
			System.out.println("lines："+line);
		}
		catch(IOException exc){
			System.out.println("File error!");
		}

	}
	
	public static int getLine(File file){
		int line=0;
		try {
			if(file.exists()) {
				long fileLength=file.length();
				LineNumberReader lineNumberReader=new LineNumberReader(new FileReader(file));
				lineNumberReader.skip(fileLength);
				line=lineNumberReader.getLineNumber();
				lineNumberReader.close();
			}
			else
				System.out.println("file does not exist.");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return line;
	}
}
