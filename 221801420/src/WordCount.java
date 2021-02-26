import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class WordCount {
    public static void main(String[] args) {
        Lib lib = new Lib();
        if (args.length >= 2) {
            try {
                File inputFile = new File(args[0]);
                File outputFile = new File(args[1]);
                FileWriter fw = new FileWriter(outputFile);
                BufferedWriter buffWriter = new BufferedWriter(fw);
				
                //获取字符个数并输出
                int characterNum = lib.statisticsCharacters(inputFile);
                buffWriter.write("characters: " + characterNum);
                buffWriter.newLine();
				
                //获取单词个数并输出
                int wordNum = lib.statisticsWords(inputFile);
                buffWriter.write("words: " + wordNum);
                buffWriter.newLine();
				
                //获取有效行数并输出
                int lineNum = lib.statisticsLines(inputFile);
                buffWriter.write("lines: " + lineNum);
                buffWriter.newLine();
                
                //对单词频率映射表进行排序并输出出现频率最高的前十个单词
                lib.wordsFrequency(buffWriter);
                buffWriter.close();
                fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}

