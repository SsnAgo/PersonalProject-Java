import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {

    private String readFileName;
    private String writeFileName;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String fileContent;
    private int charNum;
    private int wordNum;
    private int lineNum;
    private Map<String,Integer> wordMap;

    public Lib(String readFileName, String writeFileName) {
        this.readFileName = readFileName;
        this.writeFileName = writeFileName;
        charNum = 0;
        wordNum = 0;
        lineNum = 0;
        wordMap = new HashMap<String, Integer>();
    }

   private String readFile() throws IOException {
        StringBuilder fileBuilder = new StringBuilder();
       try {
           bufferedReader = new BufferedReader(new FileReader(readFileName));
           int c;
           while ((c=bufferedReader.read())!=-1)
           {
               fileBuilder.append((char)c);
           }

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       return fileBuilder.toString();
   }

   public void writeFile() throws IOException {
       calculateCharNum();
       calculateWordNum();
       bufferedWriter = new BufferedWriter(new FileWriter(writeFileName));
       bufferedWriter.write("characters: "+charNum+"\n");
       bufferedWriter.write("words: "+wordNum+"\n");
       bufferedWriter.write("lines: "+lineNum+"\n");
       int i = 1;
       for (Map.Entry<String,Integer> entry:wordMap.entrySet())
       {
           if (i>10) {
               break;
           }
           bufferedWriter.write(entry.getKey()+": "+entry.getValue()+"\n");
           i++;
       }
       bufferedWriter.flush();
   }

   private void calculateCharNum() throws IOException {
       fileContent = readFile();
       charNum = fileContent.length();
   }

   private void calculateWordNum(){
        String[] contents = fileContent.split("[^(a-zA-Z0-9)]");
        for (String content:contents)
        {
           if (isWord(content)){
               wordNum++;
               String word = content.toLowerCase();
               Integer count = wordMap.get(word);
               if (count==null){
                   count = 1;
               } else {
                   count++;
               }
               wordMap.put(word,count);
           }
        }
        //对单词进行排序
       List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(wordMap.entrySet());
       Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
           @Override
           public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
               return o2.getValue().compareTo(o1.getValue());
           }
       });
   }

   private boolean isWord(String str)
   {
       if (str.matches("[a-zA-Z]{4}[a-zA-Z0-9]*")){
           return true;
       } else{
           return false;
       }
   }
}

