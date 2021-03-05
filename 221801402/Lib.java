import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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

}
