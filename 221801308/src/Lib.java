import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private static int charNumber;
    private static int lineNumber;
    private static int wordNumber;
    private static Map<String, Integer> linkedMapWords;
    private static final String LINE_REGEX = "(^|\n)(\\s*\\S+)";
    private static final String WORD_REGEX = "(^|[^a-z0-9])([a-z]{4}[a-z0-9]*)";

    /**
     * read file and perform all the required calculation
     * @param inputFile
     * @throws IOException
     */
    public static void beginCount(String inputFile) throws IOException {
        String builderString = readFileContent(inputFile);
        countChars(builderString);
        countWords(builderString);
        countLines(builderString);
    }

    /**
     * get the file content
     * @throws IOException
     * @return
     */
    public static String readFileContent(String inputFile) throws IOException {
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
        return builder.toString().toLowerCase();
    }

    /**
     * get the number of characters
     */
    public static void countChars(String builderString) {
        charNumber = builderString.length();
    }

    /**
     * get the number of lines
     */
    public static void countLines(String builderString) {
        lineNumber = 0;
        Pattern linePattern = Pattern.compile(LINE_REGEX);
        Matcher matcher = linePattern.matcher(builderString);
        while(matcher.find()) {
            lineNumber++;
        }
    }

    /**
     * get the number of words
     */
    public static void countWords(String builderString) {
        wordNumber = 0;
        Map<String, Integer> mapWords = new HashMap<>();
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = wordPattern.matcher(builderString);
        while(matcher.find()) {
            wordNumber++;
            String word = matcher.group(2);
            if(!mapWords.containsKey(word)) {
               mapWords.put(word, 1);
            } else{
                mapWords.put(word, mapWords.get(word)+1);
            }
        }
        sortMap(mapWords);
    }

    /**
     * sort the words
     * @param mapWords
     */
    public static void sortMap(Map<String, Integer> mapWords) {
        Set<Entry<String, Integer>> mapEntry = mapWords.entrySet();
        List<Entry<String, Integer>> entryList = new LinkedList<Entry<String, Integer>>(mapEntry);
        entryList.sort(new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
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
    public static void writeFileContent(String outputFile) throws IOException {
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
            System.out.println("File write failed!");
        }
    }
}
