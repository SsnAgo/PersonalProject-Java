import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Lib {
    public static int charnum = 0;
    public static int linenum = 0;
    public static int wordnum = 0;
    public static int realnum=0;
    public static Map<String, Object> word = new TreeMap<String, Object>();
    public static Map<String, Object> keyWord = new TreeMap<String, Object>();
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