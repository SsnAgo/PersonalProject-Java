import java.io.*;

public class Counter {

    public static String openFilePath = "D:\\IDEA\\PersonalProject-Java\\input.txt" ;
    public static String writeFilePath = "D:\\IDEA\\PersonalProject-Java\\output.txt" ;
    public int countLines;
    public int countChars;
    public int countWords;

    public void open() throws IOException {
        openFile(openFilePath);
    }
    public void write() throws IOException {
        getChars(readFile());
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

    //计数写入output.txt
    public void writeFile(String path, String content){
        File file = new File(path);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.write("characters: " + countChars + "\n");
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