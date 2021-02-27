import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
        try {
            //输入文件与输出文件
            File inputFile = new File(args[0]);
            File ouputFile = new File(args[1]);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
