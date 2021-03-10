package Lib;

/**
 * 字符解析器接口
 */
public interface CharParser
{
  /**
   * 解析文本;解析后进行统计
   * @return
   */
  String parseText(String text);

  /**
   * 统计有效字符
   * @return
   */
  int countValidChars();

  /**
   * 统计有效行数
   * @return
   */
  int countValidLines();
}
