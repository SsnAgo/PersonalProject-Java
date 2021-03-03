import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    public static String readFile(String fileName) throws IOException {
        BufferedReader reader = null;
        StringBuilder str = new StringBuilder();
        int ch = 0;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while ((ch = reader.read()) != -1) {
                str.append((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return str.toString();
    }

    public static void writeFile(String OutputPath, int charsNum, int wordsNum, int linesNum
            , HashMap<String, Integer> wordMap) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(OutputPath));
        int cnt = 0;
        StringBuilder str = new StringBuilder("characters: " + charsNum + '\n' + "words: " + wordsNum + '\n'
                + "lines: " + linesNum + '\n');
        List<HashMap.Entry<String, Integer>> sortedList = Lib.getSortedList(wordMap);
        for(HashMap.Entry<String,Integer> entry:sortedList) {
            str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            cnt++;
            if (cnt >= 10) break;
        }
        writer.write(str.toString());
        writer.close();
    }

    public static int countCharacters(String str) {
        return str.length();
    }

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

    public static List<HashMap.Entry<String, Integer>> getSortedList(HashMap<String, Integer> wordMap) {
        List<HashMap.Entry<String, Integer>> list =
                new ArrayList<HashMap.Entry<String, Integer>>(wordMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
                if(o1.getValue().equals(o2.getValue()))
                    return o1.getKey().compareTo(o2.getKey());
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return list;
    }

}
