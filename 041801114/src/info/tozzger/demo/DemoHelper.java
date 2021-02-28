package info.tozzger.demo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map.Entry;

public class DemoHelper {
    public static void solve(Path in, Path out) throws IOException {
        FileCounter fc = new FileCounter(in);
        Printer printer = new Printer(out);

        printer.printfln("characters: %d", fc.getCharCount());
        printer.printfln("words: %d", fc.getWordCount());
        printer.printfln("lines: %d", fc.getLineCount());
        for (Entry<String, Long> e : fc.getWordFrequency().entrySet()) {
            printer.printfln("%s: %d", e.getKey(), e.getValue());
        }

        printer.close();
    }
}
