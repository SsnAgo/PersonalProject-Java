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
     * compare two String type values by dictionary order
     * @param str1
     * @param str2
     * @return
     */
    public static int compareStringByDict(String str1, String str2) {
        for (int i = 0, j = 0; i < str1.length() && j < str2.length(); i++, j++) {
            if (str1.charAt(i) >= str2.charAt(i)) {
                return -1;
            } else if (str1.charAt(i) < str2.charAt(i)) {
                return 1;
            }
        }
        if (str1.length() == str2.length()) {
            return 0;
        } else if (str1.length() > str2.length()) {
            return 1;
        } else {
            return -1;
        }
    }


    /**
     * sort Map by the count of words
     *
     * @param map
     * @return
     */
    public static Map<String, Integer> sortMapByCount(Map<String, Integer> map) {
        HashMap<String, Integer> finalOut = new LinkedHashMap<>();

        map.entrySet()
                .stream()
                .sorted((p1, p2) -> {
                    if (p1.getValue() != p2.getValue()) {
                        return p2.getValue().compareTo(p1.getValue());
                    } else {
                        return compareStringByDict(p2.getKey(),p1.getKey());
                    }
                })
                .collect(Collectors.toList()).forEach(ele -> finalOut.put(ele.getKey(), ele.getValue()));
        return finalOut;
    }

    /**
     * calculate the count of words and put valid words into HashMap
     *
     * @param list
     * @return
     */
    public static Map<String, Integer> calculateWordAndTransform(List<String> list) {

        Map<String, Integer> map = new HashMap<>();
        Pattern pattern = Pattern.compile("[A-Za-z]{4}.*?");
        list.forEach(x -> {
            String[] strings = x.split("\\s+|\\t");
            for (String s : strings) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.matches()) {
                    wordsCount++;
                    map.merge(s, 1, Integer::sum);
                }
            }
        });
        return map;


    }

    /**
     * generate output string message
     * @param map
     * @return
     */
    public static StringBuilder generateOutputString(Map<String,Integer> map){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("characters: %s\nlines: %s\nwords: %s\n",charactersCount,linesCount,wordsCount));
        int i = 0;
        for (Map.Entry<String,Integer> entry :map.entrySet()){
            if (i++ < 10){
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }else break;
        }
        return sb;
    }

    public static void writeOutputToFile(String filename,String message) throws IOException{
        FileWriter writer = null;
        try{
            writer = new FileWriter(filename);
            writer.write(message);
        }finally {
            try{
                if (writer != null) writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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
            countCharacters(inputFile);
            List<String> lineList = getLineList(inputFile);
            countLines(lineList);
            Map<String, Integer> stringIntegerMap = calculateWordAndTransform(lineList);
            stringIntegerMap = sortMapByCount(stringIntegerMap);
            StringBuilder sb = generateOutputString(stringIntegerMap);
            writeOutputToFile(outputFile,sb.toString());

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