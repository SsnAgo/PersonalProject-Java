import java.io.*;
import java.util.Map;

import java.util.TreeMap;

/**
 * WordCount用于接收参数并
 * 输出字符统计的结果到文档中
 */
public class WordCount {

    public static void main(String[] args) throws IOException{
        Lib.countChar(args[0]);
        Lib.countWord(args[0]);
        Lib.countLine(args[0]);
        Lib.countKeyword();

        //打开文件并写入
        FileWriter file=new FileWriter(args[1]);
        BufferedWriter out = new BufferedWriter(file);
        out.write("characters: "+Lib.charnum+"\n");
        out.write("words: "+Lib.wordnum+"\n");
        out.write("lines: "+Lib.linenum+"\n");
        //对十个键值对进行排序输出
        for(int k=0;k<Lib.realnum;k++)
        {
            int max=0;
            String maxs=null;
            for(String key:Lib.keyWord.keySet()){
                if(max<(int) Lib.keyWord.get(key))
                {
                    max=(int) Lib.keyWord.get(key);
                    maxs=key;
                }
                if(max==(int) Lib.keyWord.get(key))
                {
                    if(maxs.compareTo(key)>0)
                    {
                        max=(int) Lib.keyWord.get(key);
                        maxs=key;
                    }
                }
            }
            out.write(maxs+": "+max+"\n");
            Lib.keyWord.remove(maxs);
        }
        out.close();
    }


}