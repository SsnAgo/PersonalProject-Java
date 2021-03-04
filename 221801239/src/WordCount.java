import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class WordCount {
    public static void main(String args[]){
        String [] resultStr = null;
        BufferedWriter bufferedWriter = Lib.openOutputFile(args[1]);

        Lib.characterCount(args[0],bufferedWriter);
        resultStr = Lib.wordCount(args[0],bufferedWriter);
        Lib.lineCount(args[0],bufferedWriter);
        Lib.printWord(bufferedWriter,resultStr);

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
