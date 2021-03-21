import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {

    /** 保存程序结果状态
     *      1000 : 流程正常
     *      1001 : 参数个数错误
     *      1002 : 文件名或文件类型错误
     *      1003 : 未找到文件
     *      1004 : 文件读写错误
     *      1005 : 其他错误
      */
    private static int statusCode;
    public static void setStatusCode(int code) {
        statusCode = code;
    }
    public static int getStatusCode() {
        return statusCode;
    }


    /**
     * 将程序输出写入文件
     * @param filename 输出文件名
     * @param message 输出字符串
     * @throws IOException
     */
    public static void writeOutputToFile(String filename,String message) throws IOException{
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(message);
        }finally {
            try{
                if (writer != null) writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 判断文件名是否合法
     * @param args
     */
    public static boolean isValidFile(String[] args){

        String[] inputFileSplit = args[0].split("\\.");
        String[] outputFileSplit = args[1].split("\\.");

        if (inputFileSplit.length < 2 || outputFileSplit.length < 2) return false;

        String inputFileExt = inputFileSplit[inputFileSplit.length - 1];
        String outputFileExt = outputFileSplit[outputFileSplit.length - 1];

        return inputFileExt.trim().equals("txt") && outputFileExt.trim().equals("txt");

    }


    /**
     *  主程序
     * @param args 命令行参数数组
     */
    public static void main(String[] args){

        // 若命令行参数不为2个则参数错误
        if (args.length != 2){
            setStatusCode(1001);
            System.out.println("you should  input 2 parameters!");
            return ;
        }


        // 文件名，类型错误
        if (!isValidFile(args)) {
            setStatusCode(1002);
            System.out.println("File name error or File Type Error , only receive txt file !");
            return ;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        // 开始流程
        try {
            // 计算字符
            Counters.countCharacters(inputFile);
            // 计算行数
            List<String> lineList = Utils.getLineList(inputFile);
            Counters.countLines(lineList);
            // 计算单词
            Map<String, Integer> stringIntegerMap = Counters.countWordsAndTransform(lineList);
            stringIntegerMap = Utils.sortMapByNum(stringIntegerMap);
            // 获得输出内容，写出
            StringBuilder sb = Utils.generateOutputString(stringIntegerMap);
            writeOutputToFile(outputFile,sb.toString());
            setStatusCode(1000);
            System.out.println("write data to " + outputFile + " successfully !");
        } catch (FileNotFoundException e) {
            setStatusCode(1003);
            System.out.println("No Such File Found!");
            return;
        } catch (IOException e) {
            setStatusCode(1004);
            System.out.println("Read Or Write File Error!");
            return;
        }
        catch (RuntimeException e) {
            setStatusCode(1005);
            System.out.println(e.getMessage());
            return;
        }


    }
}
