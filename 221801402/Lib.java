import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Lib {
    //计算字符数
    public int CountChar(String inpath) {
        File file = new File(inpath);
        int i = 0;
        try {
            String name = file.getName();
            System.out.println("File:" + name);
            FileReader fr = new FileReader(file);
            BufferedReader bufr = new BufferedReader(fr);
            while(((char) bufr.read()) != (char)-1){
                i++;//按字符读取文本内容
            }
            bufr.close();
            fr.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }

    //计算行数
    public int CountLine(String inpath){
        File file = new File(inpath);
        int i = 0;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    //计算总单词数，统计单词词频
    public void CountWord(String inpath,String outpath,int charNum,int lineNum) {
        File file = new File(inpath);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bufr = new BufferedReader(fr);
            FileWriter out = new FileWriter(outpath);
            String line = null;
            String regex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
            Map<String, Integer> map = new HashMap<String, Integer>();
            //逐行读取，分割匹配单词
            while ((line = bufr.readLine()) != null) {
                line = line.toLowerCase();
                String[] words=line.split("[^(a-zA-Z0-9)]+");
                for(String word :words) {
                    //匹配单词
                    if (word.matches(regex)) {
                        if (map.get(word) == null) {
                            map.put(word, 1);
                        } else {
                            map.put(word, map.get(word) + 1);
                        }
                    }
                }
            }
            bufr.close();
            fr.close();

            //排序，值相同的根据字典序排序
            ArrayList<HashMap.Entry<String, Integer>> list =
                    new ArrayList<HashMap.Entry<String, Integer>>(map.entrySet());
            Collections.sort(list, new Comparator<HashMap.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String,Integer> o2) {
                    if (o1.getValue() < o2.getValue()) {
                        return 1;
                    } else {
                        if (o1.getValue().equals(o2.getValue())) {
                            if (o1.getKey().compareTo(o2.getKey()) > 0) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else {
                            return -1;
                        }
                    }
                }
            });

            int n = list.size();
            System.out.println("一共有" + n + "种单词");
            //输出到目标文件
            out.write("characters:"+charNum+"\n");
            out.write("words:"+n+"\n");
            out.write("lines:"+lineNum+"\n");
            if (n>10){
                n=10;
            }
            for (int i = 0; i < n; i++) {
                System.out.println(list.get(i).getKey() + ":" + list.get(i).getValue());
                out.write(list.get(i).getKey()+":"+list.get(i).getValue()+"\n");
            }
            out.flush();
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
