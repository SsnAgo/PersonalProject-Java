import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Lib {
    public static int charnum=0;
    public static int linenum=0;
    public static int wordnum=0;
    public static Map<String, Object> word = new TreeMap<String, Object>();

    public static int countChar(String filepath)throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(filepath));
        if(!reader.ready())
        {
            System.out.println("文件流无法读取");
        }

        int result;
        while((result=reader.read())!=-1)
        {
            charnum+=1;
        }
        reader.close();
        return charnum;

    }
    public static int countLine(String filepath)throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(filepath));
        if(!reader.ready())
        {
            System.out.println("文件流无法读取");
        }

        String line=null;
        while((line=reader.readLine())!=null)
        {
            String s1 = line.replaceAll("\\s", "");
            if(s1.length()!=0)
                linenum+=1;
        }
        reader.close();
        return linenum;
    }
    public static int countWord(String filepath)throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(filepath));
        if(!reader.ready())
        {
            System.out.println("文件流无法读取");
        }

        String line=null;
        while((line=reader.readLine())!=null)
        {
            String s1 = line.replaceAll("\\W", " ");
            String s2 = s1.replaceAll(" +", " ");
            String []s3=s2.split(" ");
            for(String a:s3)
            {
                if(a.length()>3&&(a.charAt(0)<'0'||a.charAt(0)>'9'))
                {
                    word.put(a.toLowerCase(),0);

                }
            }

        }
        for (String key : word.keySet()) {
            wordnum++;
        }
        return wordnum;
    }
    public static  void countKeyword(String filepath)throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(filepath));
        if(!reader.ready())
        {
            System.out.println("文件流无法读取");
        }
        int num=0;
        String line=null;
        while((line=reader.readLine())!=null)
        {
            String s1 = line.replaceAll("\\W", " ");
            String s2 = s1.replaceAll(" +", " ");
            String []s3=s2.split(" ");
            for(String a:s3)
            {
                a=a.toLowerCase();
                if(a.length()>3&&(a.charAt(0)<'0'||a.charAt(0)>'9'))
                {
                    int number=(int)word.get(a);
                    word.put(a,number+1);
                }
            }

        }
    }

}
