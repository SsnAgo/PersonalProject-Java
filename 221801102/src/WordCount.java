import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("参数不足");
            return;
        }
        String input = args[0];
        String output = args[1];
        Lib analyser = new Lib(input);
        analyser.process();

        // 全部处理
        System.out.println(analyser.getLineNum());
        System.out.println(analyser.getCharNum());
        System.out.println(analyser.getWordNum());
        analyser.getTopWord().forEach((s, integer) -> {
            System.out.println(s + " " + integer);
        });

        // 单独处理行数
        analyser.processLineNum();
        System.out.println(analyser.getLineNum());

        // 单独处理字符数
        analyser.processCharNum();
        System.out.println(analyser.getCharNum());

        // 单独处理单词数
        analyser.processWordNum();
        System.out.println(analyser.getWordNum());

        // 单独处理单词频率top10
        analyser.processWordRank();
        analyser.getTopWord().forEach((s, integer) -> {
            System.out.println(s + " " + integer);
        });
    }
}