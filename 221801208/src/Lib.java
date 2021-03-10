import java.io.*;
import java.util.*;

public class Lib {

    File inputFile;
    File outputFile;
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private int CharNum = 0;    //字符个数
    private int WordNum = 0;    //单词总数
    private int RowNum = 0;     //有效行数
    private Map<String, Integer> map = new HashMap<>();
    private Set<Word> set = new TreeSet<>();

    //单词类
    class Word implements Comparable<Word>{

        String word;     //单词
        int frequency;    //单词出现次数

        Word(String s,int frqy){
            word = s;
            frequency = frqy;
        }

        @Override
        public int compareTo(Word w) {
            if(this.frequency > w.frequency)
                return -1;
            else if(this.frequency < w.frequency)
                return 1;
            else
                return this.word.compareTo(w.word);
        }
    }

    Lib(File file1, File file2){
        inputFile = file1;
        outputFile = file2;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(outputFile));
        }
        catch (Exception e){
        }
    }

    /**
     * 判断一个字符串是否为单词
     * @param curWord
     * @return
     */
    public boolean isWord(String curWord){
        //长度小于4不属于单词
        if(curWord.length() < 4)
            return false;
        //前四个字符为字母才属于单词
        for(int i = 0; i < 4; i++){
            if(!(curWord.charAt(i) >= 65 && curWord.charAt(i) <= 90) &&
                    !(curWord.charAt(i) >= 97 && curWord.charAt(i) <= 122)){
                return false;
            }
        }
        return true;
    }

    /**
     * 将单词插入hashmap
     * @param curWord
     */
    public void wordToHashMap(String curWord){
        String lowerWord = curWord.toLowerCase();
        Integer times = map.get(lowerWord);
        //times==null说明这个单词已经插入hashmap
        if(times != null)
            map.put(lowerWord,times+1);
        else
            map.put(lowerWord,1);
    }

    /**
     * 统计并打印字符数和单词数
     * @throws IOException
     */
    public void countCharAndWord() throws IOException{
        int c;
        String curWord = "";
        while ((c = reader.read()) != -1){
            //ASCⅡ码在0到127之间的属于字符（汉字不包括在内）
            if(c >= 0 && c<= 127) {
                CharNum++;
                //如果c为数字或字母，则作为单词的一部分
                if (Character.isLetterOrDigit(c)) {
                    curWord += (char) c;
                }
                //如果c为非数字或字母的字符，则作为分隔符
                else {
                    if (isWord(curWord)) {
                        WordNum++;
                        wordToHashMap(curWord);
                    }
                    curWord = "";
                }
            }
            //如果c为中文，则作为分隔符
            else{
                if (isWord(curWord)) {
                    WordNum++;
                    wordToHashMap(curWord);
                }
                curWord = "";
            }
        }
        //对最后一个单词进行处理
        if(isWord(curWord)){
            WordNum++;
            wordToHashMap(curWord);
        }
        writer.write("characters: " + CharNum + "\n");
        writer.write("words: " + WordNum + "\n");
    }

    /**
     * 打印频率最高的10个单词的出现次数
     * @throws IOException
     */
    public void printFrequency() throws IOException{
        Iterator<Map.Entry<String, Integer>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, Integer> entry = iterator1.next();
            Word w = new Word(entry.getKey(),entry.getValue());
            set.add(w);
        }
        Iterator<Word> iterator2 = set.iterator();
        int i = 0;
        while (iterator2.hasNext()) {
            Word w = iterator2.next();
            writer.write(w.word + ": " + w.frequency + "\n");
            i++;
            if(i >= 10)
                break;
        }
    }

    /**
     * 统计并打印有效行数
     * @throws IOException
     */
    public void countRowNum() throws IOException{
        String row;
        BufferedReader reader1 = new BufferedReader(new FileReader(inputFile));
        while((row = reader1.readLine()) != null){
            //只统计包含非空白字符的行
            if(!row.trim().isEmpty())
                RowNum++;
        }
        writer.write("lines:" + RowNum + "\n");
    }

    /**
     * 关闭文件
     * @throws IOException
     */
    public void closeFile() throws IOException{
        reader.close();
        writer.close();
    }
}
