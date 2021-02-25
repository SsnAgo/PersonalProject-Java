import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args){
        try{
            FileReader inputFile = new FileReader(args[0]);
            FileWriter ouputFile = new FileWriter(args[1]);
            Counter counter = new Counter(inputFile,ouputFile);

        }
        catch(Exception e){
        }
    }
}
