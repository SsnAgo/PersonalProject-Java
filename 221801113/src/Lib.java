import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {

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




/*public static Reader readFile(String fileName) throws IOException {
        return new BufferedReader(new FileReader(fileName));
    }

    public static void countCharacters(String fileName) throws IOException {
        Reader reader = readFile(fileName);
        int ch = 0, cnt = 0;
        while ((ch = reader.read()) != -1) {
            cnt++;
        }
        reader.close();
        charsNum = cnt;
    }

    public static void countLines(String fileName) throws IOException {
        BufferedReader reader = (BufferedReader) readFile(fileName);
        int cnt = 0;
        String curLine = null;
        while ((curLine = reader.readLine()) != null) {
            if (!curLine.replaceAll("\r|\n", "").trim().equals("")) {
                cnt++;
            }
        }
        reader.close();
        linesNum = cnt;
    }

    public static void countWords(String fileName) throws IOException {
        BufferedReader reader = (BufferedReader) readFile(fileName);
        hashMap = new HashMap<String, Integer>();
        int cnt = 0;
        Pattern pattern = Pattern.compile("^[a-z]{4}[a-z0-9]*");
        Matcher matcher = null;
        String curLine = null;
        while ((curLine = reader.readLine()) != null) {
            String[] wordStrTmp = curLine.split("[^a-zA-Z0-9]");
            for (String word : wordStrTmp) {
                word = word.toLowerCase();
                matcher = pattern.matcher(word);
                if (!word.equals("") && matcher.find()) {
                    cnt++;
                    if(hashMap.containsKey(word)) {
                        hashMap.put(word, hashMap.get(word) + 1);
                    }
                    else {
                        hashMap.put(word, 1);
                    }
                }
            }
        }
        wordsNum = cnt;
    }



    public static void writeToFile(String inputPath, String OutputPath) throws IOException {
        countCharacters(inputPath);
        countLines(inputPath);
        countWords(inputPath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(OutputPath));
        int cnt = 0;
        StringBuilder str = new StringBuilder("characters: " + charsNum + '\n' + "words: " + wordsNum + '\n'
                + "lines: " + linesNum + '\n');
        List<HashMap.Entry<String, Integer>> sortedList = getSortedList();
        for(HashMap.Entry<String,Integer> entry:sortedList) {
            str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            cnt++;
            if (cnt >= 10) break;
        }
        writer.write(str.toString());
        writer.close();
    }*/

}
