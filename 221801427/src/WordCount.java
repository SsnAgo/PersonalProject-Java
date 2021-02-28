import lib.service.*;
import lib.tool.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordCount
{
    /**
     * @param inputFileName
     * @param outputFileName
     */
    private static String inputFileName;
    private static String outputFileName;
    private static String content = new String();

    private int charCnt = 0;
    private int wordCnt = 0;
    private int lineCnt = 0;

    ArrayList<HashMap.Entry<String, Long>> freqList;

    public WordCount(String inputFileName, String outputFileName)
    {
        WordCount.inputFileName = inputFileName;
        WordCount.outputFileName = outputFileName;
    }

    public void Count()
    {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try
        {
            inputStreamReader = new InputStreamReader(new FileInputStream(inputFileName));
            bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder contents = new StringBuilder();
            Map<String, Long> words;

            int in;
            while ((in = bufferedReader.read()) != -1)
            {
                contents.append((char) in);
            }
            content = contents.toString().toLowerCase();

            charCnt = CharCounter.countChar(content);
            lineCnt = LineCounter.countLine(content);
            words = StringAnalyser.analyseString(content);
            wordCnt = WordCounter.countWord(words);
            freqList = FrequencySorter.sortFrequency(words);

            bufferedReader.close();
            inputStreamReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("Error Reading File");
            e.printStackTrace();
        }
    }

    public void Print()
    {
        FilePrinter.writeFile(charCnt, wordCnt, lineCnt, freqList, outputFileName);
    }

    public static void main(String[] args)
    {
        WordCount cmd;
        if (args.length != 2)
        {
            System.out.println("Invalid input");
            return;
        }
        cmd = new WordCount(args[0], args[1]);
        cmd.Count();
        cmd.Print();
    }
}
