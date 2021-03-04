import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Control {
    static Lib lc=new Lib();
    public void run(){
        File file =new File("input.txt");
        int line=lc.linecount(file);
        System.out.println("line:"+line);
        int c=lc.charcount(file);
        System.out.println("Chars:"+c);
        lc.wordcount(file);
        
        String content ="test1111";
        File out=new File("output.txt");
        try {
            FileWriter fw=new FileWriter(out);
            BufferedWriter bfw=new BufferedWriter(fw);
            bfw.write(content);
            bfw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
