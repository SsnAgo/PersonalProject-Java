import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class CountCore {
    public static int characterCount(File inputFile){
        BufferedReader bufferedReader= null;
        int characterCount=0;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!(bufferedReader.read()!=-1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            characterCount++;
        }
        return characterCount;
    }

    public static void toLowerCase(File inputFile){
        BufferedReader bufferedReader= null;
        StringBuilder stringBuilder=new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            String str;
            while((str=bufferedReader.readLine())!=null){
                stringBuilder.append(str.toLowerCase());
                stringBuilder.append("\r\n");
            }
            bufferedReader.close();
            System.out.println(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static boolean isWord(String string){
        String wordRegex="[a-z]{4}[a-z0-9]*";
        return Pattern.matches(wordRegex,string);
    }
    public static Map<String,Integer> wordCount(File inputFile){
        Map<String,Integer> result=new HashMap<>();
        BufferedReader bufferedReader=null;
        StringBuilder stringBuilder=new StringBuilder();
        String splitRegex="[^a-zA-Z0-9]";
        try {
            String str;
            bufferedReader=new BufferedReader(new FileReader(inputFile));
            while((str=bufferedReader.readLine())!=null){
                str=str.toLowerCase();
                //将分隔符全部替换成" "
                str=str.replaceAll(splitRegex," ");
                StringTokenizer stringTokenizer=new StringTokenizer(str);
                while(stringTokenizer.hasMoreTokens()){
                    String substring=stringTokenizer.nextToken();
                    if(isWord(substring)&&result.containsKey(substring)){
                        int frequency=result.get(substring);
                        result.put(substring,frequency+1);
                    }else if(isWord(substring)&&(!result.containsKey(substring))){
                        result.put(substring,1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
