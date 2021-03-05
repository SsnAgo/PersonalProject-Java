import java.io.*;
import java.util.List;
import java.util.Map;

public class WordCount
{
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        String outFilePath = args[1];
        String content = Lib.readFile(filePath);
        int charactersNum = Lib.getCharacterNum(content);
        int linesNum = Lib.getLinesNum(content);
        int a = Lib.getWordsNum(content);
        Lib lib = new Lib();
        List list = lib.sortMap();
        System.out.println("charactersNum: "+charactersNum+"\nlinesNum: "+linesNum+"\n");
        //System.out.println(list);
        //输出
        List<Map.Entry<String,Integer>> wordsMap=lib.sortMap();
        FileOutputStream fileOutputStream=new FileOutputStream(outFilePath);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("characters:"+charactersNum+"\nlines:"+linesNum);
        String word="";
        int i = 0;
        for (Map.Entry<String,Integer>map:wordsMap)
        {
            word += map.getKey()+":"+map.getValue()+"\n";
            i++;
            if(i == 10)
            {
                break;
            }
        }
        bufferedWriter.write("\n"+word);
        bufferedWriter.flush();
    }
}