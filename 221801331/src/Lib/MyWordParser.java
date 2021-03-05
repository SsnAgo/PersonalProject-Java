package Lib;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 根据本次作业需求实现的单词解析器
 */
public class MyWordParser implements WordParser
{
  private Map<String,Integer> wordCountMap;   //单词频率统计

  private int letterNum=0;    //连续字母数量

  private boolean isWordReading=false; //单词读取位；判断是否正在读取单词

  private boolean isThisWordValid=true;   //单词合法判断位;判断当前单词是否合法

  private StringBuilder wordReader=new StringBuilder(); //单词读取器

  /**
   * 默认构造方法
   */
  public MyWordParser()
  {
    wordCountMap=new HashMap<>();
  }

  /**
   * 判断是否是字母
   * @param c
   * @return
   */
  private boolean isLetter(char c)
  {
    if((c>='A'&&c<='Z')||(c>='a'&&c<='z'))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * 判断是否是有效字符（这里的有效字符指字母和数字）
   * @param c
   * @return
   */
  private boolean isValidChar(char c)
  {
    if((c>='A'&&c<='Z')||(c>='a'&&c<='z')||(c>='0'&&c<='9'))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * 记录单词;统计数量
   * @param num
   * @return
   */
  private int recordWord(int num)
  {
    isWordReading=false;    //单词读取结束，读取位改为false
    num++;   //单词数量+1
    String word=wordReader.toString().toLowerCase();    //将单词转换为全小写
    Integer count;
    wordCountMap.put(word,(count=wordCountMap.get(word))==null?1:count+1);  //更新map
    return num; //返回数量
  }

  /**
   * 结束本单词读取；开始读取下一单词
   */
  private void endThisWordReading()
  {
    isThisWordValid=true;   //单词读取结束,重置合法位
    letterNum=0;  //清空连续字母数量
    wordReader.delete(0,wordReader.length());   //清空单词读取器
  }

  /**
   * 统计单词
   * @return
   */
  @Override
  public int countWord(String text)
  {
    char c;
    int num=0;
    for (int i=0;i<text.length();i++)   //遍历文本内容
    {
      c=text.charAt(i);
      if(isWordReading)   //正在读取单词
      {
        if(isValidChar(c)) //若是有效字符
        {
          wordReader.append(c);   //加入单词读取器
        }
        else  //否则
        {
          num=recordWord(num);    //将单词记入map；并统计数量
          endThisWordReading();   //结束本单词的读取;开始读取下一单词
        }
      }
      else   //若否
      {
        if(!isValidChar(c))   //若为分隔符
        {
          endThisWordReading();  //结束本单词的读取;开始读取下一单词
          continue;
        }
        if(!isThisWordValid)    //若此单词已不合法
        {
          continue;
        }
        if(isLetter(c))     //若为字母
        {
          wordReader.append(c);  //加入单词读取器
          letterNum++;  //字母数量+1
        }
        else
        {
          isThisWordValid=false;    //此单词不合法;将合法位置false
        }
        if(letterNum>=4)    //连续字母数量超过4个
        {
          isWordReading=true;  //开始读取单词
        }
      }
    }
    if(isWordReading)
    {
      num=recordWord(num);    //将单词记入map；并统计数量
      endThisWordReading();   //结束本单词的读取
    }
    return num;
  }

  /**
   * 获取单词统计map
   * @return
   */
  @Override
  public Map<String, Integer> getWordCountMapBySize(int size)
  {
    return wordCountMap
        .entrySet()
        .stream()
        .sorted(Map.Entry.<String, Integer> comparingByValue() //按value排序（默认为升序）
            .reversed()//倒序
            .thenComparing(Map.Entry.comparingByKey()))//按照key排序(字典序)
        .limit(size) //选择前面n个
        .collect(  //以map形式返回
            Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                (oldValue, newValue) -> oldValue,
                LinkedHashMap::new    //返回LinkedHashMap
            )
        );
  }

  /**
   * get方法
   * @return
   */
  public Map<String, Integer> getWordCountMap()
  {
    return wordCountMap;
  }

  /**
   * toString方法
   * @return
   */
  @Override
  public String toString()
  {
    return "MyWordParser{" +
        "wordCountMap=" + wordCountMap +
        ", letterNum=" + letterNum +
        ", isWordReading=" + isWordReading +
        ", isThisWordValid=" + isThisWordValid +
        ", wordReader=" + wordReader +
        '}';
  }
}

