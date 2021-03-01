import java.io.*;

public class Lib {

    private String readFileName;
    private String writeFileName;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Lib(String readFileName, String writeFileName) {
        this.readFileName = readFileName;
        this.writeFileName = writeFileName;
    }

   private String readFile() throws IOException {
        StringBuilder fileBuilder = new StringBuilder();
       try {
           bufferedReader = new BufferedReader(new FileReader(readFileName));
           int c;
           while ((c=bufferedReader.read())!=-1)
           {
               fileBuilder.append((char)c);
           }

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       return fileBuilder.toString();
   }
}
