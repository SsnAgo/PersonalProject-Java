import java.io.*;

/**
 * @description    用于和文件交互的模块
 */
public class FileTool {
    public  BufferedReader Reader;
    public  BufferedWriter Writer;

    /**
     * @description      获取reader的函数
     * @param filePath  要被读取的文件路径
     */
    BufferedReader getReader(String filePath)
    {
        File file = new File(filePath);
         Reader = null;
        try
        {
            Reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));//获取字符流
            String line;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Reader;
    }

    /**
     * @description      获取writer的函数
     * @param filePath  要被写的文件路径
     */
    BufferedWriter getWriter(String filePath)
    {
        File file = new File(filePath);
        System.out.println(file.toString());
        Writer = null;
        try
        {
            Writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));//获取字符流
            String line;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Writer;
    }

    /**
     * @description      关闭Reader的函数
     */
    void closeReader()
    {
        try
        {
            if (Reader != null)
            {
                Reader.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @description      关闭Writer的函数
     */
    void closeWriter()
    {
        try
        {
            if (Writer != null)
            {
                Writer.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
