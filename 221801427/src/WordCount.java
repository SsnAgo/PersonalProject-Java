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
        InputStreamReader isr = null;
        BufferedReader bf = null;
        String line;

        try
        {
            isr = new InputStreamReader(new FileInputStream(inputFileName));
            bf = new BufferedReader(isr);
            StringBuffer contents = new StringBuffer();
            String regex = "[^0-9A-Za-z]";

            line = bf.readLine();
            while (line != null)
            {
                if (!line.equals(""))
                {
                    lineCnt++;
                }
                contents.append(line);
                line = bf.readLine();
                if (line != null)
                {
                    contents.append("\n");
                }
            }
            content = contents.toString().toLowerCase().replaceAll(regex, "|");

            charCnt = CharCounter.countChar(content);
            wordCnt = WordCounter.countWord(content);
            freqList = FrequencySorter.sortFrequency(content);

            bf.close();
            isr.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void Print()
    {
        FilePrinter.writeFile(charCnt, wordCnt, lineCnt, freqList, outputFileName);
    }

    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("Invalid input");
            return;
        }
        WordCount cmd = new WordCount(args[0], args[1]);
        cmd.Count();
        cmd.Print();
    }
}
