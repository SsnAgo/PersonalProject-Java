
public class WordCount{

    Lib.TextFileSolver textFileSolver;
    String outputPath;
    StringBuilder answerBuilder;

    public WordCount(String inputPath,String outputPath) {
        textFileSolver = new Lib.TextFileSolver(inputPath);
        this.outputPath = outputPath;
        answerBuilder = new StringBuilder(50);
    }
    public void procceed(){
        initAnswerBuilder();
        writeAnswerToFile();
        System.out.println("successful!");
    }
    private void initAnswerBuilder(){
        answerBuilder.append("characters: ").append(textFileSolver.getFileCharNum()).append("\n");
        answerBuilder.append("words: ").append(textFileSolver.getWordNum()).append("\n");
        answerBuilder.append("lines: ").append(textFileSolver.getValidLineNum()).append("\n");
        textFileSolver.getOrderedWordFrequencyMap(10).forEach((word,frequency)->{
            answerBuilder.append(word).append(": ").append(textFileSolver.getValidLineNum()).append("\n");
        });
    }
    private void writeAnswerToFile(){
        Lib.IOUtil.writeTo(outputPath, answerBuilder.toString());
    }

    public static void main(String[] args) {
        WordCount wordCount = new WordCount("/Users/sarisemac/eclipse-workspace/testFunction/src/h.txt","/Users/sarisemac/eclipse-workspace/testFunction/src/output.txt");
        wordCount.procceed();
    }
}