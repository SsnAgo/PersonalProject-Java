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

}
