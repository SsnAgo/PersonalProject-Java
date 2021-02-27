import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class FileIO {

    public static Reader getReader(String filePath) throws FileNotFoundException {
        File file=new File(filePath);
        Reader reader=null;
        reader=new InputStreamReader(new FileInputStream(file));
        return reader;
    }


}
