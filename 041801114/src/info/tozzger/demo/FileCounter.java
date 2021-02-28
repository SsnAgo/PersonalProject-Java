package info.tozzger.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class FileCounter implements CounterImpl {

    private long solveCharCount(Path path) {
        return path.toFile().length();
    }

    private String[] solveWords(Path path) throws IOException {
        return Files.lines(path).map(String::toLowerCase).flatMap(CounterHelper::split).filter(CounterHelper::isWord1)
                .toArray(String[]::new);
    }

    private long solveWordCount(String[] words) {
        return words.length;
    }

    private HashMap<String, Long> solveAllWord(String[] words) {
        return Arrays.stream(words).collect(() -> new HashMap<String, Long>(),
                (map, word) -> map.merge(word, 1L, Long::sum), HashMap<String, Long>::putAll);
    }

    private void operateWordFrequency(LinkedHashMap<String, Long> wordFrequency, HashMap<String, Long> allWord) {
        allWord.entrySet().stream()
                .sorted(Comparator.<Entry<String, Long>>comparingLong(Entry::getValue).reversed()
                        .thenComparing(Entry::getKey))
                .limit(10).forEach(e -> wordFrequency.put(e.getKey(), e.getValue()));
    }

    private long solveLineCount(Path path) throws IOException {
        return Files.lines(path).filter(CounterHelper::isNotBlank).count();
    }

    public FileCounter(Path path) throws IOException {
        charCount = solveCharCount(path);

        String[] words = solveWords(path);

        wordCount = solveWordCount(words);

        operateWordFrequency(wordFrequency, solveAllWord(words));

        lineCount = solveLineCount(path);
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
