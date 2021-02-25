import java.io.*;

/*
* JDK环境:	openjdk 14.0.2 2020-07-14
* @author 	林浩然
* */
public class WordCount {
	public static void main(String[] args){
		try{
			int len=args.length;

			File inputFile;
			inputFile = new File(args[0]);
			InputStream is = new FileInputStream(inputFile);

			File outputFile;
			OutputStream os;
			if(len<2){
				os = System.out;
			}else{
				outputFile = new File(args[1]);
				os = new FileOutputStream(outputFile);
			}


			InputStreamWordReader wr = new InputStreamWordReader(is);
			Core core = new Core();
			core.setWordReader(wr);
			core.start();

			PrintWriter pw = new PrintWriter(os);
			pw.println(core.toString());
			pw.close();
//			System.out.println(core.toString());
		}catch (ArrayIndexOutOfBoundsException e){
			System.out.println("缺少参数");
			printInfo();
		} catch (FileNotFoundException e) {
			System.out.println("无法打开文件");
			printInfo();
		}
	}

	public static void printInfo(){
		System.out.println("参数：\n\tinput.txt：\t输入文件名\n\toutput.txt：\t可选，输出文件名，默认到命令行");
	}
}
