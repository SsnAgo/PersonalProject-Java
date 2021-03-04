import java.io.*;
import java.util.Map;

import java.util.TreeMap;
public class WordCount {
    private static Map<String, Object> word = new TreeMap<String, Object>();
    public static void main(String[] args) throws IOException{
        // write your code here

        FileWriter file=new FileWriter(args[1]);
        BufferedWriter out = new BufferedWriter(file);
        out.write("characters: "+countChar(args[0])+"\n");
        out.write("words: "+countWord(args[0])+"\n");
        out.write("lines: "+countLine(args[0])+"\n");
        out.close();
        countKeyword(args[0],args[1]);
        System.out.println("文件创建成功！");
    }
    public static int countChar(String filepath)throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(filepath));
        if(!reader.ready())
        {
            System.out.println("文件流无法读取");
        }
        int num=0;
        int result;
        while((result=reader.read())!=-1)
        {
            num+=1;
            System.out.print((char)result);

        }
        reader.close();
        return num;

    }
    public static int countLine(String filepath)throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(filepath));
        if(!reader.ready())
        {
            System.out.println("文件流无法读取");
        }
        int num=0;
        String line=null;
        while((line=reader.readLine())!=null)
        {
            num+=1;
        }
        reader.close();
        return num;
    }
    public static int countWord(String filepath)throws IOException {
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
                if(a.length()>3&&(a.charAt(0)<'0'||a.charAt(0)>'9'))
                {
                    word.put(a.toLowerCase(),0);

                }
            }

        }
        for (String key : word.keySet()) {
            num++;
        }
        return num;
    }
    public static  void countKeyword(String filepath, String filepath2)throws IOException {
        int n=countWord(filepath);
        int N=10;
        FileWriter file=new FileWriter(filepath2,true);
        BufferedWriter out = new BufferedWriter(file);
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
        for(int i=0;i<=N;i++)
        {
            int max=0;
            String maxs=null;
            for (String key : word.keySet()) {
                if(max<(int)word.get(key))
                {
                    max=(int)word.get(key);
                    maxs=key;

                }
                if(max==(int)word.get(key))
                {
                    if(maxs.compareTo(key)>0)
                    {
                        max=(int)word.get(key);
                        maxs=key;
                    }
                }
            }
            out.write(maxs+":"+max+"\n");
            word.remove(maxs);
            n--;
            if(n<=0) break;
        }

        out.close();

    }

}
