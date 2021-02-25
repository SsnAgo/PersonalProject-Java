package lib.tool;

import java.io.*;

public class FilePrinter
{
    public static void writeFile(int charCnt, String fileName)
    {
        File file = new File(fileName);
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("characters: " + charCnt + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
