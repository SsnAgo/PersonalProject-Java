import java.io.*;
import java.util.HashMap;
import java.util.Map;
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
}
