import java.io.*;
import java.util.Map;

public class Lib {

    public int getCharactersNum() {
        return charactersNum;
    }

    public void setCharactersNum(int charactersNum) {
        this.charactersNum = charactersNum;
    }

    public int getWordsNum() {
        return wordsNum;
    }

    public void setWordsNum(int wordsNum) {
        this.wordsNum = wordsNum;
    }

    public int getLinesNum() {
        return linesNum;
    }

    public void setLinesNum(int linesNum) {
        this.linesNum = linesNum;
    }

    private int charactersNum;
    private int wordsNum;
    private int linesNum;
    private String inputFile;
    private String outputFile;
    private Map<String, Integer> map;

    public Lib(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        charactersNum = 0;
        wordsNum = 0;
        linesNum = 0;
    }

    public void handleFile() throws IOException {
        String file = readFile();
        CountCharacters(file);
        CountWords(file);
    }

    public String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        StringBuilder builder = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            builder.append((char) c);
        }
        reader.close();
        return builder.toString();
    }

    private void CountCharacters(String str) {
        charactersNum = str.length();
    }

    private void CountWords(String str) {
        String[] list = str.split("[^a-zA-z0-9]");
        System.out.println(list.length);
        for (String s : list) {
            System.out.println("长度为：" + s.length() + " " + s);
        }
    }

}