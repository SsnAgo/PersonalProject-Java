import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.util.*;

public class Lib {
    public static String readFormTxt(String txtPath) {
        File file = new File(txtPath);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int temp;
            while ((temp = br.read()) !=-1) {
                result.append((char) temp);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static int getCharactersCount(String str) {
        int count = 0;
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 0 && ch[i] < 128) {
                count++;
            }
            //只计算ACSII字符
        }
        return count;
    }

    public static int getWordsCount(String str) {
        int count = 0;
        String temp = null;
        String regex = "^[a-z]+$";
        str = str.toLowerCase();//将字符串变成小写
        String[] strArray = str.split("[^a-z0-9]+");
        //用split方法将非字母数字的字符作为分割线分割成单词数组，字符串已经是小写了，不存在A-Z
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].length() >= 4) {//至少要四个英文字母开头，也就是说单词至少需要四个字符
                temp = strArray[i].substring(0, 4);//取出分割后字符串的前四个字符
                if (temp.matches(regex))
                    count++;
            }
        }
        return count;
    }

    public static int getLineCount(String str) {
        str = str.replaceAll("[^\\S\\r\\n]", "");//将str中除了换行符以外的空白字符删掉
        String count[] = StringUtils.splitString(str,"\r|\n|\r\n");//将新字符串以split方法进行换行符分割后，利用length方法统计行数
        return count.length;
    }

    public static List getWordFrequency(String str) {
        HashMap map = new HashMap<>();
        String temp = null;
        String regex = "^[a-z]+$";
        str = str.toLowerCase();//将字符串变成小写
        String[] strArray = str.split("[^a-z0-9]+");
        //用split方法将非字母数字的字符作为分割线分割成单词数组，字符串已经是小写了，不存在A-Z
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].length() >= 4) {//至少要四个英文字母开头，也就是说单词至少需要四个字符
                temp = strArray[i].substring(0, 4);//取出分割后字符串的前四个字符
                if (temp.matches(regex)) {
                    int freq = map.get(strArray[i]) == null ? 0 : (int) map.get(strArray[i]);
                    map.put(strArray[i], freq == 0 ? 1 : freq + 1);//利用hashmap，将单词作为键，出现次数作为值
                }
            }
        }
        //实现排序
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());//将entrySet转换为List
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {//修改比较器达到排序目的
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o2.getValue().compareTo(o1.getValue())==0){//同值情况按字典排序
                    return o1.getKey().compareTo(o2.getKey());
                }
                else
                    return o2.getValue().compareTo(o1.getValue());//不同值情况按值排序
            }
        });
        return list;
    }
    public static int writeToTxt(String txtPath,String str){
        File file=new File(txtPath);
        List<Map.Entry<String, Integer>> list=Lib.getWordFrequency(str);
        try {
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
            bw.write("Characters: "+Lib.getCharactersCount(str)+"\n");
            bw.write("words: "+Lib.getWordsCount(str)+"\n");
            bw.write("lines: "+Lib.getLineCount(str)+"\n");
            for(int i=0;i<(list.size()<10?list.size():10);i++){
                bw.write(list.get(i).getKey()+": "+list.get(i).getValue()+"\n");
            }
            bw.flush();
            bw.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}