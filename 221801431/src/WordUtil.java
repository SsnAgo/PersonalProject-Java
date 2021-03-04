import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordUtil {

    //统计字符数
    public String countChar(String filename) throws IOException {
        //初始化字符数
        int charnum=0;
        File file=new File("../"+filename);
        int x=-1;
        FileReader fReader=new FileReader(file);
        //逐个字符读取文件
        while((x=fReader.read())!=-1) {
            char a=(char)x;
            /*if(a!='\n'&&a!='\r')
            {
                charnum++;
            }*/
            charnum++;
        }
        String result=""+charnum;
        //关闭流
        fReader.close();
        return result;
    }

    //统计单词总数
    public String countWord(String filename) throws IOException{
        //初始化单词数
        int wordnum=0;
        File file =new File("../"+filename);
        //缓冲区
        BufferedReader bReader;
        bReader=new BufferedReader(new FileReader(file));
        String temp = "";

        //按行读文件，用正则表达式分割
        while ((temp = bReader.readLine()) != null) {
            String[] words = temp.split("[^a-zA-Z0-9]+");
            for (String word : words) {
                word.toLowerCase();
                //碰到符合条件的单词，单词数+1
                if (word.matches("[a-zA-Z]{4}[a-zA-Z0-9]*") ) {
                    wordnum++;
                }
            }
        }

        String result=""+wordnum;//保存结果
        bReader.close();//关闭流
        return result;//返回结果
    }
}
