public class WordCount {
    public static void main(String[] args) {
        String str = Lib.readFormTxt(args[0]);
        Lib.writeToTxt(args[1], str);
    }
}
