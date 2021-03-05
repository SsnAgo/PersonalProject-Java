
public class WordCount {

    public static void  main(String args[]) {
        String inFile = args[0];
        String outFile = args[1];

        try{
            int charNum,lineNum;
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
