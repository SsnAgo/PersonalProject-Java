package info.tozzger.demo;

import java.nio.file.Path;
import java.util.Map;

public class FileCounter implements CounterImpl {
    
    public FileCounter(Path path) {
        charCount = path.toFile().length();
    }

    private final long charCount;
    
    @Override
    public long getCharCount() {
        // TODO 自动生成的方法存根
        return charCount;
    }

    @Override
    public int getWordCount() {
        // TODO 自动生成的方法存根
        return 0;
    }

    @Override
    public int getLineCount() {
        // TODO 自动生成的方法存根
        return 0;
    }

    @Override
    public Map<String, Integer> getWordFrequency() {
        // TODO 自动生成的方法存根
        return null;
    }

}
