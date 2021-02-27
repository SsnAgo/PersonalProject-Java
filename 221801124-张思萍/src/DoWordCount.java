import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class DoWordCount {

    //统计字符数，空格，水平制表符，换行符，均算字符
    public static int countChars(String filePath) throws IOException {
        Reader reader=FileIO.getReader(filePath);
        int sum=0;
        int tempChar;
        while ((tempChar=reader.read())!=-1){
            sum++;
        }
        return sum;
    }

    //统计文件的有效行数：任何包含非空白字符的行，都需要统计。
    public static int countLines(ArrayList<String> lines) throws IOException{
        int sum=0;
        String line;
        for (int i = 0; i < lines.size(); i++) {
            line=lines.get(i);
            line=line.replaceAll("\\s*","");
            if(!(line.equals(""))){
                sum++;
            }
        }
        return sum;
    }

}
