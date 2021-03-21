import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * @description    用于和文件交互的模块
 */
public class FileTool {
    public  BufferedReader InputReader;
    public  BufferedWriter OutputWriter;

    /**
     * @description      获取reader的函数
     * @param filePath  要被读取的文件路径
     */
    BufferedReader getReader(String filePath)
    {
        File file = new File(filePath);
         InputReader = null;
        try
        {
            InputReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));//获取字符流
            String line;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Error！");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return InputReader;
    }

    /**
     * @description      获取writer的函数
     * @param filePath  要被写的文件路径
     */
    BufferedWriter getWriter(String filePath)
    {
        OutputWriter = null;
        try
        {
            File file = new File(filePath);
            OutputWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));//获取字符流
            String line;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Error！");
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return OutputWriter;
    }

    /**
     * @description      关闭Reader的函数
     */
    Boolean closeReader()
    {
        try
        {
            if (InputReader != null)
            {
                InputReader.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return  false;
        }
        return  true;
    }

    /**
     * @description      关闭Writer的函数
     */
    Boolean closeWriter()
    {
        try
        {
            if (OutputWriter != null)
            {
                OutputWriter.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return  true;
    }
    /**
     * @description      将文件中所有字符转化为一个string并返回
     */
    String getFileString()
    {
        StringBuilder Builder = new StringBuilder();
        int AsciiChar;
        try {
            //按字符读取
            while ((AsciiChar = InputReader.read()) != -1)
            {
                Builder.append((char) AsciiChar);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Builder.toString();
    }
    public void writeResult(int CharNums, int RowNums, int WordNums, ArrayList<Map.Entry<String, Integer>> TopList)
    {
        try {
            OutputWriter.write("characters: "+CharNums+"\n");
            OutputWriter.write("words: "+WordNums+"\n");
            OutputWriter.write("lines: "+RowNums+"\n");
            for (Map.Entry<String, Integer>TopWord: TopList)
            {
                OutputWriter.write(TopWord.getKey()+": "+TopWord.getValue()+"\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
