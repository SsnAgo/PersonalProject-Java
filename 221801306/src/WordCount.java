import java.io.*;
import java.util.*;

public class WordCount {
    public static void main(String[] args) {
        String filename = "E:/JavaTest/01-01-爱情与金钱.txt";
        String output = "E:/JavaTest/output.txt";
        String content;//文本内容
        Map<String, Integer> map;
        int num = 10;//输出词频最高的10个单词

        content = GetFile(filename);//输入文件，仅用于统计单词
        //输出字符、单词、行数统计
        PrintInfo(output, CountCharacters(filename), CountWords(content), CountLines(filename));

        map = CountFrequency(content);//词频统计
        SortFrequency(output, map, num);//词频排序和输出
    }

    private static void PrintInfo(String output, int chars, int words, int lines) {
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

    public static String GetFile(String filename) {
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
//        return content.toString().replaceAll("\\W"," ");
    }

    private static int CountCharacters(String filename) {
        int ch = 0;
        FileReader fr;
        try {
            fr = new FileReader(filename);
            try {
                while (fr.read() != -1) {//按字符读取文件，计算字符数
                    ch++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ch;
    }

    public static int CountWords(String content) {
        int num = 0;
        StringTokenizer st = new StringTokenizer(content, " \t\n\r");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            if (IsWord(word) && word.length() >= 4) {//根据作业要求，判断截取字符串是否为单词
                num++;
            }
        }
        return num;
    }

    private static int CountLines(String filename) {
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

    public static Map<String, Integer> CountFrequency(String content) {
        Map<String, Integer> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(content, " \t\n\r");
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

    private static void SortFrequency(String output, Map<String, Integer> map, int num) {
        //将HashMap中的包含映射关系的视图entrySet转换为List,然后重写比较器
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet()); //转换为list
        //idea自动转化成lambda表达式
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        //输出
        if (num > list.size()) {
            num = list.size();
        }
        FileWriter fw;
        try {
            fw = new FileWriter(output, true);
            try {
                for (int i = 0; i < num - 1; i++) {
                    fw.write(list.get(i).getKey() + ": " + list.get(i).getValue() + "\n");
                }
                //最后一行末尾不加回车
                if (num > 0) fw.write(list.get(num - 1).getKey() + ": " + list.get(num - 1).getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < num; i++) {
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
    }

    private static boolean IsWord(String word) {//前四个字符为字母判定为单词
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