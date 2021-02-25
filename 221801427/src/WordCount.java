import lib.service.*;
import lib.tool.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordCount
{
    /**
     * @param inputFileName
     * @param outputFileName
     */
    private static String inputFileName;
    private static String outputFileName;
    private int charCnt = 0;

    public WordCount(String inputFileName, String outputFileName)
    {
        WordCount.inputFileName = inputFileName;
        WordCount.outputFileName = outputFileName;
    }

    public void Count()
    {
        InputStreamReader isr = null;
        BufferedReader bf = null;
        try
        {
            isr = new InputStreamReader(new FileInputStream(inputFileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        if (isr != null)
        {
            bf = new BufferedReader(isr);
            charCnt = CharCounter.countChar(bf);
            try
            {
                isr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void Print()
    {
        FilePrinter.writeFile(charCnt, outputFileName);
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
