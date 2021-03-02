import java.io.*;
import java.util.*;

public class Lib
{
    Lib lib = new Lib();

    /**
     * 文件读取数据
     * @param filePath
     * @return 
     */
    public static String readFromFile(String filePath) 
    {
        int temp;
        BufferedReader br = null;
        StringBuilder builder = null;
        try
        {
            br = new BufferedReader(new FileReader(filePath));
            builder = new StringBuilder();
            //用read()可以按字符读入数据
            while((temp = br.read()) != -1)
            {
                builder.append((char)temp);
            }
        }
        //文件不存在或目录不存在
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e) 
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                br.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
