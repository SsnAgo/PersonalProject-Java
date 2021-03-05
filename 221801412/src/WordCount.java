import java.io.*;
import java.util.*;

public class WordCount {

    public static void main(String[] args) {
	    File input;
	    File output;
	    if(args.length<2){
	        input=new File("src/input.txt");
	        output=new File("sec/output.txt");
        }
        else {
            input = new File(args[0]);
            output = new File(args[1]);
        }
        CountData cd = new CountData();
        int countChar = cd.getCountChar();
        int countLine = cd.getCountLine();
        int countWord = cd.getCountWord();
        List<Map.Entry<String, Integer>> getWordFrequency = cd.getGetWordFrequency();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));
            bw.write("characters: " + countChar + "\n");
            bw.write("words: " + countWord + "\n");
            bw.write("lines: " + countLine + "\n");
            if (getWordFrequency.size() < 10) {
                for (int i = 0; i < getWordFrequency.size(); i++) {
                    bw.write(getWordFrequency.get(i).getKey() + ": " + getWordFrequency.get(i).getValue());
                    bw.write("\n");
                }
            }
            else {
                for (int i = 0; i < 10; i++) {
                    bw.write(getWordFrequency.get(i).getKey() + ":" + getWordFrequency.get(i).getValue());
                    bw.write("\n");
                }
            }
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        }
    }
}
class CountData{
    int countWord;
    int countLine;
    int countChar;
    HashMap<String, Integer> getWordFrequency;

    CountData() {
        countWord = 0;
        countLine = 0;
        countChar = 0;
        getWordFrequency = new HashMap<String, Integer>();
    }

    void setCountWord(int n) {
        countWord += n;
    }

    void setCountLine() {
        countLine++;
    }

    void setCountLine(int n) {
        countLine += n;
    }

    void setCountChar(int n) {
        countChar += n;
    }

    void setGetWordFrequency(HashMap<String, Integer> n) {
        for (String s : n.keySet()) {
            if (getWordFrequency.containsKey(s)) {
                getWordFrequency.put(s, getWordFrequency.get(s) + n.get(s));
            }
            else {
                getWordFrequency.put(s, 1);
            }
        }
    }

    /*Used to return the value of countWord;*/
    int getCountWord() {
        return countWord;
    }

    /*Used to return the value of countLine;*/
    int getCountLine() {
        return countLine;
    }

    /*Used to return the value of countChar;*/
    int getCountChar() {
        return countChar;
    }
    List<Map.Entry<String, Integer>> getGetWordFrequency() {
        List<Map.Entry<String, Integer>> list = null;
        list = new ArrayList<Map.Entry<String, Integer>>(getWordFrequency.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() != o2.getValue()) {
                    return o2.getValue().compareTo(o1.getValue());
                }
                else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });
        return list;
    }
}
