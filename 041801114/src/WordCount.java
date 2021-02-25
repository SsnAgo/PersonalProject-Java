import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map.Entry;

import info.tozzger.demo.FileCounter;

public class WordCount{
    
    public static void main(String[] args) throws IOException {
        FileCounter fc = new FileCounter(new File(args[0]).toPath());
        Printer out = new Printer(new File(args[1]));
        /*
         * characters: number
         * words: number
         * lines: number
         * word1: number
         * word2: number
         * ...
         */
        out.printfln("characters: %d", fc.getCharCount());
        out.printfln("words: %d", fc.getWordCount());
        out.printfln("lines: %d", fc.getLineCount());
        for (Entry<String, Long> e : fc.getWordFrequency().entrySet()) {
            out.printfln("%s: %d", e.getKey(), e.getValue());
        }
        out.close();
    }
    
    private static class Printer implements Closeable{
        private final PrintStream ps;
        private Printer(File file) throws FileNotFoundException {
            ps = new PrintStream(file);
        }
        @Override
        public void close() throws IOException {
            ps.close();
        }
        public void printfln(String format, Object...args) {
            ps.format(format.concat("\n"), args);
        }
    }
    
}