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

    private Pattern wordPattern = Pattern.compile("(^|[^A-Za-z0-9])([A-Za-z]{4}[A-Za-z0-9]*)");
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

    public Lib(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    /**
     * compute all
     */
    public void process() throws IOException {
        String str = readFile();
        processCharNumInternal(str);
        processLineNumInternal(str);
        processWordInternal(str);
    }

    /**
     * compute the number of characters
     */
    public void processCharNum() throws IOException {
        String str = readFile();
        processCharNumInternal(str);
    }

    private void processCharNumInternal(String str) {
        charNum = str.length();
    }

    /**
     * compute the number of words
     * compute the top 10 number of occurrence of words
     */
    public void processWord() throws IOException {
        String str = readFile();
        processWordInternal(str);
    }

    private void processWordInternal(String str) {
        topWord = new HashMap<>();
        wordNum = 0;
        Matcher matcher = wordPattern.matcher(str);
        while (matcher.find()) {
            String word = matcher.group(2).toLowerCase();
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
     * compute the number of not empty lines
     */
    public void processLineNum() throws IOException {
        String str = readFile();
        processLineNumInternal(str);
    }

    private void processLineNumInternal(String str) throws IOException {
        lineNum = 0;
        Matcher matcher = linePattern.matcher(str);
        while (matcher.find()) {
            lineNum++;
        }
    }

    /**
     *
     * @return the content of the file
     */
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
            reader.close();
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
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        try {
            writer.write("characters: " + charNum + "\n");
            writer.write("words: " + wordNum + "\n");
            writer.write("lines: " + lineNum + "\n");
            for (Map.Entry<String, Integer> entry : topWord.entrySet()) {
                String s = entry.getKey();
                Integer integer = entry.getValue();
                writer.write(s + ": " + integer + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            writer.close();
            throw e;
        }
        System.out.println("successfully output");
    }
}
