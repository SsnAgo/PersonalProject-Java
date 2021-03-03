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
                long startTime = System.currentTimeMillis();
                Lib.beginCount(args[0]);
                Lib.writeFileContent(args[1]);
                long endTime = System.currentTimeMillis();
                System.out.println(endTime-startTime + "ms");
            } catch (IOException e) {
                System.out.println("File read error!");
                e.printStackTrace();
            }
        }
    }

}
