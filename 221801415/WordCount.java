package wordcount_java;

import java.io.*;
import java.util.List;
import java.util.Map;

public class WordCount 
{

    public static void main(String[] args) throws IOException 
    {

        Lib lib=new Lib();
        String inputPath= args[0];
        String outputPath=args[1];


        String Char=lib.getCharacters(inputPath);
        int Char_Num=lib.getCharactersNumber(Char);
        int Word_Num=lib.getWordsNumber(Char);
        int Line_Num=lib.getLinesNumber(inputPath);
        List<Map.Entry<String,Integer>> wordsList=lib.SortMap();

        FileOutputStream fileOutputStream=new FileOutputStream(outputPath);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("characters:"+Char_Num+"\nwords:"+Word_Num+"\nlines:"+Line_Num);

        String words="";
        int i=0;
        for(Map.Entry<String,Integer>map:wordsList)
        {
            words+=map.getKey()+":"+map.getValue()+"\n";
            i++;
            if(i==10)
            {
                break;
            }
        }
        bufferedWriter.write("\n"+words);

        bufferedWriter.flush();

        System.out.print(Char_Num);


    }
}
