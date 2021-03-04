import javax.xml.ws.handler.LogicalMessageContext;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCount {
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
                text += "<" + entry.getKey() + ">: " + entry.getValue();
                if (count <= 9) {
                    text += "\r\n";
                } else {
                    break;
                }
            }
            bw.write(text.getBytes().toString(), 0, text.getBytes().length);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.out.println("Failed to write! <error>: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        File input, output;
        String data;
        if (args.length < 2) {
            System.out.println("Input error! Please restart the program.");
        }
        else {
            //定位input.txt以及output.txt
            input = new File(args[0]);
            output = new File(args[1]);
            data = readFile(input);//读入的文本内容
        }
        Lib lib = new Lib();

    }

}