import java.io.IOException;
import java.util.Date;

public class WordCount{

    Lib.TextFileSolver textFileSolver;
    String outputPath;
    StringBuilder answerBuilder;

    public WordCount(String inputPath,String outputPath) throws IOException {
        //初始化
        textFileSolver = new Lib.TextFileSolver(inputPath);
        this.outputPath = outputPath;
        answerBuilder = new StringBuilder(50);
    }

    /**
     * 运行
     */
    public void procceed(){
        initAnswerBuilder();
        writeAnswerToFile();
    }

    /**
     * 私有函数
     * 构造答案字符串
     */
    private void initAnswerBuilder(){
        String lineSeparator = System.getProperty("line.separator");
        answerBuilder.append("characters: ").append(textFileSolver.getFileCharNum()).append('\n');
        answerBuilder.append("words: ").append(textFileSolver.getWordNum()).append('\n');
        answerBuilder.append("lines: ").append(textFileSolver.getValidLineNum()).append('\n');
        textFileSolver.getOrderedWordFrequencyMap(10).forEach((word,frequency)->{
            answerBuilder.append(word).append(": ").append(frequency).append('\n');
        });
    }

    /**
     * 私有函数
     * 将答案写入文件
     */
    private void writeAnswerToFile(){
        Lib.IOUtil.writeTo(outputPath, answerBuilder.toString());
    }

    public static void main(String[] args) {
        //textLongData();
        //textBigData();
        //WordCount wordCount = new WordCount("/Users/sarisemac/eclipse-workspace/testFunction/src/h.txt","/Users/sarisemac/eclipse-workspace/testFunction/src/output.txt");
        if (args.length<2){
            System.out.println("参数不足两个,请重新运行");
        }else {
            try {
                Date begin = new Date();
                long begintime = begin.getTime();

                WordCount wordCount = new WordCount(args[0], args[1]);
                wordCount.procceed();

                Date end = new Date();
                long endTime = end.getTime();
                long time = endTime - begintime;
                System.out.println("successful,耗时"+time+"毫秒");
            }catch (IOException exception){
                System.out.println("文件读取错误");
                exception.printStackTrace();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}