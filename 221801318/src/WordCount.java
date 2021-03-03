public class WordCount {
    private Lib lib = new Lib();
    private String inputFilePath = "E:\\GitHub\\PersonalProject-Java\\221801318\\input.txt";
    private String outputFilePath = "E:\\GitHub\\PersonalProject-Java\\221801318\\output.txt";

    public static void main(String[] args){
        WordCount wordCount = new WordCount();
        wordCount.runCount();
    }

    public void runCount(){
        lib.returnCharacters(inputFilePath);
    }
}