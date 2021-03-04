import java.io.File;

public class Control {
    static Lib lc=new Lib();
    public void run(){
        File file =new File("input.txt");
        int line=lc.linecount(file);
        System.out.println("line:"+line);
        int c=lc.charcount(file);
        System.out.println("Chars:"+c);
        lc.wordcount(file);
    }
}
