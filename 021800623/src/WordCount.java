import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("there are 2 parameters needed!");
            return;
        }
        String input = args[0];
        String output = args[1];

        Lib lib = new Lib(input,output);
        lib.writeFile();
    }
}
