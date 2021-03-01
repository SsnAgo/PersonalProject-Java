import java.io.*;
import java.util.Map;

public class Lib {

    private int charactorsNum;
    private int wordsNum;
    private int linesNum;
    private String inputFile;
    private String outputFile;
    private Map<String, Integer> map;

    public Lib(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        charactorsNum = 0;
        wordsNum = 0;
        linesNum = 0;
    }

    private void CountLines() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            try{
                while (reader.readLine() != null) {
                    linesNum++;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}