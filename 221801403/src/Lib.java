import java.io.*;
import java.util.*;

class Lib {

    InputStreamReader in;
    BufferedReader br;
    String inputFile;
    HashMap <String, Integer> map = new HashMap<>();
    List<Map.Entry<String,Integer>>list;

    Lib(String inputFile) {

        this.inputFile = inputFile;

    }
    //读取文件字符数
    int getCountChar() throws FileNotFoundException {
        in=new InputStreamReader(new FileInputStream(inputFile));
        int num=0;
        try {
            while(in.read()!=-1)
            {
                num++;
            }
            System.out.println(num);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }
    //读取文件有效行数
     int getLine() throws IOException{
        int linenum=0;
        in = new InputStreamReader(new FileInputStream(inputFile));
        br = new BufferedReader(in);
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.trim().equals(""))
                continue;
            else
                linenum++;
        }
        System.out.println(linenum);
        return linenum;
    }
    //读取文件中单词个数
    int getWordNum() throws IOException {
        int wordnum=0;
        in = new InputStreamReader(new FileInputStream(inputFile));
        br = new BufferedReader(in);
        String words;
        while ((words = br.readLine()) != null) {
            String[] strs=words.split("[^a-zA-Z0-9]");
            String regexs = "^[a-zA-Z]{4,}.*";
            for(int i=0;i<strs.length;i++)
            {
                if(strs[i].matches(regexs))
                {
                    wordnum++;
                }
            }
        }
        System.out.println(wordnum);
        return wordnum;
    }
    List getWordTopRate() throws IOException {
        in = new InputStreamReader(new FileInputStream(inputFile));
        br = new BufferedReader(in);
        String words;
        while ((words = br.readLine()) != null) {
            String[] strs = words.split("[^a-zA-Z0-9]");
            String regexs = "^[a-zA-Z]{4,}.*";
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].matches(regexs)) {
                    if (!map.containsKey(strs[i].toLowerCase())) {
                        map.put(strs[i].toLowerCase(), 1);
                    } else {
                        int num = map.get(strs[i].toLowerCase());
                        map.put(strs[i].toLowerCase(), num + 1);
                    }
                }
            }
        }
        list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue().equals(o2.getValue())) {return o1.getKey().compareTo(o2.getKey());}
                else {return o2.getValue().compareTo(o1.getValue());}
            }
        });
        for(int i = 0;i<(list.size()<10 ? list.size():10);i++){
            System.out.println(list.get(i).getKey()+": "+list.get(i).getValue());
        }
        return list.size() < 10 ? list.subList(0, list.size()) : list.subList(0, 10);

    }

    
}