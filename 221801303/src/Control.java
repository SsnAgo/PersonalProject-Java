import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Control {
    static Lib lc = new Lib();
    public void run(){
        File file = new File("input.txt");
        lc.charcount(file);
        lc.linecount(file);
        lc.wordcount(file);
    }

    public void write(String content){
        File out = new File("output.txt");
        try {
            FileWriter fw = new FileWriter(out,true);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(content);
            bfw.close();
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
