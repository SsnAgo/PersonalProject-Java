package lib.tool;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

public class FileReader
{
    /**
     * @param inputFileName
     * @return ¶ÁÈ¡³öµÄ×Ö·û´®
     */
    @SuppressWarnings("resource")
    public static String readFile(String inputFileName)
    {
        File file = new File(inputFileName);
        long len = file.length();
        MappedByteBuffer mappedByteBuffer = null;

        try
        {
            mappedByteBuffer = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, len);
            if (mappedByteBuffer != null)
            {
                return StandardCharsets.UTF_8.decode(mappedByteBuffer).toString().toLowerCase();
            }
            else
            {
                return "";
            }
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
        finally
        {
            if (mappedByteBuffer != null)
            {
                Cleaner cleaner = ((DirectBuffer) mappedByteBuffer).cleaner();
                if (cleaner != null)
                {
                    cleaner.clean();
                }
            }
        }
        return "";
    }
}
