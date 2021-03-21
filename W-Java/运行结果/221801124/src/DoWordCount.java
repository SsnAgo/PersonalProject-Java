import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class DoWordCount {

    //统计字符数，空格，水平制表符，换行符，均算字符
    public static int countChars(String filePath) throws IOException {
        Reader reader = FileIO.getReader(filePath);
        int sum = 0;
        int tempChar;

        while ((tempChar = reader.read()) != -1) {
            sum++;
        }
        return sum;
    }

    //统计文件的单词总数 单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
    public static int countWords(ArrayList<String> lines) throws IOException {
        int sum = 0;
        String line;
        String[] words;

        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            words = line.split("[^a-zA-Z0-9]+");
            for (int j = 0; j < words.length; j++) {
                char[] word = words[j].toCharArray();
                if (isValidWord(word)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    //统计文件的有效行数：任何包含非空白字符的行，都需要统计。
    public static int countLines(ArrayList<String> lines) throws IOException {
        int sum = 0;
        String line;

        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            line = line.replaceAll("\\s*", "");
            if (!(line.equals(""))) {
                sum++;
            }
        }
        return sum;
    }

    //统计文件中各单词的出现次数并排序
    public static String sortWords(ArrayList<String> lines) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        String line;
        String[] words;
        String tempWord;

        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            words = line.split("[^a-zA-Z0-9]+");
            for (int j = 0; j < words.length; j++) {
                char[] word = words[j].toCharArray();
                if (isValidWord(word)) {
                    tempWord = words[j].toLowerCase();
                    if (!map.containsKey(tempWord)) {
                        map.put(tempWord, Integer.valueOf(1));
                    } else {
                        map.put(tempWord, Integer.valueOf(map.get(tempWord).intValue() + 1));
                    }
                }
            }
        }
        //通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, Integer>> mappingList = new ArrayList<>(map.entrySet());
        //通过比较器实现比较排序
        Collections.sort(mappingList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        return printTop10(mappingList);
    }

    public static String printTop10(List<Map.Entry<String, Integer>> maplist) {
        int cnt = 0;
        String outToFile = "";

        for (int i = 0; i < maplist.size(); i++) {
            Map.Entry<String, Integer> e = maplist.get(i);
            outToFile += e.getKey() + ": " + e.getValue() + "\n";
            if (cnt++ >= 9) break;
        }
        return outToFile;
    }


    //是否为有效单词
    public static boolean isValidWord(char[] word) {
        if (word.length >= 4 && isAlpha(word[0]) && isAlpha(word[1]) && isAlpha(word[2]) && isAlpha(word[3])) {
            return true;
        }
        return false;
    }

    //是否为字母
    public static boolean isAlpha(int temp) {
        if ((temp >= 97 && temp <= 122) || (temp >= 65 && temp <= 90)) {
            return true;
        } else {
            return false;
        }
    }
}
