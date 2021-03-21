import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Lib类用于统计字符数，行数，单词数，词频
 * 并保存在静态变量之中供主类调用
 */
public class Lib {
    public static int charnum = 0;//记录字符数
    public static int linenum = 0;//记录有效行数
    public static int wordnum = 0;//记录单词数
    public static int realnum=0;//记录存在keyWord中单词的真实数量
    public static Map<String, Object> word = new TreeMap<String, Object>();//记录所有单词
    public static Map<String, Object> keyWord = new TreeMap<String, Object>();//记录单词数量前十的单词
    /**
     * 统计字符数函数
     * @param filepath
     * @throws IOException
     */
    public static void countChar(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        if (!reader.ready()) {
            System.out.println("文件流无法读取");
        }

        int result;
        while ((result = reader.read()) != -1) {
            charnum += 1;
        }
        reader.close();


    }
    /**
     * 统计有效行数
     * @param filepath
     * @throws IOException
     */
    public static void countLine(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        if (!reader.ready()) {
            System.out.println("文件流无法读取");
        }

        String line = null;
        while ((line = reader.readLine()) != null) {
            String s1 = line.replaceAll("\\s", "");
            if (s1.length() != 0)
                linenum += 1;
        }
        reader.close();

    }
    /**
     * 统计单词和词频
     * @param filepath
     * @throws IOException
     */
    public static void countWord(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        if (!reader.ready()) {
            System.out.println("文件流无法读取");
        }

        String line = null;
        while ((line = reader.readLine()) != null) {

            String[] s3 = line.split("[^A-Za-z0-9]");
            for (String a : s3) {
                a=a.toLowerCase();
                if (a.length() > 3 && (a.charAt(0) < '0' || a.charAt(0) > '9')) {

                    if(word.get(a)==null)
                    {
                        word.put(a, 1);
                    }
                    else
                    {
                        int number = (int) word.get(a);
                        word.put(a, number + 1);
                    }

                }
            }

        }
        int total = 0;
        for (String key : word.keySet()) {
            total += (int) word.get(key);
        }
        wordnum = total;

    }
    /**
     * 统计数量前十的单词
     */
    public static void countKeyword() {
        int n=wordnum;
        int N=10;
        for(int i=0;i<N;i++)
        {

            int max=0;
            String maxs=null;
            for (String key : Lib.word.keySet()) {
                if(max<(int) Lib.word.get(key))
                {
                    max=(int) Lib.word.get(key);
                    maxs=key;

                }
                if(max==(int) Lib.word.get(key))
                {
                    if(maxs.compareTo(key)>0)
                    {
                        max=(int) Lib.word.get(key);
                        maxs=key;
                    }
                }
            }
            keyWord.put(maxs,max);
            Lib.word.remove(maxs);
            realnum++;
            n--;
            if(n<=0) break;
        }

    }
}