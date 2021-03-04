import java.io.*;

public class WordCount{
    public static void main(String[] args){
        //File inputFile = new File(args[0]);
        File inputFile = new File("src\\input.txt");
        File OUTPUT_File = new File("output.txt");
        outputFile(inputFile, OUTPUT_File);
    }
    /*
     函数名：outputFile(File inputFile, File outputFile)
     函数描述:  执行函数获取返回值并输出文件
     输入:      输入文件路径，输出文件路径
     返回值:
     其他说明:  函数包括：计算文件字符数，单词总数，有效行数，单词的出现次数函数
    */
    public static void outputFile(File inputFile, File outputFile){
        int characters = countCharacters(inputFile);
        System.out.println(characters);

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
            String content = "characters: " + characters + '\n';
            bufferedOutputStream.write(content.getBytes());

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
     函数名：countCharacters(File file)
     函数描述:  统计文件的字符数
     输入:      文件路径
     返回值:    文件的字符数
     其他说明:  只需要统计Ascii码，汉字不需考虑空格，水平制表符，换行符，均算字符
    */
    public static int countCharacters(File file){
        int characters = 0;

        try {
            BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(file));
            while((bufferedInputStream.read())!=-1){
                characters ++;
            }
            bufferedInputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return characters;
    }
}