import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Lib {

    private int charactersNum;
    private int wordsNum;
    private int linesNum;
    private final String inputFile;
    private final String outputFile;
    private Map<String, Integer> map;
    private List<Map.Entry<String, Integer>> wordsRank;
    private String WordPattern = "^[A-Za-z]{4}[A-Za-z0-9]*";

    public Lib(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        charactersNum = 0;
        wordsNum = 0;
        linesNum = 0;
    }

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


    public void handleFile() throws IOException {
        String file = readFile();
        countCharacters(file);
        countLines(file);
        countWords(file);
        sortWords();
        setOutputFile();
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

    private void countCharacters(String str) {
        charactersNum = str.length();
    }

    private void countLines(String str) {
        String[] list = str.split("\n");
        for (String s: list) {
            if (s.length() > 0)
                linesNum++;
        }
    }

    private void countWords(String str) {
        map = new TreeMap<>();
        String[] list = str.split("[^a-zA-z0-9]");
        for (String s : list) {
            //判断字符串是否为空
            if (s.length() != 0 && isWord(s)) {
                wordsNum++;
                String lowerCase = s.toLowerCase();
                if (map.get(lowerCase) == null) {
                    map.put(lowerCase, 1);
                }
                else {
                    int cnt = map.get(lowerCase);
                    map.put(lowerCase, cnt+1);
                }
            }
        }
    }

    private boolean isWord(String str) {
        return Pattern.matches(WordPattern, str);
    }

    private void sortWords() {
        wordsRank = new ArrayList<>(map.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator = (o1, o2) -> o2.getValue()- o1.getValue();
        wordsRank.sort(comparator);
    }

    private void showWordsRank() {
        if (wordsRank.size() > 10) {
            for (int i = 0; i < wordsRank.size(); i++ ) {
                System.out.print(wordsRank.get(i).getKey() + ": " + wordsRank.get(i).getValue() + "\n");
            }
        }
        else
            wordsRank.forEach(item -> System.out.print(item.getKey() + ": " + item.getValue() + "\n"));
    }

    private void showDetails() {
        System.out.println("characters：" + charactersNum);
        System.out.println("words: " + wordsNum);
        System.out.println("lines：" + linesNum);
        showWordsRank();
    }
    
    private void setOutputFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write("characters: " + charactersNum + "\n");
        writer.write("words: " + wordsNum + "\n");
        writer.write("lines: " + linesNum + "\n");
        wordsRank.forEach(item -> {
            try {
                writer.write(item.getKey() + ": " + item.getValue() + "\n");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
    }
}