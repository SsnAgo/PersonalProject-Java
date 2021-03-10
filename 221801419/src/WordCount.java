import java.io.*;
import java.util.List;
import java.util.Map;

public class WordCount {



    public static void main(String[] args) throws IOException {



        Lib lib=new Lib();
        String inputPath= args[0];
        String outputPath=args[1];


        /*String inputPath="C:\\Users\\穗积\\Desktop\\软工实践\\count\\src\\test3.txt";
        String outputPath="C:\\Users\\穗积\\Desktop\\软工实践\\count\\src\\out.txt";*/

        String str=lib.getCharacters(inputPath);
        int strNum=lib.getCharactersNum(str);
        int wordNum=lib.getWordsNum(str);
        int lineNum=lib.getLinesNum(inputPath);
        List<Map.Entry<String,Integer>> wordsMap=lib.sortMap();

        FileOutputStream fileOutputStream=new FileOutputStream(outputPath);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("characters: "+strNum+"\nwords: "+wordNum+"\nlines: "+lineNum);

        String words="";
        int i=0;
        for(Map.Entry<String,Integer>map:wordsMap){
            words+=map.getKey()+": "+map.getValue()+"\n";
            i++;
            if(i==10){
                break;
            }
        }
        bufferedWriter.write("\n"+words);

        bufferedWriter.flush();



    }
}
