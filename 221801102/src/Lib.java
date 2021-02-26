import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Text file analyser
 */
public class Lib {

    private String filename;
    private int charNum;
    private int wordNum;
    private Map<String, Integer> topWord;

    private Pattern wordPattern = Pattern.compile("[A-Za-z]{4}\\S*");

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getCharNum() {
        return charNum;
    }

    public int getWordNum() {
        return wordNum;
    }

    public Map<String, Integer> getTopWord() {
        // clone a map to prevent modification of the resulted map
        return new HashMap<>(topWord);
    }

    public Lib(String filename) throws IOException {
        this(filename, false);
    }

    /**
     * @param start start process text immediately
     */
    public Lib(String filename, Boolean start) throws IOException {
        this.filename = filename;
        if (start) {
            process();
        }
    }

    /**
     * process all data
     */
    public void process() throws IOException {
        int charNum = this.charNum;
        int wordNum = this.wordNum;
        Map<String, Integer> topWord = this.topWord;
        this.charNum = 0;
        this.wordNum = 0;
        this.topWord = new HashMap<>();
        try {
            readFileByLine(line -> {
                lineChar(line);
                lineWord(line);
            });
            this.charNum--;
        } catch (IOException e) {
            // restore
            this.charNum = charNum;
            this.wordNum = wordNum;
            this.topWord = topWord;
            throw e;
        }
    }

    /**
     * process character
     */
    public void processCharNum() throws IOException {
        int charNum = this.charNum;
        this.charNum = 0;
        try {
            readFileByLine(this::lineChar);
            this.charNum--;
        } catch (IOException e) {
            // restore
            this.charNum = charNum;
            throw e;
        }
    }

    /**
     * process the number of words
     */
    public void processWordNum() throws IOException {
        int wordNum = this.wordNum;
        this.wordNum = 0;
        try {
            readFileByLine(line -> {
                Matcher matcher = wordPattern.matcher(line);
                while (matcher.find()) {
                    this.wordNum++;
                }
            });
        } catch (IOException e) {
            // restore
            this.wordNum = wordNum;
            throw e;
        }
    }

    /**
     * process the top 10 number of occurrence of words
     */
    public void processWordRank() throws IOException {
        Map<String, Integer> topWord = this.topWord;
        this.topWord = new HashMap<>();
        try {
            readFileByLine(line -> {
                Matcher matcher = wordPattern.matcher(line);
                int index = 0;
                while (matcher.find()) {
                    String word = matcher.group(index);
                    Integer count = this.topWord.get(word);
                    if (count == null) {
                        count = 0;
                    }
                    this.topWord.put(matcher.group(index), count + 1);
                    index++;
                }
            });
        } catch (IOException e) {
            // restore
            this.topWord = topWord;
            throw e;
        }
    }

    private void lineWord(String line) {
        Matcher matcher = wordPattern.matcher(line);
        int ret = 0;
        while (matcher.find()) {
            String word = matcher.group(ret);
            Integer count = topWord.get(word);
            if (count == null) {
                count = 0;
            }
            topWord.put(matcher.group(ret), count + 1);
            ret++;
        }
        wordNum += ret;
    }

    private void lineChar(String line) {
        // +1换行符
        charNum += line.length() + 1;
    }

    /**
     * Read text file by line
     *
     * @param l callback after read a line successfully
     */
    private void readFileByLine(final OnLineReadListener l) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        try {
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                l.onRead(line);
            }
        } catch (IOException e) {
            reader.close();
            throw e;
        }
    }

    /**
     * callback after read a line successfully
     */
    interface OnLineReadListener {
        /**
         * @param line successful read line(without line-termination characters)
         */
        public void onRead(@NotNull String line);
    }
}
