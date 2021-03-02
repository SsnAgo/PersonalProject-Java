import java.io.*;

/**
 * IO相关的处理器
 */
public class IOHandler
{
  /**
   * 文件预处理
   * @param inputFile
   * @return
   */
  public static String filePretreatment(String inputFile)
  {
    try
    {
      BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(inputFile),"utf-8"));
      StringBuilder resultBuilder=new StringBuilder();
      int r;
      while((r=reader.read())!=-1)    //读取文件
      {
        if(r>0&&r<128)      //判断是否为ASCII码
        {
          resultBuilder.append((char) r);
        }
      }
      return resultBuilder.toString();    //返回预处理后的文本内容
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
      System.out.println("无法找到该路径下的文件!!!");
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("文件内容读取异常!!!");
    }
    return null;
  }


  /**
   * 将文本内容写入文件
   */
  public static void writeToFile(String outputFile,String textContent)
  {
    try
    {
      BufferedWriter writer=new BufferedWriter
          (new OutputStreamWriter(new FileOutputStream(outputFile),"utf-8"));

      writer.write(textContent);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
      System.out.println("无法找到该路径下的文件!!!");
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
      System.out.println("不支持的编码类型!!!");
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("IO异常!!!");
    }


  }
}
