import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

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
            String[] words = line.split("\\W+");//字母数字下划线
            String pattern = "^([A-Za-z]{4}[A-Za-z]*[0-9]*)(\\D*)";
            Pattern r = Pattern.compile(pattern);
            Set<String> wordSet = hashMap.keySet();
            for (int i = 0; i < words.length; i++) {
                Matcher m = r.matcher(words[i]);
                if (m.find()) {
                    String str = m.group(0).toLowerCase();
                    if (wordSet.contains(str)) {
                        Integer num = hashMap.get(str);
                        num++;
                        hashMap.put(str, num);
                    } else {
                        hashMap.put(str, 1);
                    }
                }
            }
        }

        Map<String, Integer> sorted = hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                        LinkedHashMap::new));
        System.out.println("sort map by values: " + sorted);


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