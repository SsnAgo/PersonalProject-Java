import java.io.*;
import java.util.*;

public class Lib {
    /**
     * 获得文件内容
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
                content.append(temp);//使用StringBuilder加快字符串拼接速度
                content.append(" ");//针对换行，在每行之间增加一个空格，仅用于统计单词
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
        //return content.toString().replaceAll("\\W"," ");
    }

    /**
     * 字符统计
     * @param filename 输入文件的路径
     * @return int
     */
    public static int countCharacters(String filename) {
        int chars = 0;
        FileReader fr;
        try {
            fr = new FileReader(filename);
            try {
                while (fr.read() != -1) {//按字符读取文件，计算字符数
                    chars++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return chars;
    }

    /**
     * 单词统计
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
     * 输出字符、单词、行数
     * @param output 输出文件的路径
     * @param chars 字符数
     * @param words 单词数
     * @param lines 行数
     */
    public static void printInfo(String output, int chars, int words, int lines) {
        FileWriter fw;
        try {
            fw = new FileWriter(output);
            try {
                fw.write("characters:" + chars + "\n");//输出字符总数
                fw.write("words:" + words + "\n");//输出单词总数
                fw.write("lines:" + lines + "\n");//输出总行数
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("characters:" + chars);//输出字符总数
        System.out.println("words:" + words);//输出单词总数
        System.out.println("lines:" + lines);//输出总行数
    }

    /**
     * 获取词频表
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
     * 对词频表排序并输出
     * @param output 输出文件的路径
     * @param map hashmap的集合
     * @param num 输出数量
     */
    public static void sortFrequency(String output, Map<String, Integer> map, int num) {
        //将HashMap中的包含映射关系的视图entrySet转换为List,然后重写比较器
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet()); //转换为list
        //idea自动转化成lambda表达式
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //输出
        List<String> sameFrequency = new ArrayList<>();//同词频单词表
        int outputCount = 0;//输出统计
        while (outputCount < 10) {
            for (int i = 0; i < list.size(); i++) {
                //如果当前字符词频与下一个不一样，则对当前所有同词频单词排序
                if (!list.get(i).getValue().equals(list.get(i + 1).getValue())) {
                    int currentValue = list.get(i).getValue();//保存当前词频
                    sameFrequency.add(list.get(i).getKey());//将当前单词加入同词频单词表
                    sameFrequency.sort(String::compareTo);//对同词频单词表排序
                    //按字典顺序输出同词频单词
                    for (String s : sameFrequency) {
                        System.out.println(s + ": " + currentValue);
                    }
                    FileWriter fw;
                    try {
                        fw = new FileWriter(output, true);
                        try {
                            for (int j = 0; j < sameFrequency.size(); j++, outputCount++) {
                                if (outputCount >= num)
                                    break;
                                fw.write(sameFrequency.get(j) + ": " + currentValue);
                                if (outputCount != num - 1)//最后一行不输出回车
                                    fw.write("\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        fw.flush();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sameFrequency.clear();
                } else sameFrequency.add(list.get(i).getKey());
                if (outputCount >= num)
                    break;
            }
        }
    }

    /**
     * 单词判定：前四个字符为字母判定为单词
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
}