import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCount {
    public static void main(String[] args) {
        String filename = "E:/input.txt";
        String content = "";

        FileReader fr = null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String temp = "";

            while (true) {
                try {
                    if (!((temp = br.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                content += temp;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringTokenizer st = new StringTokenizer(content, " ,.!?\"'");
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            word.toLowerCase();
            if (Isword(word)) {
                if(map.get(word) != null) {
                    int value = ((Integer)map.get(word)).intValue();
                    value++;
                    map.put(word, new Integer(value));
                }
                else {
                    map.put(word, new Integer(1));
                }
            } else
                continue;
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet()); //转换为list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
    }

    private static boolean Isword(String word) {
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