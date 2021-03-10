import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib{
    Lib lib=new Lib();

    //单词正则表达式
    private static String word = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
    //分隔符正则表达式
    private static String Break = "[^a-zA-Z0-9]";
    //非空行正则表达式
    private static String line = "(^|\n)\\s*\\S+";
    //获取当前工程的路径
    public static String dir = System.getProperty("user.dir");
    //Map表用于存放单词以及相对应的个数
    private static Map<String, Integer> wordsMap = new HashMap<String, Integer>();



    public static String readFromFile(String filePath) {
        int temp;
        BufferedReader br = null;
        StringBuilder sbuilder = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            sbuilder = new StringBuilder();
            while((temp = br.read()) != -1) {
                sbuilder.append((char)temp);
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
                br.close();
            }
        }
        return sbuilder.toString();
    }

    public static int getCharactersNum(String str) {
        //字符数统计数量
        int num = 0;
        char[] temp = str.toCharArray();
        for(int i = 0; i < temp.length; i++) {
            if(temp[i] >= 0 && temp[i] <= 127) {
                num++;
            }
        }
        return num;
    }

    public static int getWordsNum(String str) {
        //单词数的统计量
        int num = 0;
        String[] temp1 = str.split(Break);
        for(int i = 0; i < temp1.length; i++) {
            if(temp1[i].matches(word))
            {
                num++;
                String temp = strs[i].toLowerCase();
                if(wordsMap.containsKey(temp))
                {
                    int j = wordsMap.get(temp);
                    wordsMap.put(temp, j+1);
                }
                else
                    {
                    wordsMap.put(temp, 1);
                }
            }
        }
        return num;
    }

    public static int getLineNum(String str) {
        //行数统计数量
        File file=new File(str);
        int count=0;

        if(file.exists())
        {
            try
            {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String line;
                while((line = in.readLine()) != null)
                {
                    if(!line.equals("") )
                    {
                        count ++;
                    }
                }
                in.close();
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }

        return count;
    }

}

    public static List<Map.Entry<String, Integer>> sortHashmap() {
        List<Map.Entry<String, Integer>> list;
        list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Entry<String, Integer> m1, Entry<String, Integer> m2) {
                if(m1.getValue().equals(m2.getValue())) {
                    return m1.getKey().compareTo(m2.getKey());
                }else return m2.getValue()-m1.getValue();
            }
        });
        return list;
    }

    public static void writeToFile(int characters, int words, int lines, String filePath) {
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        String str = "characters: " + characters + "\nwords: " + words + "\nlines: " + lines +"\n";
        List<Map.Entry<String, Integer>> list = sortHashmap();
        int i = 0;
        for(Map.Entry<String, Integer> map : list)
        {
            if(i < 10)
            {
                str += map.getKey() + ": " + map.getValue() + "\n";
                i++;
            }else break;
        }

        try {
            fos = new FileOutputStream(filePath);
            writer = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(str);
            bw.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                writer.close();
                bw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}