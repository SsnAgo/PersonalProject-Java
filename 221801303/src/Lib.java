import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    public int linecount(File file) {
        int i=0;
        try {
            String name = file.getName();
            System.out.println("File:" + name);
            FileReader fr = new FileReader(file);
            BufferedReader bufr = new BufferedReader(fr);
            while (bufr.readLine() != null) {
                i++;
            }
            bufr.close();
            fr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       return i;
    }

    public int charcount(File file){
        int countc=0;
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            while((char)bfr.read()!=(char)-1)//按字符读取文本内容
            {
               countc++;
            }
            bfr.close();
            fr.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return countc;
    }

    public void wordcount(File file){
        BufferedReader bfr = null;
        try {
            bfr = new BufferedReader(new FileReader("input.txt"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer stb1 = new StringBuffer();
        String line = null;
        try {
            while((line = bfr.readLine()) != null) {
                stb1 = stb1.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bfr.close();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        String stb=stb1.toString().toLowerCase();
        Pattern pattern = Pattern.compile("[a-zA-Z]{4,}+[0-9]*");//正则表达式
        Matcher matcher = pattern.matcher(stb);
        Map<String, Integer> map = new TreeMap<String, Integer>();
        String word = "";
        Integer num = null;
        int total = 0;

        while(matcher.find()) {
            word = matcher.group();
            total ++;
            if(map.containsKey(word)) {
                num = map.get(word);
                num += 1;
            } else {
                num = 1;
            }
            map.put(word, num);
        }
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Integer value = map.get(key);
            System.out.printf("%s: %s\n", key, value);
        }
        System.out.println();
        System.out.println("total words : " + total);
        System.out.println("different words : " + map.size());
    }
}

