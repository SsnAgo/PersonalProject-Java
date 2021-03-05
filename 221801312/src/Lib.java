import java.io.*;
import java.util.*;
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

        //获取文件的BufferReader
        public BufferedReader getReader(){
            try {
                reader=new BufferedReader(new FileReader(inputFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("输出文件错误！请重新运行程序！");
            }
            return reader;
        }

        //写文件
        public void writeFile(String content,String outputPath){
            try {
                BufferedWriter out =new BufferedWriter(new FileWriter(outputPath));
                out.write(content);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("输出文件错误！");
            }
        }
    }

    //统计字符数，返回long
    public static long countChars(String inputPath) throws IOException {
        BufferedReader tempReader=new BufferedReader(new FileReader(inputPath));
        long charsNum=0;
        while(tempReader.read()!=-1){           //遍历reader
            charsNum++;
        }
        tempReader.close();
        return charsNum;                       //返回字符数
    }

    //统计单词数
    public static long countWords(String inputPath) throws IOException {
        BufferedReader tempReader=new BufferedReader(new FileReader(inputPath));
        Hashtable mapWord=countWordsTable(tempReader,true);
        tempReader.close();
        return (long) mapWord.get("wordsNum");
    }

    //统计词频，返回string
    public static String countWordFrequency(String inputPath) throws IOException {
        BufferedReader tempReader=new BufferedReader(new FileReader(inputPath));
        Map<String,Long> sortMap=sortWord(countWordsTable(tempReader,false));
        StringBuilder frequency=new StringBuilder();
        tempReader.close();
        Set<Map.Entry<String, Long>> entrySet;
        entrySet=sortMap.entrySet();
        for (Map.Entry<String, Long> entry : entrySet) {
            frequency.append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
        }
        return frequency.toString();
    }

    //统计每行单词，返回map
    private static Hashtable<String,Long> countLineWords
            (String line,Hashtable<String,Long> mapWord,boolean countWords){
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
                    if(!countWords){                //加入map前判断单词在map中是否存在，存在则取出其value,将其+1后放入map
                        long count=mapWord.containsKey (tempWordString)?mapWord.get(tempWordString):0;
                        mapWord.put(tempWordString,count+1);
                    }
                }
                wordFlag=0;                                 //不论是否为有效单词，都将wordFlag置零
                tempWord.delete(0,tempWord.length());
            }
        }
        if(countWords){
            mapWord.put("wordsNum",mapWord.get("wordsNum")+wordsNum);
        }
        return mapWord;
    }

    //map排序，将值按升序排序，当值相同时键按字典序排序    返回map
    private static Map<String, Long> sortWord(Map<String,Long> map) {
        Map<String, Long> sorted = map.entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed()       //值升序排序
                        .thenComparing(Map.Entry.comparingByKey())) .limit(10)                    //键排序
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new)
                );
        return sorted;
    }

    //遍历文件统计单词
    private static Hashtable<String,Long> countWordsTable
            (BufferedReader tempReader,boolean countLine) throws IOException {
        Hashtable<String,Long> mapWord=new Hashtable<>();
        if(countLine)
            mapWord.put("wordsNum",0L);
        BufferedReader reader=tempReader;
        int lineNumTemp=0;
        String line;
        while((line= reader.readLine())!=null){
            mapWord=countLineWords(line,mapWord,countLine);
            if(!line.trim().isEmpty() && countLine)
                lineNumTemp++;
        }
        if(countLine)
            lineNum=lineNumTemp;
        return mapWord;
    }
}
