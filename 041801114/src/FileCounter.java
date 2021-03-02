import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;

public class FileCounter implements CounterImpl {

    public FileCounter(Path path) throws IOException {
        charCount = FileCounterHelper.solveCharCount(path);

        String[] words = FileCounterHelper.solveWords(path);

        wordCount = FileCounterHelper.solveWordCount(words);

        FileCounterHelper.operateWordFrequency(wordFrequency, FileCounterHelper.solveAllWord(words));

        lineCount = FileCounterHelper.solveLineCount(path);
    }

    private final long charCount;

    @Override
    public long getCharCount() {
        return charCount;
    }

    private final long wordCount;

    @Override
    public long getWordCount() {
        return wordCount;
    }

    private final long lineCount;

    @Override
    public long getLineCount() {
        return lineCount;
    }

    private final LinkedHashMap<String, Long> wordFrequency = new LinkedHashMap<String, Long>();

    @Override
    public LinkedHashMap<String, Long> getWordFrequency() {
        return new LinkedHashMap<>(wordFrequency);
    }

}
