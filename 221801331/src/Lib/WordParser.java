package Lib;

import java.util.Map;

//单词解析器接口
public interface WordParser
{
  /**
   * 解析并统计单词
   * @return
   */
  int countWord(String text);

  /**
   * 获取单词统计map中前size位
   * @return
   */
  Map<String, Integer> getWordCountMapBySize(int size);
}
