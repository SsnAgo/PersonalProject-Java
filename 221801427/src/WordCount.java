import lib.service.*;
import lib.tool.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WordCount
{
    /**
     * @param inputFileName
     * @param outputFileName
     */
    private static String inputFileName;
    private static String outputFileName;

    public WordCount(String inputFileName, String outputFileName)
    {
        WordCount.inputFileName = inputFileName;
        WordCount.outputFileName = outputFileName;
    }

    public void Count()
    {
        final String content = FileReader.readFile(inputFileName);
        HashMap<String, Integer> words;
        words = StringAnalyser.analyseString(content);

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> charCnt = executor.submit(new Callable<Integer>()
        {
            public Integer call()
            {
                return CharCounter.countChar(content);
            }
        });

        Future<Integer> lineCnt = executor.submit(new Callable<Integer>()
        {
            public Integer call()
            {
                return LineCounter.countLine(content);
            }
        });

        Future<Integer> wordCnt = executor.submit(new Callable<Integer>()
        {
            public Integer call()
            {
                return WordCounter.countWord(words);
            }
        });

        Future<ArrayList<HashMap.Entry<String, Integer>>> freqList = executor
                .submit(new Callable<ArrayList<HashMap.Entry<String, Integer>>>()
                {
                    public ArrayList<HashMap.Entry<String, Integer>> call()
                    {
                        return FrequencySorter.sortFrequency(words);
                    }
                });

        try
        {
            FilePrinter.writeFile(charCnt.get(), wordCnt.get(), lineCnt.get(), freqList.get(), outputFileName);
            executor.shutdown();
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
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
    }
}
