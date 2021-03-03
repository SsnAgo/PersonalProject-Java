import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author wangyu
 */
public class Lib {
    private String inputFile;
    private String outputFile;
    private String bufferString;
    private int charNumber;
    private int lineNumber;
    private int wordNumber;
    private Map<String, Integer> linkedMapWords;
    private final String LINE_REGEX = "(^|\n)(\\s*\\S+)";
    private final String WORD_REGEX = "(^|[^a-z0-9])([a-z]{4}[a-z0-9]*)";

    public Lib(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     * read and count
     * @throws IOException
     */
    public void beginCount() throws IOException {
        readFileContent();
        countChars();
        countWords();
        countLines();
    }

    /**
     * get the file content
     * @throws IOException
     */
    public void readFileContent() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        StringBuilder builder = new StringBuilder();
        try {
            int tempStr;
            while ((tempStr = reader.read()) != -1) {
                builder.append((char)tempStr);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferString = builder.toString().toLowerCase();
    }

    /**
     * get the number of characters
     */
    public void countChars() {
        charNumber = bufferString.length();
    }

    /**
     * get the number of lines
     */
    public void countLines() {
        lineNumber = 0;
        Pattern linePattern = Pattern.compile(LINE_REGEX);
        Matcher matcher = linePattern.matcher(bufferString);
        while(matcher.find()) {
            lineNumber++;
        }
    }

    /**
     * get the number of words
     */
    public void countWords() {
        wordNumber = 0;
        Map<String, Integer> mapWords = new HashMap<>();
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = wordPattern.matcher(bufferString);
        while(matcher.find()) {
            wordNumber++;
            String word = matcher.group(2);
            if(!mapWords.containsKey(word)) {
               mapWords.put(word, 1);
            } else{
                mapWords.put(word, mapWords.get(word)+1);
            }
        }
        sortMapByValues(mapWords);
    }

    /**
     * sort the words
     * @param mapWords
     */
    public void sortMapByValues(Map<String, Integer> mapWords) {
        Set<Entry<String, Integer>> mapEntry = mapWords.entrySet();
        List<Entry<String, Integer>> entryList = new LinkedList<Entry<String, Integer>>(mapEntry);
        Collections.sort(entryList, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return !o2.getValue().equals(o1.getValue()) ? (o2.getValue() - o1.getValue()) : (o1.getKey()).compareTo(o2.getKey());
            }
        });
        linkedMapWords = new LinkedHashMap<>();
        for(Entry<String, Integer> entry: entryList) {
            linkedMapWords.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * write data to the file
     * @throws IOException
     */
    public void writeFileContent() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        int i = 0;
        try {
            writer.write("characters: " + charNumber + "\n");
            writer.write("words: " + wordNumber + "\n");
            writer.write("lines: " + lineNumber + "\n");
            for(Entry<String, Integer> entry: linkedMapWords.entrySet()) {
                if(i<10) {
                    writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                    i++;
                } else {
                    break;
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            writer.close();
            e.printStackTrace();
        }
        System.out.println("succeed!!!");
    }
}
