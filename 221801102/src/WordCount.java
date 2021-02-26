import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("参数不足");
            return;
        }
        String input = args[0];
        String output = args[1];
        Lib analyser = new Lib(input, output);
        analyser.process();

        // 全部处理
        System.out.println(analyser.getLineNum());
        System.out.println(analyser.getCharNum());
        System.out.println(analyser.getWordNum());
        analyser.getTopWord().forEach((s, integer) -> {
            System.out.println(s + " " + integer);
        });

        // analyser.output();
    }

    public static void makeInput() {

    }
}