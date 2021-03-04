import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.xml.stream.FactoryConfigurationError;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    Map<String, Integer> wordsMap = new HashMap<>();

    /**
     * 统计文件行数
     * @param data
     * @return lines
     */
    public int lineCount(String data) {
        int lines = 0;
        boolean flag = false;//标志是否有字符
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) > ' ') {//字符索引至i处
                flag = true;
            } else if (data.charAt(i) == '\n') {
                if (flag) {
                    lines++;
                    flag = false;//置空
                }
            }
        }
        if (flag) {
            lines++;
        }
        return  lines;
    }

    /**
     * 判断字符是否为字母
     * @param charSequence
     * @return boolean值
     */
    public static boolean isLetter(CharSequence charSequence) {
        Pattern pattern = Pattern.compile("[a-z]*");
        Matcher isLet = pattern.matcher(charSequence);
        if (!isLet.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 统计单词总数及各词频
     * @param data
     * @return sum
     */
    public int wordCount(String data) {
        int count = 0;//各单词数量
        int sum = 0;//单词总数
        String lowerData = data.toLowerCase(); //字母转小写
        String anti = "[^0-9a-zA-Z]";//使用正则表达式过滤非字母(数字)字符
        lowerData = lowerData.replaceAll(anti, " ");//筛掉非字母(数字)字符
        //分割文本筛选单词
        StringTokenizer words = new StringTokenizer(lowerData);
        try {
            while (words.hasMoreTokens()) {
                String word = words.nextToken();
                //判断是否为单词
                if (word.length() >= 4 && isLetter(word.subSequence(0, 4))) {
                    sum++;
                    //统计词频
                    if (wordsMap.containsKey(word)) {
                        count = wordsMap.get(word);
                        wordsMap.put(word, ++count);//单词已存在则个数+1
                    } else {
                        wordsMap.put(word, 1);//单词只出现1次
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to count! <error>: " + e.getMessage());
        }
        return sum;
    }

    /**
     * 排序词频
     * @return wordList
     */
    public List<HashMap.Entry<String, Integer>> wordSort() {
        List<HashMap.Entry<String, Integer>> wordList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            wordList.add(entry); //将map中的元素放入list中
        }
        //使用比较器排序
        Comparator<Map.Entry<String, Integer>> cmp = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey()); //值相同时按键返回字典序
                }
                return o2.getValue() - o1.getValue(); //字典序靠前排在前面
            }
        };
        wordList.sort(cmp);
        return wordList;
    }
}
