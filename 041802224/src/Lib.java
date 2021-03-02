import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class Lib {

    Lib(String openFilePath, String writeFilePath){
        this.openFilePath = openFilePath;
        this.writeFilePath = writeFilePath;
    }
    public  String openFilePath = "D:\\IDEA\\PersonalProject-Java\\input.txt" ;
    public  String writeFilePath = "D:\\IDEA\\PersonalProject-Java\\output.txt" ;
    public Map<String,Integer> hashMap;
    public int countLines = 0;
    public int countChars = 0;
    public int countWords = 0;

    public void open() {
        try {
            openFile(openFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            getChars(readFile());
            getLines();
            getWords();
            writeFile(writeFilePath,"test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //以StringBuilder读取文件
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

        hashMap=new HashMap<>();
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
                .sorted(Map.Entry.<String, Integer> comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                        LinkedHashMap::new));
        //System.out.println("sort map by values: " + sorted);
        hashMap = sorted;

    }

    //行数统计
    public void getLines() throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(openFilePath));
//        while(reader.readLine() != null)
//        {
//            countLines++;
//        }
//        reader.close();
        String pattern = "(^|\\n)\\s*\\S+";
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(openFilePath));
        while((line = reader.readLine())!= null)
        {
            line = line.trim();
            if(line.matches(pattern)){
                countLines++;
            }
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
            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                Integer integer = entry.getValue();
                String str = entry.getKey();
                writer.write(str + ": " + integer + "\n");
            }
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
            System.out.println("文件打开成功");
        }
        else{
            System.out.println("文件打开失败");
        }
    }
    public void getChars(String str){
        countChars = str.length();
    }
}