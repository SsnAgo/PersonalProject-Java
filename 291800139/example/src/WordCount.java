import javax.xml.ws.handler.LogicalMessageContext;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCount {

    /**
     * 读取文件
     * @param filePath
     * @return
     */
    public static String readFile(File filePath) {
        StringBuilder result = new StringBuilder();
        try {
            //获取读文件字节流并构造BufferedReader类读取文件
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            int ch = 0;
            //使用read()方法单字节读取
            while ((ch = br.read()) != -1) {
                result.append((char)ch);
            }
            br.close();
            return result.toString();
        } catch (Exception e) {
            System.out.println("Failed to read! <error> :" + e.getMessage());;
        }
        return "";
    }

    /**
     * 写文件
     * @param characters
     * @param words
     * @param lines
     * @param filePath
     * @param wList
     */
    public static void writeFile(int characters, int words, int lines,File filePath,
                                 List<HashMap.Entry<String, Integer>> wList) {
        try {
            //获取写文件字节流并构造BufferedOutputStram类写文件
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath),
                                              "utf-8");
            BufferedWriter bw = new BufferedWriter(osw);
            String text = "characters: " + characters + "\r\n"
                    + "words: " + words + "\r\n"
                    + "lines: " + lines + "\r\n";
            int count = 0;
            //词频前10的换行操作
            for (Map.Entry<String, Integer> entry:wList) {
                count++;
                text +=  entry.getKey() + ": " + entry.getValue();
                if (count <= 9) {
                    text += "\r\n";
                } else {
                    break;
                }
            }
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.out.println("Failed to write! <error>: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        File input =null, output = null;
        String data = null;

       //命令行读文件
        if (args.length < 2) {
            System.out.println("Input error! Please restart the program.");
        }
        else {
            //定位input.txt以及output.txt
            input = new File(args[0]);
            output = new File(args[1]);
            data = readFile(input);//读入的文本内容
        }

        //测试是否成功读到文本内容
        //System.out.println(data);

        //@Test
        //绝对路径测试写入是否成功
//        input = new File("E:\\GitHub\\PersonalProject-Java\\291800139\\example\\src\\input.txt");
//        output = new File("E:\\GitHub\\PersonalProject-Java\\291800139\\example\\src\\output.txt");
//        data = readFile(input);

        //处理文本
        Lib lib = new Lib();
        String dataLen = data.replaceAll("\r\n", "\n");
        int characters = dataLen.length();
        int words = lib.wordCount(data);
        int lines = lib.lineCount(data);
        List<Map.Entry<String,Integer>> wordList = lib.wordSort();

        //输出文本
        writeFile(characters,words,lines,output,wordList);
    }
}