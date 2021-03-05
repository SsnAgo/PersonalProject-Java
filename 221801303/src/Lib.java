import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    File out;
    public void set(String o){
        out = new File(o);
    }
    public void linecount(File file) {
        int count = 0;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bufr = new BufferedReader(fr);
            while (bufr.readLine() != null) {
                count++;
            }
            bufr.close();
            fr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        write("Lines:" + count + "\n");
    }

    public void charcount(File file){
        int count = 0;
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            while((char)bfr.read()!=(char)-1){
               count++;
            }
            bfr.close();
            fr.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        write("characters:" + count + "\n");
    }

    public void wordcount(File file){
        BufferedReader bfr = null;
        try {
            FileReader fr = new FileReader(file);
            bfr = new BufferedReader(fr);
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

        String stb=stb1.toString().toLowerCase();//将所有英文变为小写
        Pattern pattern = Pattern.compile("[a-zA-Z]{4,}+[0-9]*");//正则表达式
        Matcher matcher = pattern.matcher(stb);
        Map<String, Integer> map = new TreeMap<String, Integer>();
        String word = "";
        Integer num = null;
        int count = 0;

        while(matcher.find()) {
            word = matcher.group();
            count ++;
            if(map.containsKey(word)) {
                num = map.get(word);
                num += 1;
            } else {
                num = 1;
            }
            map.put(word, num);
        }
        write("words:" + count + "\n");
        //输出
        Set<String> keys = map.keySet();
        for (String key : keys) {
            int i=0;
            Integer value = map.get(key);
            write(key + ":" + value + "\n");
            i++;
            if(i>9) break;
        }
    }

    //输出到文件
    public void write(String content){
        try {
            FileWriter fw = new FileWriter(out,true);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(content);
            bfw.close();
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

