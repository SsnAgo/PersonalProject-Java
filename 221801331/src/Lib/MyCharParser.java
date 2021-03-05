package Lib;

/**
 * 根据本次作业需求实现的字符解析器
 */
public class MyCharParser implements CharParser
{
    private boolean isLineHasChar=false;    //标志位,判断该行是否有非空白字符

    private String textContent;   //文本内容

    /**
     * 默认构造方法
     */
    public MyCharParser()
    {
    }

  /**
   * 判断是否为有效字符（这里指ASCII码）
   * @param c
   * @return
   */
    private boolean isCharValid(char c)
    {
      if((int)c>0&&(int)c<128)    //判断是否为ASCII码
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
      StringBuilder sb=new StringBuilder();   //用于保存解析结果
      for (int i=0;i<text.length();i++)   //遍历解析前的文本内容
      {
        c=text.charAt(i);
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
      for (int i=0;i<textContent.length();i++)    //遍历解析后的文本内容
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
            isLineHasChar=false;    //重置标志位
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
