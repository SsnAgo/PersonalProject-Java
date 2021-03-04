import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lib {
    Map<String, Integer> wordCount = new HashMap<>();

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
     * 统计词频
     * @param data
     * @return words
     */
    public int wordCount(String data) {
        int words = 0;




        return words;
    }

    /**
     * 排序词频
     * @return wordList
     */
    public List<HashMap.Entry<String, Integer>> wordSort() {
        List<HashMap.Entry<String, Integer>> wordList = new ArrayList<>();



        return wordList;
    }



}
