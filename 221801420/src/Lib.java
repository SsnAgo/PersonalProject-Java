import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lib {
    static Map<String,Integer> wordsMap = new TreeMap<>();//单词和频率的映射表	

    /*
            * 功能：统计文件字符数
            * 输入：File文件指针
            * 输出：该文件中含有的字符数
     */
    static int statisticsCharacters(File file) {
        int characterNum = 0;
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader in = new BufferedReader(read);
            String str = null;
            while ((str = in.readLine()) != null){
                for (int i = 0;i<str.length();i++) {
                    int characterAscill=str.charAt(i);
                    if (characterAscill >=0 && characterAscill <= 127) {
                        characterNum++;
                    }
                }
                characterNum++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
    static int statisticsWords(File file) {
	     int wordNum = 0;
	     try {
	         InputStreamReader read = new InputStreamReader(new FileInputStream(file));
	         BufferedReader in = new BufferedReader(read);
	         String str = null;
	         while ((str = in.readLine()) != null){
				
	             //通过正则表达式对每一行的字符串进行匹配查找，若存在符合条件的单词则加入wordsMap
	             String ragex = "[a-zA-Z]{4,}[a-zA-Z0-9]+";
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
	                 wordNum++;
	             }
	         }
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
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
    static int statisticsLines(File file) {
        int lineNum = 0;
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader in = new BufferedReader(read);
            String str = null;
            while ((str = in.readLine()) != null){
                for (int i = 0;i<str.length();i++) {
                    if (str.charAt(i) != ' ' && str.charAt(i) !='\t') {
                        lineNum++;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineNum;
    }
    
    /*
            * 功能：通过将每个单词放入treemap，再将treemap按照value排序可获得前十个单词的频率
            * 输入：输出文件的写指针
            * 输出：无
     */
    static void wordsFrequency(BufferedWriter buffWriter){
        if (!wordsMap.isEmpty()) {
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet()); 
            
            //通过比较器实现比较
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                    return o2.getValue().compareTo(o1.getValue());  
                }  
            });  
            int num = 1;
                for(Map.Entry<String, Integer> map : list) {  
                    if(num <= 10) {  
                        try {
                            buffWriter.write("<" + map.getKey() + ">: " + map.getValue());
                            buffWriter.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }  
                        num++;  
                    }else break;  
                }  
        }
    }
}
