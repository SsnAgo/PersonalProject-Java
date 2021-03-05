
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class WordCount{
    /**
     * 程序的入口
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File inputFile=new File(args[0]);
        File outputFile=new File(args[1]);
        BufferedReader bufferedReader=FileIO.readFromFile(inputFile);
        bufferedReader.mark((int)inputFile.length()+1);
        StringBuilder content=new StringBuilder();
        int characterCount= CountCore.characterCount(bufferedReader);
        content.append("characters: "+characterCount+"\n");
        bufferedReader.reset();
        int words=CountCore.wordCount(bufferedReader);
        content.append("words: "+words+"\n");
        bufferedReader.reset();
        int lines=CountCore.lineCount(bufferedReader);
        content.append("lines: "+lines+"\n");
        bufferedReader.reset();
        Map<String,Integer> map=CountCore.wordStore(bufferedReader);
        List<Map.Entry<String,Integer>> list=CountCore.sortByFrequency(map);
        for(int i=0;i<10&&i<list.size();i++){
            content.append(list.get(i).getKey()+": "+list.get(i).getValue()+"\n");
        }
        FileIO.writeToFile(outputFile,content.toString());
    }

}