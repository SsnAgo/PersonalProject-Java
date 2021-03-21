import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        if (args.length < 2){
            System.out.println("Insufficient parameters");
        }
        else{
            String inputFile = args[0];
            String outputFile = args[1];
            Lib lib = new Lib(inputFile, outputFile);
            lib.readFile();
            lib.writeFile();
            System.out.println("succeed");
        }
    }
}
