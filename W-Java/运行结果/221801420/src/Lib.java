import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lib {
    static Map<String,Integer> wordsMap = new TreeMap<>();//单词和频率的映射表
    
    /*
     * 功能：返回utf-8编码的读指针
     * 输入：File文件指针
     * 输出：BufferedReader指针
     */
    static BufferedReader getReader(File file) throws IOException {
        InputStreamReader read = null;
        try {
            read = new InputStreamReader(new FileInputStream(file),"UTF-8");
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
          throw e;
        }
        return new BufferedReader(read);
    }
    
    /*
     * 功能：将字符串写入指定文件
     * 输入：str为字符串，filename为文件名
     * 输出：无
     */
    static void writeFile(String str,String fileName) {
        File file = new File(fileName);
        try {
            BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            buffWriter.write(str);
            buffWriter.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * 功能：统计文件字符数
     * 输入：File文件指针
     * 输出：文件中含有的字符数
     */
    static int statisticsCharacters(File file) throws FileNotFoundException {
        int characterNum = 0;
        int characterAscill = 0;
        try {
            BufferedReader in = getReader(file);
            while (true) {
                characterAscill=in.read();
                if (characterAscill >= 0 && characterAscill <= 127) {
                    characterNum ++;
                }
                if (characterAscill == -1) break;
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characterNum;
    }
	 
    /*
     * 功能：通过正则表达式对单词进行捕获
     * 输入：File文件指针
     * 输出：该文件中含有的单词数
     */
    static int statisticsWords(File file) throws FileNotFoundException {
	     int wordNum = 0;
	     int num = 0;
	     String s = null;
	     try {
	         String ragex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
             Pattern p = Pattern.compile(ragex);
	         BufferedReader in = getReader(file);
	         String str = null;
	         while ((str = in.readLine()) != null){
				
	             //通过正则表达式对每一行的字符串进行匹配查找，若存在符合条件的单词则加入wordsMap
	           /*  String ragex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
	             Pattern p = Pattern.compile(ragex);*/
	             Matcher m = p.matcher(str);
	             while (m.find()) {
	                 s = m.group();
	                 wordNum ++;
	                 s = s.toLowerCase();
	                 if (wordsMap.containsKey(s)) {
	                     num = wordsMap.get(s);
	                     wordsMap.put(s, num + 1);
	                 }else {
	                     wordsMap.put(s, 1);
	                 }
	             }
	         }
	     } catch (FileNotFoundException e) {
	         throw e;
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     return wordNum;
    }
	 
    /*
     * 功能：通过对每一行的字符串的检查来统计文件的有效行数
     * 输入：File文件指针
     * 输出：该文件中含有的有效行数
     */
    static int statisticsLines(File file) throws FileNotFoundException {
        int lineNum = 0;
        int i = 0;
        try {
            BufferedReader in = getReader(file);
            String str = null;
            while ((str = in.readLine()) != null){
                for (i = 0;i<str.length();i++) {
                    if (str.charAt(i) != ' ' && str.charAt(i) !='\t') {
                        lineNum++;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
           throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineNum;
    }
    
    /*
     * 功能：通过将每个单词放入treemap，再将treemap按照value排序可获得前十个单词的频率
     * 输入：文件的指针
     * 输出：按字典序排序完成的List
     */
    static  List<Map.Entry<String, Integer>> wordsFrequency(File file) throws FileNotFoundException{
        
       //此部分可在查询单词个数的同时完成
       /* Map<String,Integer> wordsMap = new TreeMap<>();//单词和频率的映射表
        try {
            BufferedReader in = getReader(file);
            String str = null;
            while ((str = in.readLine()) != null){
               
                //通过正则表达式对每一行的字符串进行匹配查找，若存在符合条件的单词则加入wordsMap
                String ragex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
                Pattern p = Pattern.compile(ragex);
                Matcher m = p.matcher(str);
                while (m.find()) {
                    String s = m.group();
                    s = s.toLowerCase();
                    if (wordsMap.containsKey(s)) {
                        int num = wordsMap.get(s);
                        wordsMap.put(s, num + 1);
                    }else {
                        wordsMap.put(s, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet()); 
        
        //通过比较器实现比较
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });    
        return list;
    }
    
}
