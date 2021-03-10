import java.io.*;
import java.util.List;
import java.util.Map;

public class WordCount
{
    public static void main(String[] args) throws IOException
    {
        //long start = System.currentTimeMillis();
        if(args.length < 2)
        {
            System.out.println("请输入两个文件路径！！！！");
        }
        String filePath = args[0];
        String outFilePath = args[1];
        String content = Lib.readFile(filePath);
        int charactersNum = Lib.getCharacterNum(content);
        int linesNum = Lib.getLinesNum(content);
        int wordsNum = Lib.getWordsNum(content);

        Lib lib = new Lib();
        //将得到的数据传入指定文件
        List<Map.Entry<String,Integer>> wordsMap = lib.sortMap();
        FileOutputStream fileOutputStream=new FileOutputStream(outFilePath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("characters: "+charactersNum+"\nwords: "+wordsNum+"\nlines: "+linesNum);
        //对单词按出现次数输出
        String word = "";
        int i = 0;
        for (Map.Entry<String,Integer>map:wordsMap)
        {
            word += map.getKey()+": "+map.getValue()+"\n";
            i++;
            if(i == 10)
            {
                break;
            }
        }
        bufferedWriter.write("\n"+word);
        bufferedWriter.flush();
        long end = System.currentTimeMillis();
        
        //System.out.println("time costs: " + (end - start) + "ms");
    }
}