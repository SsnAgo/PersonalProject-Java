import java.io.*;
import java.util.*;

public class WordCount {
    public static void main(String[] args) {
        String filename = "E:/JavaTest/input1.txt";
        String output="E:/JavaTest/output.txt";
        String content;//文本内容
        int num = 10;//输出词频最高的10个单词

        content = GetFile(filename);//输入文件，仅用于统计单词

        FileWriter fw;
        try {
            fw = new FileWriter(output);
            try {
                fw.write("characters:" + CountCharacters(filename)+"\n");
                fw.write("words:" + CountWords(content)+"\n");
                fw.write("lines:" + CountLines(filename)+"\n");
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("characters:" + CountCharacters(filename));//输出字符总数
        System.out.println("words:" + CountWords(content));//输出单词总数
        System.out.println("lines:" + CountLines(filename));//输出总行数

        Map<String, Integer> map;
        map = CountFrequency(content);//词频统计
        SortFrequency(map, num);//词频排序和输出
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
                content.append(temp);
                content.append(" ");//针对换行，在每行之间增加一个空格，仅用于统计单词
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private static int CountCharacters(String filename) {
        int ch = 0;
        FileReader fr;
        try {
            fr = new FileReader(filename);
            try {
                while (fr.read() != -1) {
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
        StringTokenizer st = new StringTokenizer(content, " ,.!?\"'");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            if (IsWord(word) && word.length() >= 4) {
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
                try {
                    if ((temp = br.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!"".equals(temp)) {
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

        StringTokenizer st = new StringTokenizer(content, " ,.!?\"'");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            word = word.toLowerCase();
            if (IsWord(word) && word.length() >= 4) {
                if (map.get(word) != null) {
                    int value = map.get(word);
                    value++;
                    map.put(word, value);
                } else {
                    map.put(word, 1);
                }
            }
        }
        return map;
    }

    private static void SortFrequency(Map<String, Integer> map, int num) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet()); //转换为list
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        if (num > list.size()) {
            num = list.size();
        }
        for (int i = 0; i < num; i++) {
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
    }

    private static boolean IsWord(String word) {//首字符为字母判定为单词
        char first = word.charAt(0);
        if ((first >= 'a' && first <= 'z'))
            return true;
        return first >= 'A' && first <= 'Z';
    }
}