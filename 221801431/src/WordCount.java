import java.io.*;

public class WordCount {

    public static void main(String[] args) throws IOException{

        String input = args[0];
        String output = args[1];
//        System.out.println(input+","+output);
//        System.out.println("1111");

        File inputfile = new File("../"+input);
        //判断文件是否存在？
        if (!inputfile.exists()) {
            System.out.println(inputfile + "文件未找到");
            System.exit(0);
        }else if (!inputfile.isFile()) {
            System.out.println("文件读取异常");
            System.exit(0);
        }else{
            WordUtil wu = new WordUtil();

            String result = "";

            //统计文件的字符数（对应输出第一行）：
            result+="characters: "+wu.countChar(input)+"\n";
            //统计文件的单词总数（对应输出第二行）
            result+="words: "+wu.countWord(input)+"\n";
            //统计文件的有效行数（对应输出第三行）
            result+="lines: "+wu.countLine(input)+"\n";
            //统计文件中各单词的出现次数（对应输出接下来10行）


            //结果输出至文件
            outputToTxt(output,result);
        }
    }



    //输出结果到文件
    public static void outputToTxt(String outout, String result) throws IOException
    {
        File file=new File("../"+outout);//定义文件位置
        if(!file.exists())//判断文件是否存在，不存在则直接创建新文件
        {
            file.createNewFile();
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        writer.write(result);

        writer.close();
    }
}
