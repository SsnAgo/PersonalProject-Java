import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordCount{
    int lineCount=0;
    Map<String,Integer> wordCountMap;


    public byte[] readFileToBytes(String fileName){
        try {
            File f = new File(fileName);
            int length = (int) f.length();
            byte[] data = new byte[length];
            new FileInputStream(f).read(data);
            for(int i=0;i<data.length;i++){
                if(data[i]>='A' && data[i]<='Z'){
                    data[i]+=32;
                }
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void analyzeBytes(byte[] source){
        int wordStart=0;
        Vector<Byte> currentWord=new Vector<>();
        String currentWordToString;
        for(byte b:source){
            if(b>='a'&&b<='z'){//分析单词数
                wordStart++;
                currentWord.add(b);
            }else if(b>='0'&&b<='9'){
                if(wordStart<4){
                    wordStart=0;
                }else{
                    currentWord.add(b);
                }
            }else{
                currentWordToString=vectorByteToString(currentWord);
                if(b=='\n'){//分析行数
                    lineCount++;
                }
                wordStart=0;
                if(wordCountMap.containsKey(currentWordToString)){
                    wordCountMap.put(currentWordToString,wordCountMap.get(currentWordToString)+1);
                }else{
                    wordCountMap.put(currentWordToString,1);
                }
                currentWord.clear();
            }
        }

    }

    public String bytesToString(byte[] bs) {
        return new String(bs,StandardCharsets.UTF_8);
    }

    public String vectorByteToString(Vector<Byte> vb) {
        byte[] bytes=new byte[vb.size()];
        for(int i=0;i<vb.size();i++){
            bytes[i]=vb.get(i);
        }
        return bytesToString(bytes);
    }

    public void output(){

    }


    public static void main(String[] args){
        WordCount wordCount=new WordCount();
        wordCount.wordCountMap=new HashMap<>();
        String fileName=args[0];
        byte[] result=wordCount.readFileToBytes(fileName);
        wordCount.output();
        System.out.println(wordCount.bytesToString(result));
    }
}
