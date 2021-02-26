import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Text file analyser
 */
public class Lib {

    private String inFile;
    private String outFile;
    private int charNum;
    private int wordNum;
    private int lineNum;
    private Map<String, Integer> topWord;

    private Pattern wordPattern = Pattern.compile("(^|\\s)[A-Za-z]{4}\\S*");
    private Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");

    public String getOutFile() {
        return outFile;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public int getLineNum() {
        return lineNum;
    }

    public String getInFile() {
        return inFile;
    }

    public void setInFile(String inFile) {
        this.inFile = inFile;
    }

    public int getCharNum() {
        return charNum;
    }

    public int getWordNum() {
        return wordNum;
    }

    public Map<String, Integer> getTopWord() {
        // clone a map to prevent modification of the resulted map
        return new LinkedHashMap<>(topWord);
    }

    public Lib(String inFile, String outFile) throws IOException {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    /**
     * process all data
     */
    public void process() throws IOException {
        charNum = 0;
        wordNum = 0;
        lineNum = 0;
        topWord = new HashMap<>();
        String str = readFile();
        charNum = str.length();
        Matcher matcher = linePattern.matcher(str);
        while (matcher.find()) {
            lineNum++;
        }
        matcher = wordPattern.matcher(str);
        while (matcher.find()) {
            String word = matcher.group(0).trim();
            Integer count = topWord.get(word);
            if (count == null) {
                count = 0;
            }
            topWord.put(word, count + 1);
            wordNum++;
        }
        topWord = topWord.entrySet().stream()
            .sorted(
                Map.Entry.<String, Integer>comparingByValue()
                    .reversed()
                    .thenComparing(Map.Entry.comparingByKey()))
            .limit(10)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * process character
     */
    public void processCharNum() throws IOException {
        charNum = 0;
        String str = readFile();
        charNum = str.length();
    }

    /**
     * process the top 10 number of occurrence of words
     */
    public void processWordRank() throws IOException {
        topWord = new HashMap<>();
        wordNum = 0;
        String str = readFile();

        Matcher matcher = wordPattern.matcher(str);
        while (matcher.find()) {
            String word = matcher.group(0).trim();
            Integer count = topWord.get(word);
            if (count == null) {
                count = 0;
            }
            topWord.put(word, count + 1);
            wordNum++;
        }
        topWord = topWord.entrySet().stream()
            .sorted(
                Map.Entry.<String, Integer>comparingByValue()
                    .reversed()
                    .thenComparing(Map.Entry.comparingByKey()))
            .limit(10)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }

    public void processLineNum() throws IOException {
        lineNum = 0;
        String str = readFile();
        Matcher matcher = linePattern.matcher(str);
        while (matcher.find()) {
            lineNum++;
        }
    }

    private String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        StringBuilder builder = new StringBuilder();
        try {
            int c;
            while ((c = reader.read()) != -1) {
                if (c != 13) {
                    builder.append((char) c);
                }
            }
        } catch (IOException e) {
            reader.close();
            throw e;
        }
        return builder.toString();
    }

    /**
     * write data to file in a hard-encoding format
     */
    public void output() throws IOException {
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(outFile)
        );
        writer.write("characters: " + charNum + "\n");
        writer.write("words: " + wordNum + "\n");
        writer.write("lines: " + lineNum + "\n");
        for (Map.Entry<String, Integer> entry : topWord.entrySet()) {
            String s = entry.getKey();
            Integer integer = entry.getValue();
            writer.write(s + ": " + integer + "\n");
        }
        writer.close();
    }
}
