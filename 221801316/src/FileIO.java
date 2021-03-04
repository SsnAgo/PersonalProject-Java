import java.io.*;

public class FileIO {
    
    public static void writeToFile(File outputFile, String content){
        try {
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(new FileOutputStream(outputFile));
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
