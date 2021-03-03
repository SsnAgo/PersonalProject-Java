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

    /**
     * write result string to output file
     * @param filename
     * @param message
     * @throws IOException
     */
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

    public static void main(String[] args){

        // throw Exception if params error
        if (args.length != 2){
            System.out.println("you should  input 2 parameters!");
            return ;
        }
        String inputFile = args[0];
        String outputFile = args[1];
        // start count process
        try {
            // count characters
            Counters.countCharacters(inputFile);
            // count lines
            List<String> lineList = Utils.getLineList(inputFile);
            Counters.countLines(lineList);
            // count words
            Map<String, Integer> stringIntegerMap = Counters.countWordsAndTransform(lineList);
            stringIntegerMap = Utils.sortMapByNum(stringIntegerMap);
            // get output and write to File
            StringBuilder sb = Utils.generateOutputString(stringIntegerMap);
            writeOutputToFile(outputFile,sb.toString());
            System.out.println("write data to " + outputFile + " successfully!");

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
