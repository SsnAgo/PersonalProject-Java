import java.io.*;
import java.util.*;

class Lib{
    int charCount, wordCount, lineCount;
    InputStreamReader in;
    OutputStreamWriter out;
    BufferedReader br;
    HashMap<String,Integer> wordMap = new HashMap<String, Integer>();
    List<Map.Entry<String,Integer>>list;
    Lib(){
        charCount = 0;
        wordCount = 0;
        lineCount = 0;
    }
    int getCountChar(String inputFile) {
        try {
            in = new InputStreamReader(new FileInputStream(inputFile));
            br = new BufferedReader(in);
            int x;
            while ((x = br.read()) != -1) {
                charCount++;
            }
            br.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charCount;
    }
    int getCountLine(String inputFile){
        try {
            in = new InputStreamReader(new FileInputStream(inputFile));
            br = new BufferedReader(in);
            int x;
            boolean isBlankLine = true;
            while ((x = br.read()) != -1) {
                charCount++;
                if (x != 13 && x != 10) isBlankLine = false;
                else if (x == 10 && !isBlankLine) {
                    lineCount++;
                    isBlankLine = true;
                }
            }
            br.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineCount;
    }
   int getCountWord(String inputFile){
        try{
            in = new InputStreamReader(new FileInputStream(inputFile));
            br = new BufferedReader(in);
            int x,letterCount=0;
            String word="";
            while((x = br.read())!= -1) {
                if(((x<='Z'&&x>='A')||(x<='z'&&x>='a'))&&letterCount!=-1){
                    word += (char)x;
                    letterCount++;;
                }
                else if (((x<='Z'&&x>='A')||(x<='z'&&x>='a'))&&letterCount==-1){
                    letterCount=-1;
                }
                else if(x<='9'&&x>='0'&&letterCount<4){
                    letterCount=-1;
                    word = "";
                }
                else if (x<='9'&&x>='0'&&letterCount>=4){
                    word += (char)x;
                }
                else{
                    if(letterCount>=4){
                        wordCount++;
                        addWordMap(word);
                    }
                    letterCount = 0;
                    word = "";
                }
            }
            br.close();
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return wordCount;
    }
    void addWordMap(String word){
        word = word.toLowerCase();
        if(wordMap.containsKey(word)){
            wordMap.put(word, wordMap.get(word)+1);
        }
        else {
            wordMap.put(word,1);
        }
    }
    void getWordTopTen(){
        list = new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue().equals(o2.getValue())) {return o1.getKey().compareTo(o2.getKey());}
                else {return o2.getValue().compareTo(o1.getValue());}
            }
        });
        for(int i = 0;i<(list.size()<10 ? list.size():10);i++){
            System.out.println(list.get(i).getKey()+": "+list.get(i).getValue());
        }
    }

    void writeFile(String outputFile){
        try {
            out = new OutputStreamWriter(new FileOutputStream(outputFile));
            String str = "characters: "+charCount+"\n"
                    + "words: "+wordCount+"\n"
                    +"lines: "+lineCount+"\n";
            for(int i = 0;i<(list.size()<10 ? list.size():10);i++){
                str+=(list.get(i).getKey()+": "+list.get(i).getValue()+"\n");
            }
            out.write(str);
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}