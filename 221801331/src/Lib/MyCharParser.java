package Lib;

/**
 * 字符解析器
 */
public class MyCharParser implements CharParser
{
    private boolean isLineHasChar=false;    //标志位,判断该行是否有非空白字符

    private String textContent;

    /**
     * 默认构造方法
     */
    public MyCharParser()
    {
    }

  /**
   * 判断是否为有效字符
   * @param c
   * @return
   */
    private boolean isCharValid(char c)
    {
      if((int)c>0&&(int)c<128)
      {
        return true;
      }
      else
      {
        return false;
      }
    }

    /**
     * 解析文本;解析后继续统计
     * @return
     */
    @Override
    public String parseText(String text)
    {
      char c;
      StringBuilder sb=new StringBuilder();   //保存解析结果
      for (int i=0;i<text.length();i++)
      {
        c = text.charAt(i);
        if (isCharValid(c))     //判断是否为有效字符
        {
          sb.append(c);     //若有效则加入stringbulider
        }
      }
      textContent=sb.toString();
      return textContent;   //返回解析结果
    }

    /**
     * 统计有效字符
     * @return
     */
    @Override
    public int countValidChars()
    {

      return textContent.length();
    }

    /**
     * 统计有效行数
     * @return
     */
    @Override
    public int countValidLines()
    {
      char c;
      int num=0;
      for (int i=0;i<textContent.length();i++)
      {
        c=textContent.charAt(i);
        if(c!='\t'||c!='\r'||c!='\n'||c!=' ')      //非空白字符
        {
          isLineHasChar=true;
        }
        if(c=='\n'||i==textContent.length()-1)    //遇到换行或文本内容结束
        {
          if(isLineHasChar==true)   //若改行有非空字符
          {
            num++;          //计数
            isLineHasChar=false;
          }
        }
      }
      return num;
    }

    //get、set方法
    public String getTextContent()
    {
      return textContent;
    }

    public void setTextContent(String textContent)
    {
      this.textContent = textContent;
    }

    /**
    * toString方法
    * @return
    */
    @Override
    public String toString()
    {
      return "MyCharParser{" +
        "isLineHasChar=" + isLineHasChar +
        ", textContent='" + textContent + '\'' +
        '}';
    }
}
