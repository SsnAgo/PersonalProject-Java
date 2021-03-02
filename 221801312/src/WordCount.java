import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

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
        StringBuilder tempString = new StringBuilder();
        int tempChar;                  //记录临时字符
        boolean enterFlag=false;                    //记录遇到换行符
        // 一次读入一行，直到读入null为文件结束
        while ((tempChar = reader.read()) != -1) {
            wordCount.characterNum++;
            if((char)tempChar=='\r'){               //如果碰到\r，先将行数+1
                enterFlag=true;
                if(tempString.toString().trim().length()>0)
                    wordCount.validLine++;
                wordCount.statisticsLine(tempString.toString());
                tempString.delete(0,tempString.length());
            }
            else if((char)tempChar=='\n'){
                if(enterFlag)                       //如果\r后遇到\n，不处理
                    enterFlag=false;
                else{                                //如果直接遇到\n,将行数+1
                    if(tempString.toString().trim().length()>0)
                        wordCount.validLine++;
                    wordCount.statisticsLine(tempString.toString());
                    tempString.delete(0,tempString.length());
                }
            }
            else{
                tempString.append((char)tempChar);
            }
        }
        wordCount.statisticsLine(tempString.toString());
        reader.close();

        long endTime=System.currentTimeMillis();;   //获取程序结束时间
        System.out.println("共"+wordCount.validLine+"行");
        System.out.println("程序运行时间："+ (endTime-startTime)+"ms");
        System.out.println("单词："+wordCount.validWord);
        System.out.println("字符数："+wordCount.characterNum);

        Map<String,Integer> tempMap=sortWord(wordCount.mapWord);        //用于存排序后的单词map
        Set<Map.Entry<String,Integer>> entrySet= new HashSet<>();
        entrySet=tempMap.entrySet();
        Iterator<Map.Entry<String, Integer>> iter=entrySet.iterator();
        int l=0;                                                        //限制输出10次
        while (iter.hasNext() && l<10){
            Map.Entry<String,Integer> entry=iter.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
            l++;
        }
    }

    public void statisticsLine(String line){
        if(line.trim().isEmpty())       //如果是空行，直接跳过进入下一行
            return ;

        int wordFlag=0;
        StringBuilder tempWord=new StringBuilder();                //存单词


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
            StringBuilder stringBuilder = new StringBuilder();
            //for(int k=0;k<100;k++){
                for (int i = 0; i < 100000; i++) {
                    stringBuilder.append("aaaa").append(i).append(",");
                }
                stringBuilder.append('\n');
                for (int i = 0; i < 100000; i++) {
                    stringBuilder.append("bbbb").append(i).append(",");
                }
                stringBuilder.append('\n');
                for (int i = 0; i < 10000; i++) {
                    for (int j = 0; j < 100; j++) {
                        stringBuilder.append("maxmax").append(j).append(",");
                    }
                    stringBuilder.append('\n');
                }
            //}
            String testContent = stringBuilder.toString();
            System.out.println(testContent.length());
            BufferedWriter out=new BufferedWriter(new FileWriter(outputPath));
            out.write(testContent);
            out.close();
    }

    //map排序，将值按升序排序，当值相同时键按字典序排序
    //返回一个map
    public static Map<String, Integer> sortWord(Map<String,Integer> map) {
        Map<String, Integer> sorted = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer> comparingByValue().reversed()       //值升序排序
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