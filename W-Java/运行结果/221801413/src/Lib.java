import java.io.*;
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
    //有效行数正则表达式
    private static String LINE_REGEX = "(\n|^)\\s*\\S+";
    //有效单词数正则表达式
    private static String WORD_REGEX = "([^A-Za-z0-9]+|^)([a-zA-Z]{4,}[a-zA-Z0-9]*)";
    //存放单词与前出现频率的Map集合
    private static Map<String, Integer> wordMap = new HashMap<String, Integer>();

    /**
     * 读取文件，生成字符串并返回
     *
     * @ param filePath
     * @ return TextContent
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
    public static long getCharactersNum(String chStr) {
        long charCount = 0;
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
    public static int getLinesNum(String chStr){
        int lines = 0;

        //使用正则表达式匹配有效的字符行
        Pattern charPattern = Pattern.compile(LINE_REGEX);
        Matcher charmatcher = charPattern.matcher(chStr);

        while(charmatcher.find()){
            lines++;
        }

        return lines;
    }

    /**
     * 利用正则表达式，判断有效单词数,记录其出现次数,将该单词与其出现次数存入Map集合中
     *
     * @ param chStr
     * @ return valid words‘ number
     * */
    public static long getWordNum(String chStr){
        long wordNum = 0;

        //使用正则表达式匹配
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Matcher wordMatcher = wordPattern.matcher(chStr);

        while(wordMatcher.find()){
            String temp = wordMatcher.group(2).trim();
            wordNum++;
            //若单词不存在，则加入wordMap
            if(!wordMap.containsKey(temp)) {
                wordMap.put(temp, 1);
            }
            //若存在，则将其value值加1
            else {
                int value = 1 + wordMap.get(temp);

                wordMap.put(temp, value);
            }
        }

        return wordNum;
    }

    /**
     * 使用比较器对单词频率进行排序
     *
     * @ param wordMap
     * @ return list
     * */
    public static List  sortWordMap() {

        //将wordMap转换为List，方便排序
        List<Map.Entry<String, Integer>> list =new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //若value相同，则对key值进行字典排序
                if(o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                //否则按频率排序
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
    public static String outMessage(long words, int lines, long characters, List<Map.Entry<String, Integer>> topTenWords) {
        //拼接信息
        String outMessage = "characters: "+characters+"\nwords: "+words+"\nlines: "+lines+"\n";


        for(Map.Entry<String,Integer> map : topTenWords) {
            outMessage += map.getKey()+": "+map.getValue()+"\n";
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
        //创建输出流
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter streamWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileOutputStream = new FileOutputStream(filePath);
            streamWriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
            bufferedWriter = new BufferedWriter(streamWriter);

            //将得到的数据写入对应路径的文件
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
                //关闭输出流
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
