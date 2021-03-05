import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Lib{

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
            System.out.println("Can not open source file!");
            return null;
        }
    }

    public void analyzeBytes(WordCount w){
        int wordStart=0;
        Vector<Byte> currentWord=new Vector<>();
        String currentWordToString;
        for(int i=0;i<w.sourceBytes.length;i++){
            if(w.sourceBytes[i]>='a'&&w.sourceBytes[i]<='z'){//分析单词数
                wordStart++;
                currentWord.add(w.sourceBytes[i]);
            }else if(w.sourceBytes[i]>='0'&&w.sourceBytes[i]<='9'){
                if(wordStart<4){
                    wordStart=0;
                }else{
                    currentWord.add(w.sourceBytes[i]);
                }
            }else{
                if(i>1&&w.sourceBytes[i]=='\n'&&w.sourceBytes[i-1]!='\n'&&w.sourceBytes[i-2]!='\n'){//分析行数
                    w.lineCount++;
                }
                if(wordStart>=4){
                    currentWordToString=vectorByteToString(currentWord);
                    if(w.wordCountMap.containsKey(currentWordToString)){
                        w.wordCountMap.put(currentWordToString,w.wordCountMap.get(currentWordToString)+1);
                    }else{
                        w.wordCountMap.put(currentWordToString,1);
                    }
                    w.wordCount++;
                }
                wordStart=0;
                currentWord.clear();
            }
        }
        if(w.sourceBytes[w.sourceBytes.length-1]!='\n'){
            w.lineCount++;
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

    public void output(WordCount wordCount){
//        try{
//            System.setOut(new PrintStream(new BufferedOutputStream(
//                    new FileOutputStream(outputFileName)),true));
//        }catch(FileNotFoundException e){
//            e.printStackTrace();
//        }
        System.out.println("characters: "+wordCount.sourceBytes.length);
        System.out.println("words: "+wordCount.wordCount);
        System.out.println("lines: "+wordCount.lineCount);
        List<Map.Entry<String, Integer>> list =sortMap(wordCount.wordCountMap);
        for(int i=0;i<10;i++){
            System.out.println(list.get(i).getKey()+": "+list.get(i).getValue());
        }
        System.setOut(new PrintStream(new BufferedOutputStream(
                new FileOutputStream(FileDescriptor.out)),true));
    }

    public List<Map.Entry<String, Integer>> sortMap(Map<String,Integer> wordCountMap){
        List<Map.Entry<String, Integer>> list =new ArrayList<>(wordCountMap.entrySet()); //转换为list
        list.sort((o1,o2)->{
            if(o2.getValue().equals(o1.getValue())){
                return o1.getKey().compareTo(o2.getKey());
            }else{
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return list;
    }

}
