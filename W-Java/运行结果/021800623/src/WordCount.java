import java.io.IOException;

public class WordCount {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("there are 2 parameters needed!");
            return;
        }
        String input = args[0];
        String output = args[1];

        Lib lib = new Lib(input,output);
        try {
            lib.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not Found");
        }
    }
}
