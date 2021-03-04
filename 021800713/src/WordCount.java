import java.io.*;

public class WordCount {
    public static void main(String[] args) throws IOException {
        String inputFile = args[0];
        String outputFile = args[1];
        long startTime = System.currentTimeMillis();
        Lib.charactersCount(inputFile);//统计字符数
        Lib.wordsCount(inputFile, outputFile);//统计单词数
        Lib.linesCount(inputFile, outputFile);//统计行数
        Lib.wordNum(inputFile, outputFile);//统计单词出现的个数最多十个
        System.out.printf("耗时： %d ms\n",System.currentTimeMillis() - startTime);
        Lib.writeToFile(outputFile);
        System.out.printf(Lib.characters+"");
        System.out.printf(Lib.words+"");
        System.out.printf(Lib.lines+"");
    }
}
