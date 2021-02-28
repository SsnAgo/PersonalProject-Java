package lib.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FilePrinter
{
    /**
     * @param charCnt
     * @param wordCnt
     * @param lineCnt
     * @param freqList
     * @param fileName
     */
    public static void writeFile(int charCnt, int wordCnt, int lineCnt, ArrayList<HashMap.Entry<String, Long>> freqList,
            String fileName)
    {
        File file = new File(fileName);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try
        {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("characters: " + charCnt + "\n");
            bufferedWriter.write("words: " + wordCnt + "\n");
            bufferedWriter.write("lines: " + lineCnt + "\n");
            for (HashMap.Entry<String, Long> map : freqList)
            {
                bufferedWriter.write(map.getKey() + ": " + map.getValue() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error Writing File");
            e.printStackTrace();
        }
    }
}
