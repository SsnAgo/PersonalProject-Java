import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WordCount {

    public static void main(String[] args) throws IOException {

        Lib lib=new Lib();
        File InputFile= new File(args[0]);
        File OutputFile=new File(args[1]);
        FileWriter fileWriter=new FileWriter(OutputFile);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

        int Char_Num=lib.GetCharactersNum(InputFile);
        int Word_Num=lib.GetWordsNum(InputFile);
        int Line_Num=lib.GetLinesNum(InputFile);

        bufferedWriter.write("characters:"+Char_Num);
        bufferedWriter.newLine();
        bufferedWriter.write("words:"+Word_Num);
        bufferedWriter.newLine();
        bufferedWriter.write("lines:"+Line_Num);
        bufferedWriter.newLine();



    }
}
