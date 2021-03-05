import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Lib
{
    /**
     * 读取文件，生成字符串并返回
     *
     * @ param filePath
     * @ return
     * */
    public static String readFile(String filePath) {
        int temp;
        BufferedReader br = null;
        StringBuilder builder = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            builder = new StringBuilder();

            while((temp = br.read()) != -1){
                builder.append((char)temp);
            }
            /* 使用readline（）读取时，返回的字符串缺少“\n”
            String temp;
            while((temp = br.readLine()) != null){
                builder.append(temp);
            }
            */
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }

    /**
     * 遍历字符串，判断其是否为Ascii码并统计其数量
     *
     * @ param chStr
     * @ return characters' number
     * */
    public static int getCharactersCount(String chStr) {
        int charCount = 0;
        char[] charArray = chStr.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            if(charArray[i] <= 127)
                charCount++;
        }

        return charCount;
    }

    /**
     * 利用正则表达式，判断有效行数
     *
     * @ param chStr
     * @ return valid lines
     * */
    public static int getLines(String chStr){
        int lines = 0;
        String regex = "(\n|^)\\s*\\S+";

        //使用正则表达式匹配有效的字符行
        Pattern charPattern = Pattern.compile(regex);
        Matcher matcher = charPattern.matcher(chStr);

        while(matcher.find()){
            lines++;
        }

        return lines;
    }

    /**
     * 利用正则表达式，判断有效单词数
     *
     * @ param chStr
     * @ return valid words‘ number
     * */
    public static int getWordNum(String chStr){
        int wordNum = 0;
        String regexWords = "([^A-Za-z0-9]+|^)([a-zA-Z]{4,}[a-zA-Z0-9]*)";

        Pattern wordPattern = Pattern.compile(regexWords);
        Matcher wordMatcher = wordPattern.matcher(chStr);

        while(wordMatcher.find()){
            wordNum++;
        }

        return wordNum;
    }

    /**
     * 利用正则表达式，判断有效单词并记录其出现次数,将该单词与其出现次数存入Map集合中
     *
     * @ param chStr, wordMap
     * @ return valid words‘ number
     * */
    public static void creatWordMap(String chStr, Map<String, Integer> wordMap){
        //匹配分隔符分离单词，并用String[]保存
        String[] words = chStr.split("\\s");
        /**
         * 位于第一位的单词会多出一个空白字符，假设合法单词为apple，则其为[,a,p,p,l,e]
         * */
        words[0] = words[0].substring(1);
        String regexWord = "([^A-Za-z0-9]+|^)([a-zA-Z]{4,}[a-zA-Z0-9]*)";

        //验证单词有效性，有效的单词存入集合Map<String, Integer>中
        for(int i = 0; i < words.length; i++) {
            if(words[i].matches(regexWord)) {
                String temp = words[i].toLowerCase();

                //忽略单词大小写，判断其是否已经存在，若存在，则其value值加一
                if(!wordMap.containsKey(temp)) {
                    wordMap.put(temp, 1);
                }
                //若不存在，则存入wordMap
                else {
                    int value = 1 + wordMap.get(temp);

                    wordMap.put(temp, value);
                }
            }
        }

    }

    /**
     * 使用比较器对单词频率进行排序
     *
     * @ param wordMap
     * @ return list
     * */
    public static List  sortWordMap(Map<String, Integer> wordMap) {

        //将wordMap转换为List，方便排序
        List<Map.Entry<String, Integer>> list =new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //若value相同，则对key值进行字典排序
                if(o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                else {
                    return o2.getValue()-o1.getValue();
                }
            }
        });

        //当单词数大于10时，返回前十个单词
        if(list.size() > 10) {
            return list.subList(0,10);
        }
        //否则返回整个list
        else {
            return list;
        }
    }

    /**
     * 将处理后的信息进行统合，得到字符串
     *
     * @ param words,lines,characters,
     * @ return list
     * */
    public static String outMessage(int words, int lines, int characters, List<Map.Entry<String, Integer>> topTenWords) {
        //拼接信息
        String outMessage = "characters:"+characters+"\nwords:"+words+"\nlines:"+lines+"\n";


        for(Map.Entry<String,Integer> map : topTenWords) {
            outMessage += map.getKey()+":"+map.getValue()+"\n";
        }
        return outMessage;
    }
    /**
     * 将单词数，有效行数以及词频信息写入文件
     *
     * @ param wordMap
     * @ return list
     * */

    public static void writeFile(String outMessage, String filePath) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter streamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            streamWriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
            bufferedWriter = new BufferedWriter(streamWriter);

            bufferedWriter.write(outMessage);
            bufferedWriter.flush();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileOutputStream.close();
                streamWriter.close();
                bufferedWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
