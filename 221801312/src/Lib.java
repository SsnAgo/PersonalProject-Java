import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Lib {

    public static int lineNum;
    //文件工具类，用于读取文件和写入文件
    public static class FileUtil{
        private File inputFile;
        private BufferedReader reader=null;

        //用输入文件路径构造函数
        public FileUtil(String inputPath){
            inputFile=new File(inputPath);
        }

        public BufferedReader getReader(){
            try {
                reader=new BufferedReader(new FileReader(inputFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return reader;
        }

        //写文件
        public void writeFile(String content,String outpath){

        }
    }

    //统计字符数，返回long
    public static long countChars(BufferedReader tempReader) throws IOException {
        BufferedReader reader=tempReader;
        long charsNum=0;
        while(reader.read()!=-1){
            charsNum++;
        }
        return charsNum;
    }

    //统计有效行数，返回int
    public static int countLines(BufferedReader tempReader) throws IOException {
        BufferedReader reader=tempReader;
        int lineNum=0;
        String tempString;     //记录每行的字符串
        while((tempString= reader.readLine())!=null){
            if(!tempString.trim().isEmpty())
                lineNum++;
        }
        return lineNum;
    }

    //统计单词
    public static long countWords(BufferedReader tempReader) throws IOException {
        BufferedReader reader=tempReader;
        int lineNumTemp=0;
        long wordsNum = 0;
        String line;
        while((line= reader.readLine())!=null){
            wordsNum+=countLineWordsNum(line);
            if(!line.trim().isEmpty())
                lineNumTemp++;
        }
        lineNum=lineNumTemp;
        return wordsNum;
    }

    //统计每行的单词数，返回long
    private static long countLineWordsNum(String line){
        long wordsNum=0;
        int wordFlag=0;
        StringBuilder tempWord=new StringBuilder();     //存单词
        for(int i=0;i<line.length();i++){
            if(Character.isLetter(line.charAt(i))){
                wordFlag++;                             //如果是字母，将wordFlag++;
                tempWord.append(line.charAt(i));        //用于清空tempWord
            }
            else if(Character.isDigit(line.charAt(i)) && wordFlag>=4){
                wordFlag++;                             //当wordFlag>=4时，判定为一个单词，若后续为数字，将wordFlag继续++
                tempWord.append(line.charAt(i));
            }
            else if(wordFlag<4){                        //当wordFlag<4且遇到字符为数字时，说明不可能为单词，将wordFlag置零
                wordFlag=0;
                tempWord.delete(0,tempWord.length());
            }
            if(!Character.isLetterOrDigit(line.charAt(i)) || i==line.length()-1){
                if(wordFlag>=4){                            //当遇到分隔符或检索到行尾时
                    wordsNum++;                            //若有wordFlag>=4，判定为单词有效，将有效单词++
                    String tempWordString=tempWord.toString().toLowerCase();
                }
                wordFlag=0;                                 //不论是否为有效单词，都将wordFlag置零
                tempWord.delete(0,tempWord.length());
            }
        }
        return wordsNum;
    }

    public static Map<String,Long> countWordFrequency(BufferedReader tempReader) throws IOException {
        BufferedReader reader=tempReader;
        Map<String,Long> mapWord=new HashMap<>();
        String line;
        while((line= reader.readLine())!=null){
            mapWord=countLineWordsFrenquency(line,mapWord);
        }
        mapWord=sortWord(mapWord);
        return mapWord;
    }

    //统计每行的单词数，返回long
    private static Map<String,Long> countLineWordsFrenquency(String line,Map<String,Long> mapWord){
        long wordsNum=0;
        int wordFlag=0;
        StringBuilder tempWord=new StringBuilder();     //存单词
        for(int i=0;i<line.length();i++){
            if(Character.isLetter(line.charAt(i))){
                wordFlag++;                             //如果是字母，将wordFlag++;
                tempWord.append(line.charAt(i));        //用于清空tempWord
            }
            else if(Character.isDigit(line.charAt(i)) && wordFlag>=4){
                wordFlag++;                             //当wordFlag>=4时，判定为一个单词，若后续为数字，将wordFlag继续++
                tempWord.append(line.charAt(i));
            }
            else if(wordFlag<4){                        //当wordFlag<4且遇到字符为数字时，说明不可能为单词，将wordFlag置零
                wordFlag=0;
                tempWord.delete(0,tempWord.length());
            }
            if(!Character.isLetterOrDigit(line.charAt(i)) || i==line.length()-1){
                if(wordFlag>=4){                            //当遇到分隔符或检索到行尾时
                    wordsNum++;                            //若有wordFlag>=4，判定为单词有效，将有效单词++
                    String tempWordString=tempWord.toString().toLowerCase();
                    //加入map前判断单词在map中是否存在，存在则取出其value,将其+1后放入map
                    long count=mapWord.containsKey (tempWordString)?mapWord.get(tempWordString):0;
                    mapWord.put(tempWordString,count+1);
                }
                wordFlag=0;                                 //不论是否为有效单词，都将wordFlag置零
                tempWord.delete(0,tempWord.length());
            }
        }
        return mapWord;
    }

    //map排序，将值按升序排序，当值相同时键按字典序排序
    //返回一个map
    private static Map<String, Long> sortWord(Map<String,Long> map) {
        Map<String, Long> sorted = map.entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed()       //值升序排序
                        .thenComparing(Map.Entry.comparingByKey()))                     //键排序
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new)
                );
        return sorted;
    }
}
