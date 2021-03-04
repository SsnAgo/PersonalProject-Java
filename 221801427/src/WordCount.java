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
        String content = new String();
        Map<String, Long> words;

        content = FileReader.readFile(inputFileName);
        charCnt = CharCounter.countChar(content);
        lineCnt = LineCounter.countLine(content);

        words = StringAnalyser.analyseString(content);
        wordCnt = WordCounter.countWord(words);
        freqList = FrequencySorter.sortFrequency(words);
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
