package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/****
 * 2021.3.2
 * 工具类 单例
 * @author 王少聪
 */
public class Lib {

    private static Lib instance;

    public static Lib getInstance() {
        if (instance == null)
            instance = new Lib();
        return instance;
    }

    public String readTxt(String filePath){
        StringBuilder content = new StringBuilder();
        try {
            String encoding="utf8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while((lineTxt = bufferedReader.readLine()) != null){
                    content.append(lineTxt).append("\n");
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return content.toString();
    }

    public int getCharTotalCount(String content){
        char[] arr = content.toCharArray();
        return arr.length;
    }

    public int getWordTotalCount(String content){
        int wordNum = 0;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Pattern wordPattern = Pattern.compile("^([a-z]|[A-Z]){4}([a-z]|[A-Z]|[0-9])*");
        String[] arr = pattern.split(content);
        for(String str : arr){
            if (str.equals(""))
                continue;
            Matcher m = wordPattern.matcher(str);
            if (m.matches()){
                wordNum++;
            }
        }
        return wordNum;
    }

    public int getLineCount(String content){
        String[] arr = content.split("\n");
        int num = arr.length;
        Pattern pattern = Pattern.compile("^\s*");
        for(String str : arr){
            Matcher matcher = pattern.matcher(str);
            if (matcher.matches()){
                num--;
            }
        }
        return num;
    }

    @SuppressWarnings("unchecked")
    public Map<String,Integer> getWordNum(String content){
        Map map = new TreeMap<String ,Integer>();
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Pattern wordPattern = Pattern.compile("^([a-z]|[A-Z]){4}([a-z]|[A-Z]|[0-9])*");
        String[] arr = pattern.split(content);
        for(String str : arr){
            Matcher m = wordPattern.matcher(str);
            if (m.matches()){
                String t = m.group(0).toLowerCase();
                if (map.containsKey(t)){
                    map.put(t,(Integer)map.get(t)+1);
                }else{
                    map.put(t,1);
                }
            }
        }
        return sortMapByValue(map);
    }

    public Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(oriMap.entrySet());
        entryList.sort(new MapValueComparator());

        int num = 0;
        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry;
        while (iter.hasNext() && num < 10) {
            num++;
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    static class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

            return me2.getValue().compareTo(me1.getValue());
        }
    }
}
