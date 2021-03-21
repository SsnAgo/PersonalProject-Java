import java.io.*;
import java.util.*;

class Lib {
    InputStreamReader in;
    OutputStreamWriter out;
    BufferedReader br;
    int countChar;//记录字符数
    int countWord;//记录单词数
    int countLine;//记录行数
    String inputFile;
    String outputFile;
    HashMap <String, Integer> map = new HashMap<>();//使用键值对来保存保存单词（key）和频率（value）
    List<Map.Entry<String,Integer>>list;

    Lib(String inputFile,String outputFile) {
        this.outputFile = outputFile;
        this.inputFile = inputFile;
        countChar = 0;
        countWord = 0;
        countLine = 0;
        List<Map.Entry<String,Integer>> list = null;
    }

    //读取文件字符数
    void getCountChar() throws FileNotFoundException {
        in=new InputStreamReader(new FileInputStream(inputFile));
        br = new BufferedReader(in);
        try {
            char ch;
            while((ch=(char)br.read())!=(char)-1)
            {
                if(ch<=127)//保证读取的字符为ASCLL码
                {
                    countChar++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取文件有效行数
     void getLineCount() throws FileNotFoundException {
        in = new InputStreamReader(new FileInputStream(inputFile));
        br = new BufferedReader(in);
        try {
            String line;
            while ((line = br.readLine()) != null) {
                char[] c=line.toCharArray();
                for (int i=0;i<c.length;i++)
                {
                    if (c[i]!='\n' )
                    {
                        countLine++;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取文件中单词个数
    void getWordNum() throws FileNotFoundException {
        in = new InputStreamReader(new FileInputStream(inputFile));
        br = new BufferedReader(in);
        String words;
        try {
            while ((words = br.readLine()) != null) {
                String[] strs=words.split("[^a-zA-Z0-9]");
                String regexs = "^[a-zA-Z]{4,}.*";//利用正则表达式筛选符合规则的单词
                for(int i=0;i<strs.length;i++)
                {
                    if(strs[i].matches(regexs))
                    {
                        countWord++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //选取出现频率最高的10个单词输出
     void getWordTopRate() throws IOException {
        in = new InputStreamReader(new FileInputStream(inputFile));
        br = new BufferedReader(in);
        String words;
        while ((words = br.readLine()) != null) {
            String[] strs = words.split("[^a-zA-Z0-9]");
            String regexs = "^[a-zA-Z]{4,}.*";
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].matches(regexs)) {
                    if (!map.containsKey(strs[i].toLowerCase())) {
                        //排除重复单词
                        map.put(strs[i].toLowerCase(), 1);
                    } else {
                        //使用map来保存出现过的单词，并变成全小写形式
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
                if(o1.getValue().equals(o2.getValue())) {
                    //频率相同按字典序排序
                    return o1.getKey().compareTo(o2.getKey());
                }
                else
                    //按值排序
                    return o2.getValue().compareTo(o1.getValue());
            }
        });
        
    }

    //将数据录入到指定文件中
    void writeFile() throws IOException {
            out = new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8");
            StringBuilder str = new StringBuilder();
            str.append("characters: "+countChar+"\n" + "words: "+countWord+"\n" +"lines: "+countLine+"\n");
            for(int i = 0;i<(list.size()<10 ? list.size():10);i++){
                str.append(list.get(i).getKey()+": "+list.get(i).getValue()+"\n");
            }
            out.write(str.toString());
            out.flush();
            out.close();
    }

}