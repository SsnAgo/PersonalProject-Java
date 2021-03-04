import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LineCount {
    public int count(File file) {
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
}
