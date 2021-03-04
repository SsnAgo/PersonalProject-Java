import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Lib {
    public int linecount(File file) {
        int i=0;
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

    public int charcount(File file){
        int countc=0;
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            while((char)bfr.read()!=(char)-1)//按字符读取文本内容
            {
               countc++;
            }
            bfr.close();
            fr.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return countc;
    }
}
