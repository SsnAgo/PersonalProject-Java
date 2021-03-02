import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {

    //java WordCount input.txt output.txt

    private static int charactersCount = 0;
    private static int linesCount = 0;
    private static int wordsCount = 0;

    /**
     * calculate the number of characters in input file
     *
     * @param filename
     */
    public static void countCharacters(String filename) throws FileNotFoundException, IOException, RuntimeException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filename);
            int c;
            boolean content = false;
            while ((c = fileReader.read()) != -1) {
                charactersCount++;
                if (!content) content = true;
            }
            if (!content) {
                throw new RuntimeException("No content found!");
            }
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * get a List contains every line in the input file
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static List<String> getLineList(String filename) throws IOException {
        Path path = Path.of(filename);
        Stream<String> stringStream = Files.lines(path);
        return stringStream.collect(Collectors.toList());
    }

    /**
     * calculate the number of valid lines(expects lines that just contain space) in input file
     *
     * @param list
     */
    public static void countLines(List<String> list) {
        list.forEach(x -> {
            if (!x.trim().equals("")) {
                linesCount++;
            }
        });
    }


    public static void main(String[] args) throws IOException {

        // throw Exception if params error
        if (args.length < 2){
            throw  new InvalidParameterException("you should at least input 2 parameters!");
        }
        String inputFile = args[0];
        // make sure input file exist
        String outputFile = args[1];
        try {
            // 主体代码
            countCharacters(inputFile);
            List<String> lineList = getLineList(inputFile);
            countLines(lineList);

        } catch (FileNotFoundException e) {
            System.out.println("No Such File Found!");
            return;
        } catch (IOException e) {
            System.out.println("Read Or Write File Error!");
            return;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }


    }
}