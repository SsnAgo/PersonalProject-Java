import java.io.PrintWriter;

public class WordCount {

    public static void  main(String args[]) {
        String inFile = args[0];
        String outFile = args[1];

        try{
            int charNum;
            Lib a=new Lib();
            charNum=a.CountChar(inFile);
            System.out.println(charNum);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
