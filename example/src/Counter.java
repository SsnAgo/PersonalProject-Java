import java.io.*;

public class Counter {

    public static String openFilePath = "D:\\IDEA\\PersonalProject-Java\\input.txt" ;
    public static String writeFilePath = "D:\\IDEA\\PersonalProject-Java\\output.txt" ;
    public int countLines;
    public int countChars;
    public int countWords;

    public void open() {
        openFile(openFilePath);
    }
    public void write() {
        writeFile(writeFilePath,"test");
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

    public void openFile(String path) {
        File file=new File(path);
        if (file.exists()) {
            System.out.println("input.txt文件存在！");
        }
    }

}


