import java.io.*;
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
    Map map = new TreeMap<String ,Integer>();

    public static Lib getInstance() {
        if (instance == null)
            instance = new Lib();
        return instance;
    }

    public String readTxt(String filePath){
        StringBuffer content = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            char[] buff = new char[1024];
            int len = -1;
            while( (len = br.read(buff)) != -1 ){
                content.append(new String(buff, 0, len));
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return content.toString();
    }

    public void write(String filePath, String data){
        try{
            File file =new File(filePath);

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fileWritter = new FileWriter(file.getName(),false);
            fileWritter.write(data);
            fileWritter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getCharTotalCount(String content){
        return content.length();
    }

    @SuppressWarnings("unchecked")
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
                String t = m.group(0).toLowerCase();
                if (map.containsKey(t)){
                    map.put(t,(Integer)map.get(t)+1);
                }else{
                    map.put(t,1);
                }
            }
        }
        return wordNum;
    }

    public int getLineCount(String content){
        String[] arr = content.split("\n");
        int num = arr.length;
        Pattern pattern = Pattern.compile("\\s*");
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
