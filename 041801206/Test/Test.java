import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Test {

    @org.junit.Test
    public void t1(){
        String path="C:\\Users\\cmy\\Desktop\\test.txt";
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(path), "utf-8");
            BufferedReader in = new BufferedReader(read);
            String temp = null;
            long start = System.currentTimeMillis();
            while ((temp = in.readLine()) != null) {
               System.out.println(temp.contains("\r"));
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
