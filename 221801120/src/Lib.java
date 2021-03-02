import java.io.*;

public class Lib {
    private String inputFile;
    private String outputFile;
    private String content;
    private int charsNum;

    /**
     * 构造函数
     * @param inputFile 读取文件地址
     * @param outputFile 写入文件地址
     */
    public Lib(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     * 读取文件内容
     */
    public void readFile() throws IOException {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try{
            reader = new BufferedReader(new FileReader(inputFile));
            int num = 0;
            char ch;
            while ((num = reader.read()) != -1) {
                // UTF-8中'\n'对应编码int值为13
                if (num != 13) {
                    ch = (char) num;
                    builder.append(ch);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (reader != null)
                reader.close();
        }
        content = builder.toString();
    }

    /**
     * 写入文件
     */
    public void writeFile() throws IOException {
        countCharsNum();
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write("characters: " + charsNum + "\n");
            writer.write(content);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (writer != null)
                writer.close();
        }
    }

    /**
     * 统计字符数
     */
    public void countCharsNum(){
        charsNum = content.length();
    }
}
