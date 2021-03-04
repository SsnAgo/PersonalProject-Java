import java.io.*;
import java.util.*;

class Lib {
    int num;
    int wordnum;
    int linenum;
    InputStreamReader in;
    OutputStreamWriter out;
    BufferedReader br;
    String inputFile;
    String outputFile;
    HashMap <String, Integer> map = new HashMap<>();
    List<Map.Entry<String,Integer>>list;

    Lib(String inputFile,String outputFile) {
        this.outputFile = outputFile;
        this.inputFile = inputFile;
        num = 0;
        wordnum = 0;
        linenum = 0;
    }
    //读取文件字符数
    int getCountChar() throws FileNotFoundException {
        in=new InputStreamReader(new FileInputStream(inputFile));

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

    void writeFile() throws IOException{
            out = new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8");
            StringBuilder str = new StringBuilder();
            str.append("characters: "+num+"\n" + "words: "+wordnum+"\n" +"lines: "+linenum+"\n");
            for(int i = 0;i<(list.size()<10 ? list.size():10);i++){
                str.append(list.get(i).getKey()+": "+list.get(i).getValue()+"\n");
            }
            out.write(str.toString());
            out.flush();
            out.close();
    }

}
    
}