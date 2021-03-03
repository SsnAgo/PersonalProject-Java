import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：WordCount中main方法要调用的函数
 * 作者：张福荣
 * 学号：221801315
 * 邮箱：784536133@qq.com
 * 创建时间：2021/2/28 15:22
 * 最后修改时间：2021/3/3 0:8
 */
public class Lib {
    public static Map<String, Integer> wordFrequencyRecords = new HashMap<>();  //单词频率记录表
    public static String recordSource = "";   //记录表的数据源
    public static boolean isSorted = false;   //记录表是否被排序过
    public static List<Map.Entry<String, Integer>> sortedRecord = null;   //排序后频率最高的10个单词

    /* 检测所给文件路径是否有效，输入文件不存在则抛出异常，输出文件不存在则创建
       输入参数：输入文件路径inFilePath，输出文件路径outFilePath
       返回值：空
       异常：输入文件不存在异常FileNotFoundException*/
    public static void checkFileValid(String inFilePath, String outFilePath) throws FileNotFoundException {
        File inFile = new File(inFilePath);
        //当输入文件不存在时，打印提示信息，抛出异常
        if (!inFile.exists()) {
            System.out.println("File " + inFilePath + " doesn't exist, program will exit.");
            throw new FileNotFoundException();
        }

        //当输入输出文件不为.txt文件时，默认创建同名输出文件
        String inFileExtend = inFilePath.substring(inFilePath.lastIndexOf("."));
        String outFileExtend = outFilePath.substring(outFilePath.lastIndexOf("."));
        if (!inFileExtend.equals(".txt"))
            System.out.println("Input file is recommended to be .txt file, but the program still reads it.");
        if (!outFileExtend.equals(".txt")) {
            System.out.println("Output file should be .txt file, the program will create the same name .txt file.");
            outFilePath = outFilePath.substring(0, outFilePath.lastIndexOf(".")) + ".txt";
        }

        File outFile = new File(outFilePath);
        //当输出文件不存在时，创建文件
        if (!outFile.exists()) {
            try {
                outFile.createNewFile();
                System.out.println("Output file has been created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* 统计输入文件中的字符总数
       输入参数：输入文件路径inFilePath
       返回值：字符总数count */
    public static int countTotalChar(String inFilePath) {
        int count = 0;    //记录字符总数
        int temp;

        //测试文件不会出现ASCII码以外的字符，因此只需统计文件内容+换行+回车的长度即可
        try {
            Reader in = new InputStreamReader(new FileInputStream(inFilePath), "UTF-8");
            //读取文件，直到文件结束
            while ((temp = in.read()) != -1) {
                ++count;
            }
            /*BufferedReader in=new BufferedReader(new FileReader(inFilePath));
            String str;
            while((str=in.readLine())!=null)
                count+=str.length()+1;*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    /* 统计输入文件中的单词总数
       输入参数：输入文件路径inFilePath
       返回值：单词总数count */
    public static int countTotalWord(String inFilePath) {
        int count = 0;    //记录单词总数

        //没有输入文件对应的单词频率表的时候先创建
        if (!recordSource.equals(inFilePath))
            createWordFrequencyRecords(inFilePath);

        Object[] frequency = wordFrequencyRecords.values().toArray();
        for (int i = 0; i < frequency.length; i++) {
            count += (int) frequency[i];
        }

        return count;
    }

    /* 统计输入文件中的有效行数
       输入参数：输入文件路径inFilePath
       返回值：行数count */
    public static int countValidLine(String inFilePath) {
        int count = 0;    //记录字符总数
        String str = null;
        String validLine = ".*\\S+.*";   //非空白字符行的正则表达式

        //关注点在行数，则这里使用BufferedReader
        try {
            BufferedReader in = new BufferedReader(new FileReader(inFilePath));
            //读取文件，直到文件结束
            while ((str = in.readLine()) != null) {
                //如果当前行为非空白字符
                if (str.matches(validLine))
                    ++count;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    /* 先按频率后按字典序给单词记录表排序
       输入参数：输入文件路径inFilePath
       返回值：空 */
    public static void sortWordFrequencyRecords(String inFilePath) {
        int count = 0;    //记录单词总数

        //没有输入文件对应的单词频率表的时候先创建
        if (!recordSource.equals(inFilePath))
            createWordFrequencyRecords(inFilePath);

        //记录表已被排序，不需要再排序
        if (isSorted)
            return;

        sortedRecord = new ArrayList<Map.Entry<String, Integer>>(wordFrequencyRecords.entrySet());
        sortedRecord.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        sortedRecord.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        if (sortedRecord.size() > 10)
            sortedRecord = sortedRecord.subList(0, 10);
        isSorted = true;
    }

    /* 将统计结果写入输出文件
       输入参数：输入文件路径inFilePath，输出文件路径outFilePath
       返回值：空 */
    public static void writeToOutFile(String inFilePath, String outFilePath) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFilePath), "utf-8"));
            writer.write(("characters: " + countTotalChar(inFilePath) + '\n').toString());
            writer.write(("words: " + countTotalWord(inFilePath) + '\n').toString());
            writer.write(("lines: " + countValidLine(inFilePath) + '\n').toString());
            sortWordFrequencyRecords(inFilePath);
            for (int i = 0; i < sortedRecord.size(); i++) {
                writer.write((sortedRecord.get(i).getKey() + ": " + sortedRecord.get(i).getValue() + '\n').toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 创建单词频率记录表
       输入参数：输入文件路径inFilePath
       返回值：无 */
    public static void createWordFrequencyRecords(String inFilePath) {
        recordSource = inFilePath;
        wordFrequencyRecords.clear();
        isSorted = false;
        sortedRecord = null;

        try {
            BufferedReader in = new BufferedReader(new FileReader(inFilePath));
            String temp;
            String regex = "(^|[^a-z0-9])([a-z]{4}[a-z\\d]*)";

            //读取文件，直到文件结束
            while ((temp = in.readLine()) != null) {
                temp = temp.toLowerCase(Locale.ROOT);
                Matcher matcher = Pattern.compile(regex).matcher(temp);

                while (matcher.find()) {
                    addRecord(matcher.group(2));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 往单词频率记录表加入记录
       输入参数：单词记录表wordFrequencyRecords
       返回值：无 */
    public static void addRecord(String keyWord) {
        //如果记录表中已有该单词，则频率+1
        if (wordFrequencyRecords.containsKey(keyWord)) {
            int count = wordFrequencyRecords.get(keyWord);
            wordFrequencyRecords.remove(keyWord);
            wordFrequencyRecords.put(keyWord, ++count);
        } else {
            wordFrequencyRecords.put(keyWord, 1);
        }
    }
}
