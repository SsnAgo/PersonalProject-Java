import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class CountCore {
    /**
     * 字符统计
     * @param bufferedReader
     * @return characterCount 字符个数
     */
    public static int characterCount(BufferedReader bufferedReader){
        int characterCount=0;
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

    /**
     * 判断是否是一个合法的单词
     * @param string
     * @return 布尔值
     */
    public static boolean isWord(String string){
        String wordRegex="[a-z]{4}[a-z0-9]*";
        return Pattern.matches(wordRegex,string);
    }

    /**
     * 将合法的单词存入map
     * @param bufferedReader
     * @return 单词与数量的键值对map
     */
    public static Map<String,Integer> wordStore(BufferedReader bufferedReader){
        Map<String,Integer> result=new HashMap<>();
        String splitRegex="[^a-zA-Z0-9]";
        try {
            String str;
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
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 单词总数统计
     * @param bufferedReader
     * @return 单词总的个数
     */
    public static int wordCount(BufferedReader bufferedReader){
       int words=0;
        Map<String,Integer> map=wordStore(bufferedReader);
       for(Map.Entry<String,Integer> entry:map.entrySet()){
            words+=entry.getValue();
        }
       return words;
    }

    /**
     * 行数统计
     * @param bufferedReader
     * @return 非空白行的数量
     */
    public static int lineCount(BufferedReader bufferedReader){
        //正则表达式,匹配至少一个非空白字符
        String regex="\\S+";
        int lines=0;
        try {
            String str;
            while((str=bufferedReader.readLine())!=null){
                if(Pattern.matches(regex,str))lines++;
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 对map中的键值对进行自定义排序
     * @param map
     * @return 有序的键值对实体的list
     */
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
