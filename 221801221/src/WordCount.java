import java.util.*;

/*
    Created at 2021/2/25 by GONGHAIXU
 */
public class WordCount {

    public static void main(String[] args){
        String s = FileUtil.getFullFile(args[0]);
        ArrayList list = new WordCount().sortWordsByFrequencyOfAppearance(s);
        for (int i = 0 ; i < list.size() ; i++) {
            System.out.println(list.get(i).toString());
        }
    }

    public static int countChars(String s){
        return s.length();
    }

    public static int countWords(String s){
        String[] words = s.split("[^0-9|^A-z]");
        int wordsNum = words.length;
        for (String word:
                words) {
            if(word.isEmpty() || !word.matches("^[A-z]{4,}[A-z|0-9]*$")) wordsNum--;
        }
        return wordsNum;
    }

    public static int countLInes(String s){
        String[] lines = s.split("\n");
        int linesNum = lines.length;
        for (String line:
                lines) {
            if(line.matches("^\\s*")) linesNum--;
        }
        return linesNum;
    }

    public ArrayList sortWordsByFrequencyOfAppearance(String s){
        HashMap<String,WordEntry<String,Integer>> wordMap = new HashMap<>();
        String[] words = s.split("[^0-9|^A-z]");
        ArrayList<WordEntry<String,Integer>> wordEntries = new ArrayList<>();
        HashMap<String,WordEntry<String,Integer>> wordEntryHashMap = new HashMap<>();
        for (String word:
                words) {
            if(word.isEmpty() || !word.matches("^[A-z]{4,}[A-z|0-9]*$")) continue;
            if(!wordEntryHashMap.containsKey(word)){
                WordEntry<String,Integer> wordEntry = new WordEntry<>(word,1);
                wordEntries.add(wordEntry);
                wordEntryHashMap.put(word,wordEntry);
            }else{
                wordEntryHashMap.get(word).value++;
            }
        }
        Collections.sort(wordEntries,(WordEntry<String,Integer> e1, WordEntry<String,Integer> e2) ->
                e1.value == e2.value ? e1.key.compareTo(e2.key) : e1.value - e2.value);
        return wordEntries;
    }

    private class WordEntry<K,V>{

        K key;
        V value;

        WordEntry(K key,V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "WordEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

}
