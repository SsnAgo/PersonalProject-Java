import java.io.*;
import java.util.*;

public class WordCount{

    public String inputPath;
    public String outputPath;
    public int validLine;       //有效行
    public int validWord;       //有效单词
    public int characterNum;    //字符数
    public Map<String,Integer> mapWord=new HashMap<String, Integer>();//用于存单词


    //构造函数
    public WordCount(String inputPath,String outputPath) throws IOException {
        this.inputPath=inputPath;
        this.outputPath=outputPath;

        //bigDateTest();
    }

    //入口
    public static void main(String[] args) throws IOException {
        if(args.length!=2){
            System.out.println("参数错误！");
            return ;
        }

        WordCount wordCount=new WordCount(args[0],args[1]);
        long startTime=System.currentTimeMillis();//获取程序开始时间
        File inputFile=new File(wordCount.inputPath);
        BufferedReader reader=null;

        reader=new BufferedReader(new FileReader(inputFile));
        String tempString = null;
        int line = 1;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            // 显示行号
            System.out.println("line " + line + ": " + tempString);
            line++;
            wordCount.statisticsLine(tempString);
        }
        reader.close();

        long endTime=System.currentTimeMillis();;   //获取程序结束时间
        System.out.println("共"+wordCount.validLine+"行");
        System.out.println("程序运行时间："+ (endTime-startTime)+"ms");
        System.out.println("单词："+wordCount.validWord);
        System.out.println("字符数："+wordCount.characterNum);

        Map<String,Integer> tempMap=sortWord(wordCount.mapWord);        //用于存排序后的单词map
        Set<Map.Entry<String,Integer>> entrySet=tempMap.entrySet();
        Iterator<Map.Entry<String, Integer>> iter=entrySet.iterator();
        while (iter.hasNext()){
            Map.Entry<String,Integer> entry=iter.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    public void statisticsLine(String line){
        if(line.trim().isEmpty())       //如果是空行，直接跳过进入下一行
            return ;
        validLine++;                    //不是空行就是有效行

        int wordFlag=0;
        StringBuilder tempWord=new StringBuilder();                //存单词

        characterNum+=line.length();    //字符数+=每行的字符数

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
                    validWord++;                            //若有wordFlag>=4，判定为单词有效，将有效单词++
                    String tempWordString=tempWord.toString().toLowerCase();
                    //加入map前判断单词在map中是否存在，存在则取出其value,将其+1后放入map
                    int count=mapWord.containsKey(tempWordString)?mapWord.get(tempWordString):0;
                    mapWord.put(tempWordString,count+1);
                }
                wordFlag=0;                                 //不论是否为有效单词，都将wordFlag置零
                tempWord.delete(0,tempWord.length());
            }
        }
    }

    public void bigDateTest() throws IOException {
        BufferedWriter out=new BufferedWriter(new FileWriter(outputPath));

        for(int j=0;j<100000;j++){
            for(int i=0;i<10000;i++){
                out.write("a");
            }
            out.write("\n");
            for(int i=0;i<10000;i++){
                out.write("b");
            }
            out.write("\n");
            for(int i=0;i<10000;i++){
                out.write("c");
            }
            out.write("\n");
        }
        out.close();
    }

    public static Map<String, Integer> sortWord(Map<String,Integer> map){   //将存单词的map按值排序
        if(map==null || map.isEmpty())      //若map为空直接返回
            return null;
        Map<String, Integer> sortedMap=new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList=new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(entryList, new MapValueComparator());
        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
    static class MapValueComparator implements Comparator<Map.Entry<String,Integer>>{       //map比较器类

        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }
    }
}