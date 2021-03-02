import java.io.*;

public class CountCore {
    public static int characterCount(File inputFile){
        BufferedReader bufferedReader= null;
        int characterCount=0;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!(bufferedReader.read()!=-1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            characterCount++;
        }
        return characterCount;
    }
}
