import java.io.*;

public class WordCount {

    public static void main(String[] args) throws IOException {

        Lib lib=new Lib();
        String inputPath= args[0];
        String outputPath=args[1];

        String Char_Num=lib.GetCharacters(inputPath);
        int Word_Num=lib.GetWordsNum(inputPath);
        int Line_Num=lib.GetLinesNum(inputPath);

        System.out.print(args[0]);
        /*bufferedWriter.write("characters:"+Char_Num);
        bufferedWriter.newLine();
        bufferedWriter.write("words:"+Word_Num);
        bufferedWriter.newLine();
        bufferedWriter.write("lines:"+Line_Num);
        bufferedWriter.newLine();*/
        FileOutputStream fileOutputStream=new FileOutputStream(outputPath);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(inputPath);
        bufferedWriter.flush();


        System.out.print("BBBB");


    }
}
