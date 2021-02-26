import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Lib {
    public static Reader openInputFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Reader reader = new InputStreamReader(new FileInputStream(file));
        return reader;
    }

    public static Writer openOutputFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        return writer;
    }

    //统计字符数，空格，水平制表符，换行符，均算字符
    public static int charactersCount(String inputFile, String outputFile) throws IOException {
        Reader reader = openInputFile(inputFile);
        int num = 0;
        int temp;
        while ((temp = reader.read()) != -1) {
            num++;
        }
        return num;
    }
}
