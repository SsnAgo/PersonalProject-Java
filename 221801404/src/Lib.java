import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lib
{
    //从文件中读取数据
    public static String readFile(String filePath)
    {
        int flag;
        StringBuffer content = new StringBuffer();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            //将字符一个个读入content
            while((flag=br.read()) != -1)
            {
                content.append((char)flag);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

}
