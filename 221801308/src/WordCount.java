import java.io.*;

/**
 * @author wangyu
 */
public class WordCount {
    public static void main(String [] args) {
        if(args.length<2) {
            System.out.println("The parameter is less than two, please run again");
        } else {
            try {
                Lib  wordCount = new Lib(args[0], args[1]);
                //wordCount.readFileContent();
                wordCount.beginCount();
                wordCount.writeFileContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
