import java.io.*;
import java.util.*;


/**
 * 编写者：221801435
 * 主要完成四个个功能：
 * 1. 统计Ascii字符数
 * 2. 统计单词数
 * 3. 统计行数
 * 4. 统计最多的十个单词
 */
public class Lib {

    /**
     * 编写者：221801435
     * 功能：统计文件的Ascii字符数
     * @param file_info 文件信息字符串
     * @return Ascii字符数
     */
    public int getAsciiCount(String file_info){
        int counter = 0;
        for (int i=0;i<file_info.length();i++){
            int c = (int)file_info.charAt(i);
            if (c>=0&&c<=127){
                counter++;
            }
        }
        return counter;
    }

    /**
     * 编写者：221801435
     * 功能：统计文件的非空行数
     * @param file_path 文件路径
     * @return int 文件行数
     */
    public int getLinesCount(String file_path){
        String file_info = fileToString(file_path);

        BufferedReader reader = null;
        int counter = 0;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file_path)));
            String line = null;
            while((line=reader.readLine())!=null){
                //判断改行是否非空
                if (line.length()>0&&!line.matches("\\s+")){
                    counter++;
                }
            }
        }catch (IOException e){
            System.err.println(file_path+"文件打开失败");
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    System.err.println(file_path+"文件关闭失败");
                }
            }
        }
        return counter;
    }

    /**
     * 编写者：221801435
     * @param file_info 文件信息字符串
     * @return int 文件单词数
     */
    public int getWordsCount(String file_info){
        //先遍历一遍，将所有非字母数字的符号都换成空格符
        StringBuilder builder = new StringBuilder(file_info);
        for (int i=0;i<builder.length();i++){
            char c = builder.charAt(i);
            if (!Character.isDigit(c)&&!((c>='a'&&c<='z'||c>='A'&&c<='Z'))){
                builder.setCharAt(i,' ');
            }
        }
        //将其按空格拆分
        String []words = builder.toString().split("\\s+");
        //判断是否是合法字符,统计单词数量
        int counter = 0;
        for(int i=0;i<words.length;i++){
            if (isWord(words[i])){
                counter++;
            }
        }
        return counter;
    }

    /**
     * 编写者：221801435
     * 功能：统计文件中出现次数最多的十个单词
     * @param file_info 文件内容字符串
     * @return Map集合，key为string类型，代表单词内容；integer是单词出现次数
     */
    public List<Map.Entry<String,Integer>> getMostFrequentlyWords(String file_info){
        //先遍历一遍，将所有非字母数字的符号都换成空格符
        StringBuilder builder = new StringBuilder(file_info);
        for (int i=0;i<builder.length();i++){
            if (!Character.isDigit(builder.charAt(i))&&!Character.isLetter(builder.charAt(i))){
                builder.setCharAt(i,' ');
            }
        }
        //将其按空格拆分
        String []words = builder.toString().split(" ");
        //保存每个单词和其出现的频率
        Map<String,Integer> words_map = new HashMap<>();
        //该数组用于排序
        List<Map.Entry<String,Integer>> words_arr = null;
        //开始统计
        for (int i=0;i<words.length;i++){
            //首先得是合法单词
            if (isWord(words[i])){
                //判断是否在Map中出现过，没有出现则加1,map中只保存小写形式
                String current = words[i].toLowerCase();
                if (words_map.containsKey(current)){
                    int num = words_map.get(current);
                    words_map.put(current,num+1);
                }else{
                    words_map.put(current,1);
                }
            }
        }

        //排序
        words_arr = new ArrayList<>(words_map.entrySet());
        words_arr.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue()!=o2.getValue()){
                    return o1.getValue().compareTo(o2.getValue());
                }else{
                    return -1*o1.getKey().compareTo(o2.getKey());
                }
            }
        });

        //从大到小，所以要翻转
        Collections.reverse(words_arr);
        return words_arr;
    }

    /**
     * 编写者: 221801435
     * 功能: 判断字符串是否是单词,单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写
     * @param word 单词字符串
     * @return 判断结果，是单词返回True，否则返回False
     */
    private boolean isWord(String word){
        if (word.length()<4){
            return false;
        }
        //不区分大小写
        String test = word.toLowerCase();
        //前4位是字母
        for(int i=0;i<4;i++){
            char c = test.charAt(i);
            if (!(c>='a'&&c<='z')){
                return false;
            }
        }
        //后跟字母数字符号
        for(int i=3;i<word.length();i++){
            if(!Character.isDigit(test.charAt(i))&&!Character.isLetter(test.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public String fileToString(String file_path){
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file_path)));
            int c;
            while ((c=reader.read())!=-1){
                builder.append((char)c);
            }
        }catch (FileNotFoundException e){
            System.err.println(file_path+"文件不存在");
        }catch (IOException e){
            System.err.println(file_path+"文件打开失败");
        }
        finally {
            if (reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    System.err.println(file_path+"文件关闭失败");
                }
            }
        }
        return builder.toString();
    }
}
