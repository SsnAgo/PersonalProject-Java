public class WordCount {
    public static void main(String argv[]){
    	Lib lib=new Lib();
        String str=lib.readTextFile("input.txt");
        System.out.println("characters:"+lib.countChars(str));
        System.out.println("words:"+lib.countWords(str));
        System.out.println("lines:"+lib.countLines(str));
        System.out.print(lib.countTimes(str));
    }
     
}
