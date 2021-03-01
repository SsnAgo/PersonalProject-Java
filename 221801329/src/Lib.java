import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class Lib {
    private static String FLITER_REGEX = "[^a-zA-Z0-9]";
    private static String WORD_REGEX = "[a-z]{4}[a-z0-9]*";
    /**
     * @description  统计文件中ASCII数量
     * @param str    待统计文件
     * @return count ASCII数量
     */
    public static int countChars(String str) throws IOException {
        File file = new File(str);
        FileChannel fileChannel = new RandomAccessFile(file,"r").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1 << 15);
        byte[] temp = new byte[0];
        int count = 0;
        while(fileChannel.read(byteBuffer) != -1) {
            byte[] bs = new byte[byteBuffer.position()];
            byteBuffer.flip();
            byteBuffer.get(bs);
            byteBuffer.clear();
            for (int i = 0; i < bs.length; i++) {
                count++;
            }
        }
        return count;
    }
    /**
     * @description   将字符串中含有的单词正则处理并记录至map中
     * @param line    待处理字符串
     * @param wordMap 用于存放统计结果的HashMap
     * @return cnt    当前字符串含有单词数
     */
    public static int countWords(String line, HashMap<String,Integer> wordMap) {
        line = line.toLowerCase().replaceAll(FLITER_REGEX," ");
        int cnt = 0;
        StringTokenizer tmpWords = new StringTokenizer(line);
        while (tmpWords.hasMoreTokens()) {
            String word = tmpWords.nextToken();
            if (Pattern.matches(WORD_REGEX, word)) {
                cnt++;
                if (wordMap.containsKey(word)) {
                    wordMap.put(word, wordMap.get(word) + 1);
                } else {
                    wordMap.put(word, 1);
                }
            }
        }
        return cnt;
    }
    /**
     * @description     将map按照value降序 key字典序升序排序
     * @param wordMap   存放有词频的无序HashMap
     * @return wordList 排序后的List
     */
    public static List<HashMap.Entry<String, Integer>> getSortedList(ConcurrentHashMap<String,Integer> wordMap) {
        List<HashMap.Entry<String, Integer>> wordList = new ArrayList<>(wordMap.entrySet());
        Comparator<Map.Entry<String, Integer>> cmp = (o1, o2) -> {
            if(o1.getValue().equals(o2.getValue()))
                return o1.getKey().compareTo(o2.getKey());
            return o2.getValue()-o1.getValue();
        };
        wordList.sort(cmp);
        return wordList;
    }

}