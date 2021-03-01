import java.io.IOException;
import java.io.Writer;

public class WordCount{

    public static void main(String[] args) {
        FileTool Temp=new FileTool();
        Temp.getWriter(args[1]);
        Temp.getReader(args[0]);
        try {
            Temp.Writer.write("123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Temp.closeReader();
        Temp.closeWriter();
    }

    }
