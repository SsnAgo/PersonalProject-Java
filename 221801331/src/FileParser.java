import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文件解析
 */
public class FileParser
{
  //文件
  File file;

  //输出文件路径
  String outputFile;

  //有效字符数量
  int validCharsNum;

  //有效行数量
  int validLinesNum;

  //单词数量
  int wordNum;

  //文件输入流
  BufferedInputStream inputStream;

  //文件输出流
  BufferedWriter outputStream;

  //单词统计
  Map<String,Integer> wordCountMap;


  /**
   * 默认构造方法
   */
  public FileParser()
  {
  }

  /**
   * 构造方法
   * @param file
   */
  public FileParser(File file,String outputFile) throws IOException
  {
    this.file = file;
    this.validCharsNum=0;
    this.validLinesNum=0;
    this.wordNum=0;
    this.wordCountMap=new HashMap<>();
    this.inputStream=new BufferedInputStream(new FileInputStream(file));
    this.outputFile=outputFile;
  }

  /**
   * 全构造方法
   * @param file
   * @param outputFile
   * @param validCharsNum
   * @param validLinesNum
   * @param inputStream
   * @param outputStream
   * @param wordCountMap
   */
  public FileParser(File file, String outputFile, int validCharsNum, int validLinesNum,int wordNum,
                    BufferedInputStream inputStream, BufferedWriter outputStream, Map<String, Integer> wordCountMap)
  {
    this.file = file;
    this.outputFile = outputFile;
    this.validCharsNum = validCharsNum;
    this.validLinesNum = validLinesNum;
    this.wordNum=wordNum;
    this.inputStream = inputStream;
    this.outputStream = outputStream;
    this.wordCountMap = wordCountMap;
  }

  /**
   * 统计有效字符
   * @return
   * @throws IOException
   */
  public int countValidChars() throws IOException
  {
    inputStream=new BufferedInputStream(new FileInputStream(file));
    int c;
    while((c=inputStream.read())!=-1)
    {
      if(c>0&&c<128)
      {
        validCharsNum++;
      }
    }
    inputStream.close();
    return c;
  }

  /**
   * 统计单词
   * @return
   * @throws IOException
   */
  public int countWord() throws IOException
  {
    inputStream=new BufferedInputStream(new FileInputStream(file));
    boolean isWordReading=false; //单词读取位；判断是否正在读取单词
    StringBuilder wordReader=new StringBuilder(); //单词读取器
    int c;
    int numToWord=0;    //连续字母数量
    while((c=inputStream.read())!=-1)  //读取文件
    {
      if(isWordReading)   //正在读取单词
      {
        if((c>='A'&&c<='Z')||(c>='a'&&c<='z')||(c>='0'&&c<='9')) //若是字母数字
        {
          wordReader.append((char)c);   //加入单词读取器
        }
        else  //否则
        {
          isWordReading=false;    //单词读取结束，读取位改为false
          wordNum++;   //单词数量+1
          Integer count;
          String word=wordReader.toString();
          numToWord=0;  //清空连续字母数量
          wordReader.delete(0,wordReader.length());   //清空单词读取器
          wordCountMap.put(word,(count=wordCountMap.get(word))==null?1:count+1);  //更新map
        }
      }
      else   //若否
      {
        if((c>='A'&&c<='Z')||(c>='a'&&c<='z')) //判断字符;若为字母
        {
          wordReader.append((char) c);  //加入单词读取器
          numToWord++;  //字母数量+1
        }
        if(numToWord>=4)    //连续字母数量超过
        {
          isWordReading=true;  //开始读取单词
        }
      }
    }
    inputStream.close();
    return wordNum;
  }

  /**
   * 统计有效行数
   * @return
   */
  public int countValidLines() throws IOException
  {
    inputStream=new BufferedInputStream(new FileInputStream(file));
    BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
    while (reader.readLine()!=null)
    {
      validLinesNum++;
    }
    inputStream.close();
    return validLinesNum;
  }

  //get
  public File getFile() {
    return file;
  }

  public String getOutputFile() {
    return outputFile;
  }

  public BufferedInputStream getInputStream() {
    return inputStream;
  }

  public BufferedWriter getOutputStream() {
    return outputStream;
  }

  /**
   * 获取有效字符数量
   * @return
   */
  public int getValidCharsNum() {
    return validCharsNum;
  }

  /**
   * 获取有效行数
   * @return
   */
  public int getValidLinesNum() {
    return validLinesNum;
  }

  /**
   * 获取单词数量
   * @return
   */
  public int getWordNum() {
    return wordNum;
  }

  /**
   * 获取单词统计map
   * @return
   */
  public Map<String, Integer> getWordCountMap()
  {
    return wordCountMap
        .entrySet() //获取set
        .stream()   //获取流
        .sorted(Map.Entry.<String, Integer> comparingByValue() //按照数值排序，默认升序
            .reversed()//倒序
            .thenComparing(Map.Entry.comparingByKey()))//按照key排序
        .limit(10) //选择最前面的十个
        .collect(  //以map形式返回
            Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldVal, newVal) -> oldVal,
                LinkedHashMap::new
            )
        );
  }

  /**
   * 输出到文件
   * @throws IOException
   */
  public void writeToFile() throws IOException
  {
    this.outputStream=new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(outputFile), "utf-8"));

    StringBuilder content=new StringBuilder("characters:"+validCharsNum+"\n" +
        "words:"+wordNum+"\n" +
        "lines:"+validLinesNum+"\n");
    Map<String,Integer> map=getWordCountMap();
    for (String key: map.keySet())
    {
        content.append(key+":"+map.get(key)+"\n");
    }
    outputStream.write(content.toString());

  }


}
