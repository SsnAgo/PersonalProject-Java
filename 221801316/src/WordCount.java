
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class WordCount{

    public static void main(String[] args) {
        File inputFile=new File(args[0]);
        File outputFile=new File(args[1]);
        StringBuilder content=new StringBuilder();
        int characterCount= CountCore.characterCount(inputFile);
        content.append("characters:"+characterCount+"\n");
        int words=CountCore.wordCount(inputFile);
        content.append("words:"+words+"\n");
        int lines=CountCore.lineCount(inputFile);
        content.append("lines:"+lines+"\n");
        Map<String,Integer> map=CountCore.wordStore(inputFile);
        List<Map.Entry<String,Integer>> list=CountCore.sortByFrequency(map);
        for(int i=0;i<10&&i<list.size();i++){
            content.append(list.get(i).getKey()+":"+list.get(i).getValue()+"\n");
        }
        FileIO.writeToFile(outputFile,content.toString());
    }

}