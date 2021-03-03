import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    /**
     * @description     读取对应文件
     * @param filePath  文件路径
     * @return          文件中字符拼成的字符串
     * @throws IOException
     */
    public static String readFile(String filePath) throws IOException {
        BufferedReader reader = null;
        StringBuilder str = new StringBuilder();
        int ch = 0;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((ch = reader.read()) != -1) {
                str.append((char)ch);
            }
        } catch (FileNotFoundException e) {
            System.out.println("未找到需读入文件：" + filePath);
            e.printStackTrace();
        } finally {
            reader.close();
        }

        return str.toString();
    }

    /**
     * @description      写入对应文件
     * @param OutputPath 写入文件的路径
     * @param charsNum   读入文件中的字符总数
     * @param wordsNum   读入文件中的单词总数
     * @param linesNum   读入文件中的有效行数
     * @param wordMap    存放单词个数的hashMap
     * @throws IOException
     */
    public static void writeFile(String OutputPath, int charsNum, int wordsNum, int linesNum
            , HashMap<String, Integer> wordMap) throws IOException {
        BufferedWriter writer = null;
        int cnt = 0;
        StringBuilder str = new StringBuilder("characters: " + charsNum + '\n' + "words: " + wordsNum + '\n'
                + "lines: " + linesNum + '\n');
        List<HashMap.Entry<String, Integer>> sortedList = Lib.getSortedList(wordMap);

        for(HashMap.Entry<String,Integer> entry:sortedList) {
            str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            cnt++;
            if (cnt >= 10) break;
        }

        try {
            writer = new BufferedWriter(new FileWriter(OutputPath));
            writer.write(str.toString());
        } catch (FileNotFoundException e) {
            System.out.println("未找到需写入文件：" + OutputPath);
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    /**
     * @description 计算字符数
     * @param str   文件字符串
     * @return      字符数
     */
    public static int countCharacters(String str) {
        return str.length();
    }

    /**
     * @description 统计有效行数
     * @param str   文件字符串
     * @return      有效行数
     */
    public static int countLines(String str) {
        int cnt = 0;
        String[] strLine = str.split("\r\n|\n");

        for (String validLine : strLine) {
            if (!validLine.replaceAll("\r|\n", "").trim().equals("")) {
                cnt++;
            }
        }

        return cnt;
    }

    /**
     * @description   统计单词数，并将各个单词存入hashMap
     * @param str     文件字符串
     * @param wordMap 用来存放单词的hashMap
     * @return        单词数
     */
    public static int countWords(String str, HashMap<String, Integer> wordMap) {
        int cnt = 0;
        Pattern pattern = Pattern.compile("^[a-z]{4}[a-z0-9]*");
        Matcher matcher = null;
        String[] wordStrTmp = str.split("[^a-zA-Z0-9]");

        for (String word : wordStrTmp) {
            word = word.toLowerCase();
            matcher = pattern.matcher(word);

            if (!word.equals("") && matcher.find()) {
                cnt++;
                if(wordMap.containsKey(word)) {
                    wordMap.put(word, wordMap.get(word) + 1);
                }
                else {
                    wordMap.put(word, 1);
                }
            }
        }

        return cnt;
    }

    /**
     * @description   对wordMap里存放的单词按次数进行排序
     * @param wordMap 存放单词的hashMap
     * @return        排序完的list
     */
    public static List<HashMap.Entry<String, Integer>> getSortedList(HashMap<String, Integer> wordMap) {
        List<HashMap.Entry<String, Integer>> list =
                new ArrayList<HashMap.Entry<String, Integer>>(wordMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
                if(o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        return list;
    }

}
