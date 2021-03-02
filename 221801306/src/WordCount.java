import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCount {
    public static void main(String[] args) {
        String filename = "E:/input1.txt";
        String content = "";//文本内容
        Map map = new HashMap<>();

        content = GetFile(filename);//输入文件

        //System.out.println(CountCharacters(filename));//输出字符总数
        System.out.println("words:" + CountWords(content));//输出单词总数
        System.out.println("lines:" + CountLines(filename));//输出总行数

        map = CountFrequency(content);//词频统计
        SortFrequency(map, 10);//词频排序和输出
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
                content.append(" ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    /*private static int CountCharacters(String filename) {
        int ch=0;
        FileReader fr = null;
        try {
            fr = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }*/

    public static int CountWords(String content) {
        int num = 0;
        StringTokenizer st = new StringTokenizer(content, " ,.!?\"'");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            if (Isword(word) && word.length() >= 4) {
                num++;
            } else {
            }
        }
        return num;
    }

    private static int CountLines(String filename) {
        int lines = 0;
        FileReader fr = null;
        try {
            fr = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fr != null;
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
        return lines;
    }

    public static Map CountFrequency(String content) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        StringTokenizer st = new StringTokenizer(content, " ,.!?\"'");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            word.toLowerCase();
            if (Isword(word) && word.length() >= 4) {
                if (map.get(word) != null) {
                    int value = ((Integer) map.get(word)).intValue();
                    value++;
                    map.put(word, new Integer(value));
                } else {
                    map.put(word, new Integer(1));
                }
            } else
                continue;
        }
        return map;
    }

    private static void SortFrequency(Map map, int num) {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet()); //转换为list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        if (num > list.size()) {
            num = list.size();
        }
        for (int i = 0; i < num; i++) {
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
    }

    private static boolean Isword(String word) {//首字符为字母判定为单词
        char first = word.charAt(0);
        if ((first >= 'a' && first <= 'z'))
            return true;
        if ((first >= 'A' && first <= 'Z'))
            return true;
        if (first >= '0' && first <= '9')
            return false;
        return false;
    }
}