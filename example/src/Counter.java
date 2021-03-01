import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Counter {

    public static String openFilePath = "D:\\IDEA\\PersonalProject-Java\\input.txt" ;
    public static String writeFilePath = "D:\\IDEA\\PersonalProject-Java\\output.txt" ;
    public int countLines = 0;
    public int countChars = 0;
    public int countWords = 0;

    public void open() throws IOException {
        openFile(openFilePath);
    }

    public void write() throws IOException {
        getChars(readFile());
        getLines();
        writeFile(writeFilePath,"test");
    }

    //以StringBuilder读取input.txt
    public String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(openFilePath));
        StringBuilder builder = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            if (c>0&&c<128) {
                builder.append((char) c);
            }
        }
        reader.close();
        return builder.toString();
    }

    //单词数统计
    public void getWords() throws IOException {

        String pattern = "(\\D+\\d+)(\\D*)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher("read123hh");
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
        }

        }

    //行数统计
    public void getLines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(openFilePath));
        while(reader.readLine() != null)
        {
            countLines++;
        }
        reader.close();
    }

    //计数写入output.txt
    public void writeFile(String path, String content){
        File file = new File(path);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.write("characters: " + countChars + "\n");
            writer.write("lines: " + countLines + "\n");
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("文件写入错误");
        }
    }

    public void openFile(String path) throws IOException {
        File file=new File(path);
        if (file.exists()) {
            readFile();
            System.out.println("input.txt文件存在！");
        }
    }

    public void getChars(String str){
        countChars = str.length();
    }
}