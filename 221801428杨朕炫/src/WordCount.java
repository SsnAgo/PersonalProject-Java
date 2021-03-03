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

    /** save the status code of result
     *      1000 : success
     *      1001 : params error
     *      1002 : file type error
     *      1003 : No file found
     *      1004 : Read Or Write Error
     *      1005 : Other errors
      */
    private static int statusCode;

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

    /**
     * whether input file name and output file name form of *.txt
     * @param args
     */
    public static boolean isValidFile(String[] args){

        String[] inputFileSplit = args[0].split("\\.");
        String[] outputFileSplit = args[1].split("\\.");

        if (inputFileSplit.length < 2 || outputFileSplit.length < 2) return false;

        String inputFileExt = inputFileSplit[inputFileSplit.length - 1];
        String outputFileExt = outputFileSplit[outputFileSplit.length - 1];

        return inputFileExt.trim().equals("txt") && outputFileExt.trim().equals("txt");

    }

    /**
     * whether input file name valid
     * @param code
     */

    public static void setStatusCode(int code) {
        statusCode = code;
    }

    public static int getStatusCode() {
        return statusCode;
    }

    public static void main(String[] args){

        // throw Exception if params error
        if (args.length != 2){
            setStatusCode(1001);
            System.out.println("you should  input 2 parameters!");
            return ;
        }



        if (!isValidFile(args)) {
            setStatusCode(1002);
            System.out.println("File name error or File Type Error , only receive txt file !");
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
            setStatusCode(1000);
            System.out.println("write data to " + outputFile + " successfully!");

        } catch (FileNotFoundException e) {
            setStatusCode(1003);
            System.out.println("No Such File Found!");
            return;
        } catch (IOException e) {
            setStatusCode(1004);
            System.out.println("Read Or Write File Error!");
            return;
        } catch (RuntimeException e) {
            setStatusCode(1005);
            System.out.println(e.getMessage());
            return;
        }


    }
}
