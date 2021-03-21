import java.io.*;

public class WordCount {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {//未输入两个文件
            System.out.println("there are 2 parameters needed!");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        Lib.charactersCount(inputFile);//统计字符数
        Lib.wordsCount(inputFile, outputFile);//统计单词数
        Lib.linesCount(inputFile, outputFile);//统计行数
        Lib.wordNum(inputFile, outputFile);//统计单词出现的个数最多十个
        Lib.writeToFile(outputFile);
    }
}
