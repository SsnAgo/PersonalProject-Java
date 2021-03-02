import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangyu
 */
public class Lib {
    private String inputFile;
    private String outputFile;
    private int charNumber;
    private int lineNumber;
    private int wordNumber;
    private final Pattern linePattern = Pattern.compile("(^|\n)(\\s*\\S+)");
    private final Pattern wordPattern = Pattern.compile("(^|[^a-z0-9])([a-z]{4}[a-z0-9]*)");
    public Lib(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     *
     * @return the file content
     * @throws IOException
     */
    public String readFileContent() throws IOException {
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
     * @throws IOException
     */
    public void countChars() throws IOException{
        String str = readFileContent();
        charNumber = str.length();
    }

    /**
     * get the number of lines
     * @throws IOException
     */
    public void countLines() throws IOException{
        lineNumber = 0;
        String str = readFileContent();
        Matcher matcher = linePattern.matcher(str);
        while(matcher.find()){
            lineNumber++;
        }
    }

    /**
     * get the number of words
     * @throws IOException
     */
    public void countWords() throws IOException{
        wordNumber = 0;
        String str = readFileContent();
        Matcher matcher = wordPattern.matcher(str);
        while(matcher.find()){
            wordNumber++;
        }
    }

    /**
     * write data to the file
     * @throws IOException
     */
    public void writeFileContent() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        try {
            writer.write("characters: " + charNumber + "\n");
            writer.write("words: " + wordNumber + "\n");
            writer.write("lines: " + lineNumber + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            writer.close();
            e.printStackTrace();
        }
        System.out.println("succeed!!!");
    }
}
