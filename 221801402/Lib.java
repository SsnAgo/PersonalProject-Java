import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Lib {
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



    public void CountWord(String inpath,String outpath,int charNum,int lineNum) {
        File file = new File(inpath);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bufr = new BufferedReader(fr);
            FileWriter out = new FileWriter(outpath);
            String line = null;
            String regex = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
            Map<String, Integer> map = new HashMap<String, Integer>();
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

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
