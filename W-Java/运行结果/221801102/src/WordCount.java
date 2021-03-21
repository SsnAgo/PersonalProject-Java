import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("require 2 parameters");
            return;
        }
        String input = args[0];
        String output = args[1];
        // 全部处理
        Lib analyser = new Lib(input, output);
        analyser.process();
        analyser.output();
    }
}