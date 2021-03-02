import java.io.*;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

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

    private String pattern = "^[A-Za-z]{4}[A-Za-z0-9]*";


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
        map = new TreeMap<>();
        String[] list = str.split("[^a-zA-z0-9]");
        System.out.println(list.length);
        for (String s : list) {
            System.out.println("长度为：" + s.length() + " " + s);

            //判断字符串是否为空
            if (s.length() != 0 && isWord(s)) {
                String lowstring = s.toLowerCase();
                if (map.get(lowstring) == null) {
                    map.put(lowstring, 1);
                }
                else {
                    int cnt = map.get(lowstring);
                    map.put(lowstring, cnt+1);
                }
            }
        }
        map.forEach((k,v) -> System.out.println(k + ":" + v));
    }

    private boolean isWord(String str) {
        return Pattern.matches(pattern, str);
    }

}