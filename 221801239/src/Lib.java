import java.io.*;
import java.util.*;

public class Lib {

    public static BufferedReader openInputFile(String fileName) {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bufferedReader;
    }
}

