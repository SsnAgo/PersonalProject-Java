import java.io.*;
import java.util.*;

/**
 * 功能：WordCount中main方法要调用的函数
 * 作者：张福荣
 * 学号：221801315
 * 邮箱：784536133@qq.com
 * 创建时间：2021/2/28 15:22
 * 最后修改时间：2021/3/1 2:27
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

        File outFile = new File(outFilePath);
        //当输出文件不存在时，创建文件
        if (!outFile.exists()) {
            try {
                outFile.createNewFile();
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

    /* 判断字符是否是字母
       输入参数：字符t
       返回值：判断结果的bool值 */
    public static boolean isLetter(char t) {
        if ((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z'))
            return true;
        return false;
    }

    /* 判断字符是否是数字
       输入参数：字符t
       返回值：判断结果的bool值 */
    public static boolean isDigit(char t) {
        if (t >= '0' && t <= '9')
            return true;
        return false;
    }

    /* 判断字符是否是分隔符
       输入参数：字符t
       返回值：判断结果的bool值 */
    public static boolean isSeparator(char t) {
        if (isLetter(t))
            return false;
        else if (isDigit(t))
            return false;
        return true;
    }

    /* 创建单词频率记录表
       输入参数：输入文件路径inFilePath
       返回值：无 */
    public static void createWordFrequencyRecords(String inFilePath) {
        int letterCount = 0;     //字母数
        String word = "";
        recordSource = inFilePath;
        wordFrequencyRecords.clear();
        isSorted = false;
        sortedRecord = null;

        try {
            Reader in = new InputStreamReader(new FileInputStream(inFilePath), "UTF-8");
            int temp;
            //读取文件，直到文件结束
            while ((temp = in.read()) != -1) {
                if (isLetter((char) temp)) {
                    ++letterCount;
                    word += (char) temp;
                } else {
                    //是单词，则继续读取直到分隔符
                    if (letterCount >= 4) {
                        while (!isSeparator((char) temp)) {
                            word += (char) temp;
                            temp = in.read();
                        }

                        addRecord(word.toLowerCase(Locale.ROOT));

                        word = "";
                        letterCount = 0;
                    } else {
                        //发现不是单词后，直接跳到分隔符，开始判断下一个单词
                        while (!isSeparator((char) temp))
                            temp = in.read();

                        word = "";
                        letterCount = 0;
                    }
                }
            }

            //如果最后一个单词没有碰到分隔符，文件就结束，应当加入该单词
            if (!word.equals(""))
                addRecord(word.toLowerCase(Locale.ROOT));
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
