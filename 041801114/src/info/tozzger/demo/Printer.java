package info.tozzger.demo;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

public class Printer implements Closeable {

    private final PrintStream ps;

    public Printer(Path path) throws FileNotFoundException {
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