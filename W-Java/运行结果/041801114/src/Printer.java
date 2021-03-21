import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;

class Printer implements Closeable {

    private final PrintStream ps;

    Printer(Path path) throws FileNotFoundException, UnsupportedEncodingException {
        ps = new PrintStream(path.toFile(), StaticField.CHARSET);
    }

    @Override
    public void close() throws IOException {
        ps.close();
    }

    void printfln(String format, Object... args) {
        ps.format(format.concat(StaticField.LINE_SEPARATOR), args);
    }

}