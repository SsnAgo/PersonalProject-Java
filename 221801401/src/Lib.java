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
    Lib lib = new Lib();
    //单词正则表达式
    private static String WORDS_RE = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
    //分隔符正则表达式
    private static String BREAK_RE = "[^a-zA-Z0-9]";
    //非空行正则表达式
    private static String LINE_RE = "(^|\n)\\s*\\S+";
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
        int temp;
        //创建输入流
        BufferedReader br = null;
        StringBuilder builder = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            builder = new StringBuilder();
            //按字符读入文件数据
            while((temp = br.read()) != -1) {
                builder.append((char)temp);
            }
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
        int count = 0;
        Matcher matcher = Pattern.compile(LINE_RE).matcher(str);
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
           }else continue;
       }
       return count;
    }
    
    /*
     * 统计文件的单词
     * @param str
     * @return count
     * */
    public static int getWordsCount(String str) {
        int count = 0;
        //用正则表达式匹配分隔符分割字符串
        String[] strs = str.split(BREAK_RE);
        //遍历字符串数组，匹配符合正则表达式的单词
        for(int i = 0; i < strs.length; i++) {
            if(strs[i].matches(WORDS_RE)) {
                //忽略大小写，添加单词到Map中
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
                //按照字典序以及value的值排序
                if(m1.getValue().equals(m2.getValue())) {
                    return m1.getKey().compareTo(m2.getKey());
                }else return m2.getValue()-m1.getValue();
                }
            });	
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
                i++;
                }else break;
            }
        
        //得到输出流
        FileOutputStream fos = null; 
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        
        try {
            fos = new FileOutputStream(filePath);
            writer = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(str);
            bw.flush();
            }catch(IOException e) {
                e.printStackTrace();
                }finally {
                    try {
                        fos.close();
                        writer.close();
                        bw.close();
                        }catch(IOException e) {
                            e.printStackTrace();
                            }
                    }
        }
}
