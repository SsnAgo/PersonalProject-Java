package WordCount;
import java.io.*;
import java.util.*;
public class WordCount {
	private String fileIn;     //�����ļ���
	private String fileOut;    //����ļ���
	
	public WordCount(String fileInPath, String fileOutPath) {
		this.fileIn = fileInPath;
		this.fileOut = fileOutPath;
	}
	public void Counting() {
		Lib lib = new Lib(fileIn, fileOut);
		
		try { 
			lib.countCharWord();
			lib.countLine();
			lib.sortWordOccurs();
			lib.printFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		WordCount wordcount = new WordCount(args[0],args[1]);
		wordcount.Counting();
	}
}
