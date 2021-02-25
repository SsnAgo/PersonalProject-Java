import java.io.*;
import java.util.*;

/*
 * @author 张海浪
 * 用于查询的工具类
 * 
 * */
public class Lib {
	private static String WORD_REGEX="[a-z]{4}[a-z0-9]*";
	private static String FLITER_REGEX="[^a-zA-Z0-9]";
	
	//获取当前工程的路径
	public static String DIR=System.getProperty("user.dir");
	
	/*
	 * 统计文件行数
	 * @param file
	 * @return lines
	 * */
	public static int getTextLines(String filePath) {
		//行数的统计量
		int count=0;
		//循环变量
		String str="";
		
		//得到输入流
		FileInputStream is=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		
		try {
			is=new FileInputStream(filePath);
			isr=new InputStreamReader(is);
			br=new BufferedReader(isr);
			while((str=br.readLine())!=null) {
				if(!"".equals(str)) {
					count++;
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				//关闭输入流
				is.close();
				isr.close();
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
