import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class WordCount {
    static LineCount lc=new LineCount();
    public static void main(String[] args){
        File file =new File("input.txt");
        int line=lc.count(file);
        System.out.println("line"+line);
    }
}
