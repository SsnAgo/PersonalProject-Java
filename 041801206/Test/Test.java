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
            char[] chars=new char[1000];
            int whileCount=0;
            int remain=0;
            int _nNum=0;
            while ((in.read(chars)) != -1) {
                remain=0;
                for (int i=0;i<chars.length;i++){
                    if((int)chars[i]==0) {
                        remain=i;
                        break;
                    }
                    if (chars[i]=='\n') _nNum++;
                }
                whileCount++;
            }

            int characterNum=(whileCount-1)*1000+remain;
            System.out.println(_nNum);
            System.out.println("sum:"+characterNum);
            System.out.println((int)chars[6]);
            System.out.println((int)chars[7]);
            System.out.println((int)chars[8]);
            System.out.println((int)chars[9]);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
