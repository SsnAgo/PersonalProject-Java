package info.tozzger.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

class FileCounterHelper {

    static long solveCharCount(Path path) {
        return path.toFile().length();
    }

    static String[] solveWords(Path path) throws IOException {
        return Files.lines(path)// 分行
                .map(String::toLowerCase)// 转小写
                .flatMap(CounterHelper::split)// 分词
                .filter(CounterHelper::isWord1)// 保留单词
                .toArray(String[]::new);
    }

    static long solveWordCount(String[] words) {
        return words.length;
    }

    private static final BiConsumer<HashMap<String, Long>, String> MERGE = (map, word) -> map.merge(word, 1L,
            Long::sum);

    static HashMap<String, Long> solveAllWord(String[] words) {
        return Arrays.stream(words).collect(HashMap<String, Long>::new, MERGE, HashMap<String, Long>::putAll);
    }

    private static final Comparator<Entry<String, Long>> COMPARTOR = Comparator
            .<Entry<String, Long>>comparingLong(Entry::getValue).reversed().thenComparing(Entry::getKey);

    static void operateWordFrequency(LinkedHashMap<String, Long> wordFrequency, HashMap<String, Long> allWord) {
        allWord.entrySet().stream().sorted(COMPARTOR).limit(10)
                .forEach(e -> wordFrequency.put(e.getKey(), e.getValue()));
    }

    static long solveLineCount(Path path) throws IOException {
        return Files.lines(path).filter(CounterHelper::isNotBlank).count();
    }

}
