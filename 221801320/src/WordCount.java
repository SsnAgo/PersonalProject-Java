package wordcount;

import java.io.IOException;

public class WordCount {
	public static void main(String[] args) throws IOException {
        String inputFile = "F:\\1.txt";
        String outputFile = "F:\\2.txt";
        Lib.charactersCount(inputFile);//统计字符数
        Lib.wordsCount(inputFile);//统计单词数
        Lib.linesCount(inputFile);//统计行数
        Lib.wordNum(inputFile);//
        System.out.println(Lib.output);
        Lib.writeToFile(outputFile, Lib.output);
    }
}
