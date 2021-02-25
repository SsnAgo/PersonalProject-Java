import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class WordCount {
	public static void main(String[] args) throws IOException{
		File f1=new File(args[0]);
		FileWriter writer=new FileWriter(args[1]);
		
		//统计文件的字符数（对应输出第一行）：只需要统计Ascii码，汉字不需考虑,空格，水平制表符，换行符，均算字符
		try{
			int num=getCharNum(f1);
			writer.write("characters："+num);
			writer.write("\n");
			System.out.println("characters："+num);
		}
		catch(IOException exc){
			System.out.println("File error!");
		}
		
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
	
	//实现统计文件的字符数的功能
	public static int getCharNum(File file) throws FileNotFoundException {
		InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file));
		int num=0;
		try {
			while(inputStreamReader.read()!=-1)
			{
				num++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	//实现统计文件行数的功能
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
