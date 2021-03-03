import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * The type Word count.
 */
class  WordCount{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

    }

    /**
     * 将统计结果输出到文件
     *
     * @param characters    the characters 文件的字符数
     * @param words         the words 单词总数
     * @param lines         the lines 有效行数
     * @param wordWithCount the word with count 最多的10个单词及其词频
     * @param outputFile    the output file 要输出的文件
     */
    private static void generateOutputFile(int characters, int words, int lines
            , List<Map.Entry<String,Integer> > wordWithCount, File outputFile) {
        try {
            PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile,false),Config.CHARSET
            )));
            outputWriter.println("characters: " + characters);
            outputWriter.println("words: " + words);
            outputWriter.println("lines: " + lines);
            for (Map.Entry<String,Integer> wordPair : wordWithCount) {
                outputWriter.println(wordPair.getKey() + ": " + wordPair.getValue());
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException ioException) {
            System.out.println(Config.IO_EXCEPTION_TIP);
            ioException.printStackTrace();
        }
    }
}