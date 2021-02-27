import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author 张海浪
 * 用于查询的工具类
 *
 * */
public class Lib {
    //单词正则表达式
    private static String WORDS_RE = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
    //分隔符正则表达式
    private static String BREAK_RE = "[^a-zA-Z0-9]";
    //获取当前工程的路径
    public static String DIR = System.getProperty("user.dir");
    //Map表用于存放单词以及相对应的个数
    private static Map<String, Integer> wordsMap = new HashMap<String, Integer>();
    
    
    /*
     * 从文件中读取数据
     * @param filePath
     * @return 字符串
     * */
    public static String readFromFile(String filePath) {
        String str = "";
        BufferedReader br = null;
        StringBuilder builder = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            builder = new StringBuilder();
            while((str = br.readLine()) != null) {
                builder.append(str + "\n");
            }
            //删除最后一个'\n'
            builder.deleteCharAt(builder.length()-1);
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
    
    /*
     * 使用正则表达式统计字符串行数
     * @param str
     * @return count
     * */
    public static int getLineCount(String str) {
        int count = 1;
        Matcher matcher = Pattern.compile("\r\n|\r|\n").matcher(str);
        while(matcher.find()) {
            count++;
        }
        return count;
    }
    
    
    /*
     * 统计字符串的字符数
     * @ param str
     * @ return count
     * */
    public static int getCharactersCount(String str) {
       int count = 0;
       char[] ch = str.toCharArray();
       for(int i = 0; i < ch.length; i++) {
           if(ch[i] >= 0 && ch[i] <= 127) {
               count++;
           }
       }
       return count;
    }
    
    /*
     * 统计文件的单词
     * @param str
     * @return count
     * */
    public static int getWordsCount(String str) {
        //单词数的统计量
        int count = 0;
        String[] strs = str.split(BREAK_RE);
        for(int i = 0; i < strs.length; i++) {
            if(strs[i].matches(WORDS_RE)) {
                String temp = strs[i].toLowerCase();
                if(wordsMap.containsKey(temp)) {
                    int num = wordsMap.get(temp);
                    wordsMap.put(temp, 1 + num);
                    }
                else {
                    wordsMap.put(temp, 1);
                    }
                count++;
                }
            }
        return count;
    }
    
    /*
     * 用比较器实现单词排序
     * @param 无参
     * @return 无返回值
     * */
    public static List<Map.Entry<String, Integer>> sortHashmap() {
        //将words.entrySet()转换为list
        List<Map.Entry<String, Integer>> list;
        list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());
        //通过比较器实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Entry<String, Integer> m1, Entry<String, Integer> m2) {
                if(m1.getValue().equals(m2.getValue())) {
                    return m1.getKey().compareTo(m2.getKey());
                }else return m2.getValue()-m1.getValue();
                }
            });	
        int i = 0;
        for(Map.Entry<String, Integer> map : list) {
            if(i < 10) {
                System.out.println(map.getKey() + ":" + map.getValue());
                }
            i++;
            }
        return list;
    }
    
    /*
     * 将数据输出到指定文件中
     * @param characters words lines filePath
     * @return 无返回值
     * */
    public static void writeToFile(int characters, int words, int lines, String filePath) {
        //获取将要输出的字符串信息
        String str = "characters: " + characters + "\nwords: " + words + "\nlines: " + lines +"\n";
        List<Map.Entry<String, Integer>> list = sortHashmap();
        int i = 0;
        for(Map.Entry<String, Integer> map : list) {
            if(i < 10) {
                str += map.getKey() + ": " + map.getValue() + "\n";
                }
            i++;
            }
        
        //得到输出流
        FileOutputStream fos = null; 
        OutputStreamWriter writer = null;
        
        try {
            fos = new FileOutputStream(filePath);
            writer = new OutputStreamWriter(fos, "UTF-8");
            writer.write(str);
            writer.flush();
            }catch(IOException e) {
                e.printStackTrace();
                }finally {
                    try {
                        fos.close();
                        writer.close();
                        }catch(IOException e) {
                            e.printStackTrace();
                            }
                    }
        }
}
