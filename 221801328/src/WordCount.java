import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class WordCount {

    public static void main(String[] args) {

        if (args.length >= 2)
        {
//            String inputFileName = "input.txt";
//            String outputFileName = "output.txt";
            String inputFileName = args[0];
            String outputFileName = args[1];
            Lib lib = new Lib(inputFileName,outputFileName);
            lib.startCount();
            lib.outputResult();
        }
        else
            System.out.println("错误，请输入两个文件的名字");
    }
}
