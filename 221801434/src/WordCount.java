import java.io.*;
import java.util.Map;

import java.util.TreeMap;
public class WordCount {

    public static void main(String[] args) throws IOException{
        // write your code here
        Lib.countChar(args[0]);
        Lib.countWord(args[0]);
        Lib.countLine(args[0]);
        Lib.countKeyword(args[0]);

        FileWriter file=new FileWriter(args[1]);
        BufferedWriter out = new BufferedWriter(file);
        out.write("characters: "+Lib.charnum+"\n");
        out.write("words: "+Lib.wordnum+"\n");
        out.write("lines: "+Lib.linenum+"\n");
        int N=10;int n=Lib.wordnum;
        for(int i=0;i<=N;i++)
        {

            int max=0;
            String maxs=null;
            for (String key : Lib.word.keySet()) {
                if(max<(int) Lib.word.get(key))
                {
                    max=(int) Lib.word.get(key);
                    maxs=key;

                }
                if(max==(int) Lib.word.get(key))
                {
                    if(maxs.compareTo(key)>0)
                    {
                        max=(int) Lib.word.get(key);
                        maxs=key;
                    }
                }
            }
            out.write(maxs+":"+max+"\n");
            Lib.word.remove(maxs);
            n--;
            if(n<=0) break;
        }
        out.close();
        System.out.println("文件创建成功！");
    }


}
