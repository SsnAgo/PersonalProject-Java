import java.io.*;
import java.util.HashMap;
import java.util.Map;
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
        Scanner scanner=new Scanner(new File(openFilePath));
        HashMap<String,Integer> hashMap=new HashMap<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //字母数字下划线
            String[] words = line.split("\\W+");
            String pattern = "([A-Za-z]{4}[A-Za-z]*[0-9]*)(\\D*)";
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);
            Set<String> wordSet = hashMap.keySet();
            for (int i = 0; i < words.length; i++) {
                Matcher m = r.matcher(words[i]);
                System.out.println(words[i]);
                if (m.find()) {
                    if (wordSet.contains(m.group(1))) {
                        Integer num = hashMap.get(words[i]);
                        num++;
                        hashMap.put(words[i], num);
                    } else {
                        hashMap.put(m.group(1), 1);
                    }
                }
            }

        }

        for(HashMap.Entry<String, Integer> entry : hashMap.entrySet()){
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
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