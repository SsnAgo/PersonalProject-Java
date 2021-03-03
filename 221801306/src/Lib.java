import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Lib {
    /**
     * 获得文件内容
     *
     * @param filename 输入文件的路径
     * @return String
     */
    public static String getFile(String filename) {
        StringBuilder content = new StringBuilder();
        FileReader fr;
        try {
            fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            while (true) {
                try {
                    if ((temp = br.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                content.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
        //return content.toString().replaceAll("\\W"," ");
    }

    /**
     * 字符统计
     *
     * @param content 文件内容
     * @return int
     */
    public static int countCharacters(String content) {
        return content.length();
    }

    /**
     * 单词统计
     *
     * @param content 文章内容
     * @return int
     */
    public static int countWords(String content) {
        int words = 0;
        StringTokenizer st = new StringTokenizer(content, " ,.!?\"'\n\t\r");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            if (IsWord(word) && word.length() >= 4) {//根据作业要求，判断截取字符串是否为单词
                words++;
            }
        }
        return words;
    }

    /**
     * 行数统计
     *
     * @param filename 输入文件的路径
     * @return int
     */
    public static int countLines(String filename) {
        int lines = 0;
        FileReader fr;
        try {
            fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            while (true) {
                try {//按行读取，记录行数
                    if ((temp = br.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!"".equals(temp)) {//检查是否为空行，不记录空行
                    lines++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 获取输入文件的信息
     *
     * @param chars     字符数
     * @param words     单词数
     * @param lines     总行数
     * @param frequency 词频
     * @return String
     */
    public static String answerBuilder(int chars, int words, int lines, String frequency) {
        System.out.println("characters:" + chars);//输出字符总数
        System.out.println("words:" + words);//输出单词总数
        System.out.println("lines:" + lines);//输出总行数
        System.out.println(frequency);
        return "characters:" + chars +
                "\nwords:" + words +
                "\nlines:" + lines + "\n" +
                frequency;
    }

    /**
     * 获取词频表
     *
     * @param content 文章内容
     * @return Map 词频hashmap集合
     */
    public static Map<String, Integer> countFrequency(String content) {
        Map<String, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(content, " ,.!?\"'\n\t\r");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            word = word.toLowerCase();//将单词全部转为小写
            if (IsWord(word) && word.length() >= 4) {//根据作业要求，判断截取字符串是否为单词
                if (map.get(word) != null) {//如果单词有记录，则将值+1
                    int value = map.get(word);
                    value++;
                    map.put(word, value);
                } else {//如果单词无记录，则新增单词，值为1
                    map.put(word, 1);
                }
            }
        }
        return map;
    }

    /**
     * 对词频表排序
     *
     * @param map hashmap的集合
     * @param num 输出数量
     */
    public static String sortFrequency(Map<String, Integer> map, int num) {
        StringBuilder result = new StringBuilder();
        //将HashMap中的包含映射关系的视图entrySet转换为List,然后重写比较器
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet()); //转换为list
        //idea自动转化成lambda表达式
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //输出
        List<String> sameFrequency = new ArrayList<>();//同词频单词表
        int outputCount = 0;//输出统计
        for (int i = 0; i < list.size() && outputCount < num; i++) {
            //如果当前字符词频与下一个不一样，则对当前所有同词频单词排序
            if (!list.get(i).getValue().equals(list.get(i + 1).getValue())) {
                sameFrequency.add(list.get(i).getKey());//将当前单词加入同词频单词表
                sameFrequency.sort(String::compareTo);//对同词频单词表排序
                //按字典顺序记录同词频单词
                for (String s : sameFrequency) {
                    String temp = s + ": " + list.get(i).getValue();
                    result.append(temp);
                    outputCount++;
                    if (outputCount != num) {
                        result.append("\n");
                    } else
                        break;
                }
                sameFrequency.clear();
            } else sameFrequency.add(list.get(i).getKey());
        }

        return result.toString();
    }

    /**
     * 单词判定：前四个字符为字母判定为单词
     *
     * @param word 单词
     * @return boolean
     */
    public static boolean IsWord(String word) {
        if (word.length() < 4)
            return false;
        char letter;
        for (int i = 0; i < 4; i++) {
            letter = word.charAt(i);
            if (!(letter >= 'a' && letter <= 'z'))
                return false;
        }
        return true;
    }

    /**
     * 向文件输出内容
     * @param output 输出文件的路径
     * @param answerBuilder 输出内容
     */
    public static void outputInfo(String output, String answerBuilder) {
        File outputFile = new File(output);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), StandardCharsets.UTF_8));
            writer.write(answerBuilder);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件写入错误");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("输出流关闭异常");
                }
            }
        }
    }
}