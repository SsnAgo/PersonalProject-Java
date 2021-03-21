import java.io.*;
import java.util.*;

public class Lib {
    static CountData data = new CountData();

    /**
     * 读取文件
     * @param input
     */
    static void openFile(File input) {
        try {
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                data.setCountLine();
                data.setCountWord(countWord(s));
                data.setGetWordFrequency(getWordFrequency(s));
            }
            br.close();
            fr.close();
            return;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return;
        }
    }


    static CountData getData() {
        return data;
    }

    /**
     * 统计字符数
     * @param file
     * @return
     */
    static int countChar(File file) {
        int myCountChar = 0;
        try {
            byte[] tem = new byte[20 * 1024];
            int len = tem.length;
            int bytes = 0;
            FileInputStream in = new FileInputStream(file);
            while ((bytes = in.read(tem, 0, len)) != - 1)
                myCountChar += bytes;
            data.setCountChar(myCountChar);
            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return myCountChar;
        }
    }

    /**
     * 统计单词数
     * @param s
     * @return
     */
    static int countWord(String s) {
        int myWordCount = 0;
        String word = "";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i))
                    || Character.isLowerCase(s.charAt(i))) {
                word += s.charAt(i);
            }
            else {
                if (isWord(word)) {
                    myWordCount++;
                }
                word = "";
            }
        }
        if (isWord(word)) {
            myWordCount++;
        }
        return myWordCount;
    }

    /**
     * 统计最多的10个单词及其词频
     * @param s
     * @return
     */
    static HashMap<String, Integer> getWordFrequency(String s) {
        String word = "";
        HashMap<String, Integer> myWordCount = new HashMap<String, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i))
                    || Character.isLowerCase(s.charAt(i))) {
                word += s.charAt(i);
            }
            else {
                if (isWord(word)) {
                    word = word.toLowerCase();
                    if (myWordCount.containsKey(word)) {
                        myWordCount.put(word, myWordCount.get(word) + 1);
                    }
                    else
                        myWordCount.put(word, 1);
                }
                word = "";
            }
        }
        if (isWord(word)) {
            word = word.toLowerCase();
            if (myWordCount.containsKey(word)) {
                myWordCount.put(word, myWordCount.get(word) + 1);
            }
            else
                myWordCount.put(word, 1);
        }
        return myWordCount;
    }

    /**
     * 判断字符串是否为单词
     * @param s
     * @return
     */
    static boolean isWord(String s) {
        if (s.length() < 4)
            return false;
        for (int i = 0; i < 4; i++) {
            if (! Character.isLowerCase(s.charAt(i)) && ! Character.isUpperCase(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

class CountData{
    int countWord;
    int countLine;
    int countChar;
    HashMap<String, Integer> getWordFrequency;

    CountData() {
        countWord = 0;
        countLine = 0;
        countChar = 0;
        getWordFrequency = new HashMap<String, Integer>();
    }

    void setCountWord(int n) {
        countWord += n;
    }

    void setCountLine() {
        countLine ++;
    }

    void setCountChar(int n) {
        countChar += n;
    }

    void setGetWordFrequency(HashMap<String, Integer> n) {
        for (String s : n.keySet()) {
            if (getWordFrequency.containsKey(s)) {
                getWordFrequency.put(s, getWordFrequency.get(s) + n.get(s));
            }
            else {
                getWordFrequency.put(s, 1);
            }
        }
    }

    int getCountWord() {
        return countWord;
    }

    int getCountLine() {
        return countLine;
    }

    int getCountChar() {
        return countChar;
    }

    List<Map.Entry<String, Integer>> getGetWordFrequency() {
        List<Map.Entry<String, Integer>> list;
        list = new ArrayList<>(getWordFrequency.entrySet());
        list.sort((o1, o2) -> {
            if (o1.getValue() != o2.getValue()) {
                return o2.getValue().compareTo(o1.getValue());
            }
            else {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return list;
    }
}