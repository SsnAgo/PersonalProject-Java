import java.io.*;

public class FileIO {
    /**
     * 对文件进行读操作
     * @param inputFile
     * @return BufferedReader
     */
    public static BufferedReader readFromFile(File inputFile){
        BufferedReader bufferedReader=null;
        try {
            bufferedReader=new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
            e.printStackTrace();
        }
        return bufferedReader;
    }

    /**
     * 将内容写入输出文件
     * @param outputFile
     * @param content
     */
    public static void writeToFile(File outputFile, String content){
        try {
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(new FileOutputStream(outputFile));
            BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
