import java.io.*;
import java.util.List;
import java.util.Map;

public class WordCount {



    public static void main(String[] args) throws IOException {

        Lib lib=new Lib();
        String inputPath= args[0];
        String outputPath=args[1];


        String str=lib.getCharacters(inputPath);
        int strNum=lib.getCharactersNum(str);
        int wordNum=lib.getWordsNum(str);
        int lineNum=lib.getLinesNum(inputPath);
        List<Map.Entry<String,Integer>> wordsList=lib.sortMap();

        FileOutputStream fileOutputStream=new FileOutputStream(outputPath);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("characters:"+strNum+"\nwords:"+wordNum+"\nlines:"+lineNum);

        String words="";
        int i=0;
        for(Map.Entry<String,Integer>map:wordsList){
            words+=map.getKey()+":"+map.getValue()+"\n";
            i++;
            if(i==10){
                break;
            }
        }
        bufferedWriter.write("\n"+words);

        bufferedWriter.flush();



    }
}
