import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("参数输入个数有误，请重新输入");
            return;
        }

        String inFile = args[0];
        String outFile = args[1];
        String outToFile = "";

        try {
            ArrayList<String> lines = FileIO.readFile(inFile);
            outToFile += "characters: " + DoWordCount.countChars(inFile) + "\n";
            outToFile += "words: " + DoWordCount.countWords(lines) + "\n";
            outToFile += "lines: " + DoWordCount.countLines(lines) + "\n";
            outToFile += DoWordCount.sortWords(lines);
            FileIO.writeToFile(outFile, outToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
