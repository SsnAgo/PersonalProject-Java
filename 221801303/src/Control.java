import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Control {
    public File in;
    Lib cou = new Lib();
    public void set(String i,String o){
        in = new File(i);
        cou.set(o);
    }

    public void run(){
        cou.charcount(in);
        cou.linecount(in);
        cou.wordcount(in);
    }

}
