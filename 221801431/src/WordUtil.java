import java.io.*;

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

}
