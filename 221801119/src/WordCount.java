package word.count.pro;

import java.io.*;
import java.util.Scanner;
import java.lang.String;
    
public class WordCount {
	public static void main(String[] args) {
		//读取文件名
		Scanner input = new Scanner(System.in);
		String inputStr = input.nextLine();
		input.close();
        String[] files = inputStr.split(" "); 
        String inputFile = files[0];
        String outputFile = files[1];
		//通过封装核心功能的接口计算
        CoreCount coreCount = new CoreCount(inputFile);
        coreCount.Count();
        /* 测试用：
        System.out.print(coreCount.getCharCount());
        */
        //生成输出文件
		File file = new File(outputFile);
		if (file.exists()) {
			System.out.print("File \"" + outputFile + "\" already exist.");
		}
		try {
			file.createNewFile();
		} catch (IOException e){			
		}
    }
}
