import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class WordCount {

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            List<String> wordList = new ArrayList();//单词列表
            String line = null;
            //一行一行读取文件
            while((line = br.readLine()) != null){
                //分割符：空格，非字母数字符号,以分割符来分割出单词
                String[] words = line.split("[^a-zA-Z0-9]");
                //单词：至少以4个英文字母开头
                Pattern pattern = Pattern.compile("[a-zA-Z]{4}[a-zA-Z0-9]*");
                for (String word : words){
                    if (word.length() >= 4) {
                        //正则表达式判断单词是否合法
                        if (pattern.matcher(word).matches()){
                            //统一转小写
                            wordList.add(word.toLowerCase());
                        }
                    }
                }
            }
            br.close();

            for (String word : wordList){
                System.out.println(word);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
