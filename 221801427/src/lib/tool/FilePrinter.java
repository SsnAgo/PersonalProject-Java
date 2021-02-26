package lib.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FilePrinter
{
    public static void writeFile(int charCnt, int wordCnt, int lineCnt, ArrayList<HashMap.Entry<String, Long>> freqList,
            String fileName)
    {
        File file = new File(fileName);
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("characters: " + charCnt + "\n");
            bufferedWriter.write("words: " + wordCnt + "\n");
            bufferedWriter.write("lines: " + lineCnt + "\n");
            for (HashMap.Entry<String, Long> map : freqList)
            {
                bufferedWriter.write("<" + map.getKey() + ">: " + map.getValue() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
