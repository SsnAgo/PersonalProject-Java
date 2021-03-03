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

    public static BufferedWriter openOutputFile(String fileName) {
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedWriter;
    }

    public static void characterCount(String inputFile,BufferedWriter bufferedWriter){
        BufferedReader bufferedReader = Lib.openInputFile(inputFile);
        int count = 0;
        int temp;

        try {
            while((temp = bufferedReader.read()) != -1){
                count ++;
            }

            bufferedWriter.write("characters:"+count+'\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

