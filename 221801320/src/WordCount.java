package wordcount;

import java.io.IOException;

public class WordCount {
	public static String output = "1";
	
	public static void main(String[] args) throws IOException {
		
        String inputFile = args[0];
        String outputFile = args[1];
        Lib.charactersCount(inputFile,outputFile);//统计文件字符数量
        Lib.wordsCount(inputFile,outputFile);//统计文件单词数
        Lib.linesCount(inputFile,outputFile);//统计文件有效行数
        Lib.wordNum(inputFile,outputFile);//统计文件的单词数

    }
}
