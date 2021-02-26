import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Text file analyser
 */
public class Lib {

    private String filename;

    public Lib(String filename) {
        this.filename = filename;
    }

    /**
     * Read text file by line
     * TODO make private
     *
     * @param l callback after read a line successfully
     */
    public void readFileByLine(final OnLineReadListener l) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        try {
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                l.onRead(line);
            }
        } catch (IOException e) {
            reader.close();
            throw e;
        }
    }

    /**
     * callback after read a line successfully
     */
    interface OnLineReadListener {
        /**
         * @param line successful read line(without line-termination characters)
         */
        public void onRead(@NotNull String line);
    }
}
