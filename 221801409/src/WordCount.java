public class WordCount {
    public static void main(String[] args) {
        System.out.println(Lib.readFormTxt("D:/123.txt"));
        System.out.println(Lib.getCharactersCount(Lib.readFormTxt("D:/123.txt")));
        System.out.println(Lib.getWordsCount(Lib.readFormTxt("D:/123.txt")));
    }
}
