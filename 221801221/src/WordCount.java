import java.util.*;

/*
    Created at 2021/2/25 by GONGHAIXU
 */
public class WordCount {

    private String inputFileContent;

    public static void main(String[] args){
        String fileContent = FileUtil.getFullFile(args[0]);
        WordCount wordCount = new WordCount(fileContent);
        StringBuilder outputFileContentBuilder = new StringBuilder();
        outputFileContentBuilder.append("characters: " + wordCount.countCharNum() + "\n");
        outputFileContentBuilder.append("words: " + wordCount.countWords() + "\n");
        outputFileContentBuilder.append("lines: " + wordCount.countLInes() + "\n");
        for (WordEntry e:
                wordCount.getTop10WordsByFrequencyOfAppearance()) {
            outputFileContentBuilder.append(e.key + ": " + e.value + "\n");
        }
        FileUtil.outputStringToFile(args[1],outputFileContentBuilder.toString());

    }

    public WordCount(String fileContent){
        this.inputFileContent = fileContent;
    }

    public int countCharNum(){
        return inputFileContent.length();
    }

    public int countWords(){
        String[] words = inputFileContent.split("[^0-9|^A-z]");
        int wordsNum = words.length;
        for (String word:
                words) {
            if(word.isEmpty() || !word.matches("^[A-z]{4,}[A-z|0-9]*$")) wordsNum--;
        }
        return wordsNum;
    }

    public int countLInes(){
        String[] lines = inputFileContent.split("\n");
        int linesNum = lines.length;
        for (String line:
                lines) {
            if(line.matches("^\\s*")) linesNum--;
        }
        return linesNum;
    }


    public List<WordEntry<String,Integer>> getTop10WordsByFrequencyOfAppearance(){
        HashMap<String,WordEntry<String,Integer>> wordMap = new HashMap<>();
        String[] words = inputFileContent.split("[^0-9|^A-z]");
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
                e1.value == e2.value ? e2.key.compareTo(e1.key) : e2.value - e1.value);
        return wordEntries.size() >= 10 ? wordEntries.subList(0,10) : wordEntries.subList(0, wordEntries.size());
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