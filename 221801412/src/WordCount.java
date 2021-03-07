import java.io.*;
import java.util.*;

public class WordCount {
    /**
     * 主函数
     */
    public static void main(String[] args) {
	    File input;
	    File output;
	    //若命令行参数小于2则新建文件
	    if (args.length < 2) {
	        input = new File("src/input.txt");
	        output = new File("src/output.txt");
        }
        else {
            input = new File(args[0]);
            output = new File(args[1]);
        }
        try {
            CountData data = new CountData();
            Lib.openFile(input);
            Lib.countChar(input);
            data = Lib.getData();
            //统计字符数
            int countChar = data.getCountChar();
            //统计行数
            int countLine = data.getCountLine();
            //统计单词数
            int countWord = data.getCountWord();
            List<Map.Entry<String, Integer>> getWordFrequency = data.getGetWordFrequency();
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write("characters: " + countChar + "\n");
            writer.write("words: " + countWord + "\n");
            writer.write("lines: " + countLine + "\n");
            if (getWordFrequency.size() < 10) {
                for (int i = 0; i < getWordFrequency.size(); i++) {
                    writer.write(getWordFrequency.get(i).getKey() + ": " + getWordFrequency.get(i).getValue());
                    writer.write("\n");
                }
            }
            else {
                for (int i = 0; i < 10; i++) {
                    writer.write(getWordFrequency.get(i).getKey() + ":" + getWordFrequency.get(i).getValue());
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        }
    }
}
