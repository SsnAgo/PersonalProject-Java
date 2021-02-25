package lib.service;

import java.io.*;

public class CharCounter
{
    /**
     * @param fileName
     * @return cnt
     */
    public static int countChar(BufferedReader bf)
    {
        int b = 0;
        int cnt = 0;

        try
        {
            while ((b = bf.read()) != -1)
            {
                if (b != 13)
                {
                    cnt++;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return cnt;
    }
}
