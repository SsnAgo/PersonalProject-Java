import java.io.File;

public class WordCount {
    public static void main(String[] args){
        try{
            File inputFile = new File(args[0]);
            File ouputFile = new File(args[1]);
            Counter counter = new Counter(inputFile,ouputFile);
        }
        catch(Exception e){
        }
    }
}
