import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isLetter;

/*
  类名：WordCount
  作者:黄明亮 日期:2021-3-4
  模块描述: 实现读取一个文件，获取该文件字符数，单词总数，有效行数，单词的出现次数函数，并输出至output文件的功能
  函数列表: countCharacters(File file)
*/
public class WordCount{
    public static void main(String[] args){
        //File inputFile = new File(args[0]);
        File inputFile = new File("src\\input.txt");
        File OUTPUT_File = new File("output.txt");
        outputFile(inputFile, OUTPUT_File);
    }
    /*
     函数名：   outputFile(File inputFile, File outputFile)
     函数描述:  执行函数获取返回值并输出文件
     输入:      输入文件路径，输出文件路径
     返回值:
     其他说明:  函数包括：计算文件字符数，单词总数，有效行数，单词的出现次数函数
    */
    public static void outputFile(File inputFile, File outputFile){
        int PRINT_WORDS_NUMBER = 10;

        int characters = countCharacters(inputFile);
        System.out.println(characters);
        int words = countTotalWords(inputFile);
        System.out.println(words);
        int lines = countValidLines(inputFile);
        System.out.println(lines);
        List<Map.Entry<String, Integer>> list = countWords(inputFile);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getKey()+":"+list.get(i).getValue());
        }

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
            String content = "characters: " + characters + '\n';
            content += "words: " + words + "\n";
            content += "lines: " + lines + "\n";
            for(int i = 0; i < PRINT_WORDS_NUMBER && i < list.size(); i++)
                content += list.get(i).getKey() + ": " + list.get(i).getValue() + "\n";

            bufferedOutputStream.write(content.getBytes());

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
     函数名：   countCharacters(File inputFile)
     函数描述:  统计文件的字符数
     输入:      输入文件路径
     返回值:    文件的字符数
     其他说明:  只需要统计Ascii码，汉字不需考虑空格，水平制表符，换行符，均算字符
    */
    public static int countCharacters(File inputFile){
        int characters = 0;

        try {
            BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(inputFile));
            while((bufferedInputStream.read())!=-1){
                characters ++;
            }

            bufferedInputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return characters;
    }
    /*
         函数名：   countTotalWords(File inputFile)
         函数描述:  统计文件的合法单词数
         输入:      输入文件路径
         返回值:    文件的单词数
         其他说明:  英文字母： A-Z，a-z
                   字母数字符号：A-Z， a-z，0-9
                   分割符：空格，非字母数字符号
                   例：file123是一个单词， 123file不是一个单词。file，File和FILE是同一个单词
    */
    public static int countTotalWords(File inputFile){
        int total = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            String line = "";

            while((line = bufferedReader.readLine()) != null){
                String[] words = line.split("[^a-zA-Z0-9]");

                for(int i = 0; i < words.length; i++)
                {
                    if(isValidWord(words[i].toCharArray()))
                        total++;
                }
            }

            bufferedReader.close();
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return total;
    }
    /*
         函数名：   isValidWord(char[] words)
         函数描述:  判断单词合法性
         输入:      字符数组
         返回值:    布尔值
         其他说明:  至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
    */
    public static boolean isValidWord(char[] words) {
        if(words.length >= 4)
        if(isLetter(words[0]) && isLetter(words[1]) && isLetter(words[2]) && isLetter(words[3])) {
            return true;
        }
        return false;
    }
    /*
         函数名：   countValidLines(File inputFile)
         函数描述:  非空白行统计
         输入:      输入文件路径
         返回值:    非空白行数
         其他说明:  任何包含非空白字符的行，都需要统计。
    */
    public static int countValidLines(File inputFile){
        int lines = 0;
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));

            while((line = bufferedReader.readLine()) != null){
                if(!line.equals(""))
                    lines++;
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
    /*
         函数名：   List<Map.Entry<String, Integer>> countWords
         函数描述:  统计文件中各单词的出现次数（对应输出接下来10行），最终只输出频率最高的10个
         输入:      输入文件路径
         返回值:    排序好的list
         其他说明:  频率相同的单词，优先输出字典序靠前的单词。
                   输出的单词统一为小写格式
    */
    public static List<Map.Entry<String, Integer>> countWords(File inputFile){
        Map<String, Integer> map = new TreeMap<String, Integer>();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while((line = bufferedReader.readLine())!=null){
                String string = line.toLowerCase();
                Pattern pattern = Pattern.compile("[a-zA-Z]{4}([a-zA-Z0-9])*");
                Matcher matcher = pattern.matcher(string);
                String word = "";
                Integer num = null;

                while(matcher.find()) {
                    word = matcher.group();
                    if(map.containsKey(word)) {
                        num = map.get(word);
                        num += 1;
                    }else {
                        num = 1;
                    }
                    map.put(word, num);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());

        Collections.sort(list, new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int flag = o2.getValue() - o1.getValue();
                if(flag == 0){
                    flag = (o1.getKey()).compareTo(o2.getKey());
                }
                return flag;
            }
        });

        return list;
    }
}