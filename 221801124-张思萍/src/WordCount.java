import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {

        String inFile = args[0];
        String outFile = args[1];
        String outToFile = "";

        try {
            ArrayList<String> lines = FileIO.readFile(inFile);
            outToFile += "characters: " + DoWordCount.countChars(inFile) + "\n";
            outToFile += "words: " + DoWordCount.countWords(lines) + "\n";
            outToFile += "words: " + DoWordCount.countLines(lines) + "\n";
            outToFile += DoWordCount.printTop10(DoWordCount.sortWords(lines));
            FileIO.writeToFile(outFile, outToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
