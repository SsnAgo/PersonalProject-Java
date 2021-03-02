/*
    Created at 2021/2/25 by GONGHAIXU
 */
public class WordCount {

    public static void main(String[] args){
        String s = FileUtil.getFullFile(args[0]);
    }

    public static int countChars(String s){
        return s.length();
    }

    public static int countWords(String s){
        String[] splitString = s.split("[^0-9|^A-z]");
        int wordsNum = splitString.length;
        for (String str:
             splitString) {
            if(str.isEmpty() || !str.matches("^[A-z]{4,}[A-z|0-9]*$")) wordsNum--;
        }
        return wordsNum;
    }

    public static int countLInes(String s){
        String[] splitString = s.split("\n");
        int linesNum = splitString.length;
        for (String str:
                splitString) {
            if(str.matches("^\\s*")) linesNum--;
        }
        return linesNum;
    }

}
