import java.io.*;
import java.util.*;
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
    public static boolean isWord(String string){
        String wordRegex="[a-z]{4}[a-z0-9]*";
        return Pattern.matches(wordRegex,string);
    }
    public static Map<String,Integer> wordStore(File inputFile){
        Map<String,Integer> result=new HashMap<>();
        BufferedReader bufferedReader=null;
        String splitRegex="[^a-zA-Z0-9]";
        try {
            String str;
            bufferedReader=new BufferedReader(new FileReader(inputFile));
            while((str=bufferedReader.readLine())!=null){
                str=str.toLowerCase().replaceAll(splitRegex," ");
                //将分隔符全部替换成" "
                StringTokenizer stringTokenizer=new StringTokenizer(str);
                while(stringTokenizer.hasMoreTokens()) {
                    String substring = stringTokenizer.nextToken();
                    if (isWord(substring) && result.containsKey(substring)) {
                        int frequency = result.get(substring);
                        result.put(substring, frequency + 1);
                    } else if (isWord(substring) && (!result.containsKey(substring))) {
                        result.put(substring, 1);
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
    public static int wordCount(File inputFile){
       int words=0;
        Map<String,Integer> map=wordStore(inputFile);
       for(Map.Entry<String,Integer> entry:map.entrySet()){
            words+=entry.getValue();
        }
       return words;
    }

    public static int lineCount(File inputFile){
        BufferedReader bufferedReader=null;
        //正则表达式,匹配至少一个非空白字符
        String regex="\\S+";
        int lines=0;
        try {
            bufferedReader=new BufferedReader(new FileReader(inputFile));
            String str;
            while((str=bufferedReader.readLine())!=null){
                if(Pattern.matches(regex,str))lines++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public static List<Map.Entry<String,Integer>> sortByFrequency(Map<String,Integer> map){
        List<Map.Entry<String,Integer>> list=new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return  o1.getValue().equals(o2.getValue())?
                        o1.getKey().compareTo(o2.getKey()):o2.getValue()-o1.getValue();
            }
        });
        return list;
    }
}
