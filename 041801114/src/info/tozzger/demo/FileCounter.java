package info.tozzger.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class FileCounter implements CounterImpl {

    private static final Pattern WORD_PATTERN = Pattern.compile("[a-z]{4,}[a-z0-9]");

    private static boolean isWord(String str) {
        return WORD_PATTERN.matcher(str).matches();
    }
    private static boolean isNotBlank(String str) {
        return !str.trim().isEmpty();
    }

    public FileCounter(Path path) throws IOException {
        charCount = path.toFile().length();

        String[] words = Files.lines(path).map(String::toLowerCase)
                .flatMap(str -> Arrays.stream(str.split("[^a-z0-9]+"))).filter(FileCounter::isWord)
                .toArray(String[]::new);
        wordCount = words.length;

        HashMap<String, Long> allWord = Arrays.stream(words).collect(() -> new HashMap<String, Long>(),
                (map, word) -> map.merge(word, 1L, Long::sum), HashMap<String, Long>::putAll);

        allWord.entrySet().stream()
                .sorted(Comparator.<Entry<String, Long>>comparingLong(Entry::getValue).reversed()
                        .thenComparing(Entry::getKey))
                .limit(10).forEach(e -> wordFrequency.put(e.getKey(), e.getValue()));

        lineCount = Files.lines(path).filter(FileCounter::isNotBlank).count();
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
