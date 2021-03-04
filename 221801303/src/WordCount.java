import java.io.File;

public class WordCount {
    static Lib lc=new Lib();
    public static void main(String[] args){
        File file =new File("input.txt");
        int line=lc.linecount(file);
        System.out.println("line:"+line);
        int c=lc.charcount(file);
        System.out.println("Chars:"+c);
    }
}
