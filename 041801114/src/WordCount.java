import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;

import info.tozzger.demo.FileCounter;

public class WordCount {

    public static void main(String[] args) throws IOException {
        solve(Paths.get(args[0]), Paths.get(args[1]));
    }

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

    public static class Printer implements Closeable {

        private final PrintStream ps;

        private Printer(Path path) throws FileNotFoundException {
            ps = new PrintStream(path.toFile());
        }

        @Override
        public void close() throws IOException {
            ps.close();
        }

        public void printfln(String format, Object... args) {
            ps.format(format.concat("\n"), args);
        }

    }

}