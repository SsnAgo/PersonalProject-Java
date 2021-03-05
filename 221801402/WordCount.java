import java.io.PrintWriter;

public class WordCount {

    public static void  main(String args[]) {
        String inFile = args[0];
        String outFile = args[1];

        try{
            int charNum,lineNum;
            PrintWriter pw=new PrintWriter(outFile);//每次用新input.txt内容时将原output.txt的内容清除
            pw.write("");
            pw.flush();
            pw.close();
            Lib a=new Lib();
            charNum=a.CountChar(inFile);
            System.out.println(charNum);
            lineNum=a.CountLine(inFile);
            System.out.println(lineNum);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
