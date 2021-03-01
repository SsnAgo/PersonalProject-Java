import java.io.*;

public class WordCount {

    public static void main(String[] args)  {
        String openFilePath = "D:\\IDEA\\PersonalProject-Java\\input.txt";
        String writeFilePath = "D:\\IDEA\\PersonalProject-Java\\output.txt";
        Counter counter = new Counter(openFilePath,writeFilePath);
        counter.open();
        counter.write();
    }
}
