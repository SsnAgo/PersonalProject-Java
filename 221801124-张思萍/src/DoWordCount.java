import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class DoWordCount {

    //统计字符数，空格，水平制表符，换行符，均算字符
    public static int countChars(String filePath) throws IOException {
        Reader reader=FileIO.getReader(filePath);
        int sum=0;
        int tempChar;
        while ((tempChar=reader.read())!=-1){
            sum++;
        }
        return sum;
    }

    //统计文件的单词总数 单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
    public static int countWords(ArrayList<String> lines) throws IOException{
        int sum=0;
        String line;
        String[] words;
        for (int i=0;i<lines.size();i++){
            line=lines.get(i);
            words=line.split("[^a-zA-Z0-9]+");
            for (int j=0;j<words.length;j++){
                char[] word=words[j].toCharArray();
                if(isValidWord(word)){
                    sum++;
                }
            }
        }
        return sum;
    }

    //统计文件的有效行数：任何包含非空白字符的行，都需要统计。
    public static int countLines(ArrayList<String> lines) throws IOException{
        int sum=0;
        String line;
        for (int i = 0; i < lines.size(); i++) {
            line=lines.get(i);
            line=line.replaceAll("\\s*","");
            if(!(line.equals(""))){
                sum++;
            }
        }
        return sum;
    }

    //是否为有效单词
    public static boolean isValidWord(char[] word){
        if(word.length>=4&&isAlpha(word[0])&&isAlpha(word[1])&&isAlpha(word[2])&&isAlpha(word[3])){
            return true;
        }
        return false;
    }

    //是否为字母
    public static boolean isAlpha(int temp){
        if ((temp >= 97 && temp <= 122) || (temp >= 65 && temp <= 90)) {
            return true;
        } else {
            return false;
        }
    }

}
