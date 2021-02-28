
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {

	private static String inputfile;
    private static String outputfile;
	public WordCount(String inputPath, String outputPath) {
        this.inputfile = inputPath;
        this.outputfile = outputPath;
    }
	
	public static void main(String[] args) throws IOException {
		if (args.length<2) {
            System.out.println("需要两个参数，请重新输入！");
            return;
        }
        WordCount wordcount = new WordCount(args[0],args[1]);
        
		File dir = new File(" ");
		inputfile = dir.getCanonicalPath()+"\\src\\"+inputfile;
		outputfile = dir.getCanonicalPath()+outputfile;
		System.out.println("读取文件的地址："+inputfile);
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Lib.solve(br);
		br.close();
		Lib.printall(inputfile);
		//写入指定文件
		Lib.writeIn(inputfile, outputfile);   
	}
}


