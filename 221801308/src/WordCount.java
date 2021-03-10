import java.io.*;

/**
 * @author wangyu
 */
public class WordCount {
    public static void main(String [] args) throws IOException {
        if(args.length<2) {
            System.out.println("The parameter is less than two, please run again");
            return;
        }
        long startTime = System.currentTimeMillis();
        //Lib.beginCount("C:\\Users\\wangyu\\Desktop\\PersonalProject-Java\\221801308\\src\\input7.txt");
        //Lib.writeFileContent("C:\\Users\\wangyu\\Desktop\\PersonalProject-Java\\221801308\\src\\output7.txt");
        Lib.beginCount(args[0]);
        Lib.writeFileContent(args[1]);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime + "ms");
    }

}
