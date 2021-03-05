package wordcount;

import java.io.IOException;

public class WordCount {
	public static void main(String[] args) throws IOException {
        String inputFile = "F:/66/1.txt";
        String outputFile = "F:/66/2.txt";
        Count.charactersCount(inputFile);//统计字符数
        Count.wordsCount(inputFile);//统计单词数
        Count.linesCount(inputFile);//统计行数
        Count.wordNum(inputFile);//
        System.out.println(Count.output);
        FileIO.writeToFile(outputFile, Count.output);
    }
}
