import javax.crypto.spec.PSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordCount{
    int lineCount=0;
    Map<String,Integer> wordCountMap;
    byte[] sourceBytes;
    String outputFileName;

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
        for(int i=0;i<source.length;i++){
            if(source[i]>='a'&&source[i]<='z'){//分析单词数
                wordStart++;
                currentWord.add(source[i]);
            }else if(source[i]>='0'&&source[i]<='9'){
                if(wordStart<4){
                    wordStart=0;
                }else{
                    currentWord.add(source[i]);
                }
            }else{
                if(source[i]=='\n'&&source[i-1]!='\n'&&source[i-2]!='\n'){//分析行数
                    lineCount++;
                }
                if(wordStart>=4){
                    currentWordToString=vectorByteToString(currentWord);
                    if(wordCountMap.containsKey(currentWordToString)){
                        wordCountMap.put(currentWordToString,wordCountMap.get(currentWordToString)+1);
                    }else{
                        wordCountMap.put(currentWordToString,1);
                    }
                }
                wordStart=0;
                currentWord.clear();
            }
        }
        if(source[source.length-1]!='\n'){
            lineCount++;
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
//        try{
//            System.setOut(new PrintStream(new BufferedOutputStream(
//                    new FileOutputStream(outputFileName)),true));
//        }catch(FileNotFoundException e){
//            e.printStackTrace();
//        }
        System.out.println("characters: "+sourceBytes.length);
        System.out.println("words: "+wordCountMap.size());
        System.out.println("lines: "+lineCount);
        List<Map.Entry<String, Integer>> list =sortMap();
        for(int i=0;i<list.size();i++){
            System.out.println("word"+(i+1)+": "+list.get(i));
        }
        System.setOut(new PrintStream(new BufferedOutputStream(
                new FileOutputStream(FileDescriptor.out)),true));
    }

    public List<Map.Entry<String, Integer>> sortMap(){
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


    public static void main(String[] args){
        WordCount wordCount=new WordCount();
        wordCount.wordCountMap=new HashMap<>();
        String inputFileName=args[0];
        wordCount.outputFileName=args[1];
        wordCount.sourceBytes=wordCount.readFileToBytes(inputFileName);
        wordCount.analyzeBytes(wordCount.sourceBytes);
        wordCount.output();
        /*for(int i=0;i<wordCount.sourceBytes.length;i++){
            if(wordCount.sourceBytes[i]=='\r'){
                System.out.println("\\r");
            }else if(wordCount.sourceBytes[i]=='\n'){
                System.out.println("\\n");
            }else{
                System.out.print((char)wordCount.sourceBytes[i]);
            }
        }*/
    }
}
import javax.crypto.spec.PSource;
        import java.io.*;
        import java.nio.charset.StandardCharsets;
        import java.util.*;

public class WordCount{
    int lineCount=0;
    Map<String,Integer> wordCountMap;
    byte[] sourceBytes;
    String outputFileName;

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
        for(int i=0;i<source.length;i++){
            if(source[i]>='a'&&source[i]<='z'){//分析单词数
                wordStart++;
                currentWord.add(source[i]);
            }else if(source[i]>='0'&&source[i]<='9'){
                if(wordStart<4){
                    wordStart=0;
                }else{
                    currentWord.add(source[i]);
                }
            }else{
                if(source[i]=='\n'&&source[i-1]!='\n'&&source[i-2]!='\n'){//分析行数
                    lineCount++;
                }
                if(wordStart>=4){
                    currentWordToString=vectorByteToString(currentWord);
                    if(wordCountMap.containsKey(currentWordToString)){
                        wordCountMap.put(currentWordToString,wordCountMap.get(currentWordToString)+1);
                    }else{
                        wordCountMap.put(currentWordToString,1);
                    }
                }
                wordStart=0;
                currentWord.clear();
            }
        }
        if(source[source.length-1]!='\n'){
            lineCount++;
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
//        try{
//            System.setOut(new PrintStream(new BufferedOutputStream(
//                    new FileOutputStream(outputFileName)),true));
//        }catch(FileNotFoundException e){
//            e.printStackTrace();
//        }
        System.out.println("characters: "+sourceBytes.length);
        System.out.println("words: "+wordCountMap.size());
        System.out.println("lines: "+lineCount);
        List<Map.Entry<String, Integer>> list =sortMap();
        for(int i=0;i<list.size();i++){
            System.out.println("word"+(i+1)+": "+list.get(i));
        }
        System.setOut(new PrintStream(new BufferedOutputStream(
                new FileOutputStream(FileDescriptor.out)),true));
    }

    public List<Map.Entry<String, Integer>> sortMap(){
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


    public static void main(String[] args){
        WordCount wordCount=new WordCount();
        wordCount.wordCountMap=new HashMap<>();
        String inputFileName=args[0];
        wordCount.outputFileName=args[1];
        wordCount.sourceBytes=wordCount.readFileToBytes(inputFileName);
        wordCount.analyzeBytes(wordCount.sourceBytes);
        wordCount.output();
        /*for(int i=0;i<wordCount.sourceBytes.length;i++){
            if(wordCount.sourceBytes[i]=='\r'){
                System.out.println("\\r");
            }else if(wordCount.sourceBytes[i]=='\n'){
                System.out.println("\\n");
            }else{
                System.out.print((char)wordCount.sourceBytes[i]);
            }
        }*/
    }
}
