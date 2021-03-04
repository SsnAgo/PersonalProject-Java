import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：WordCount中main方法要调用的函数
 * 作者：张**
 * 学号：221801315
 * 邮箱：****@***.com
 * 创建时间：2021/2/28 15:22
 * 最后修改时间：2021/3/5 3:04
 */
public class Lib {
    private static final Map<String, Integer> wordFrequencyRecords = new HashMap<>();   //单词频率记录表
    private static String recordSource = "";   //记录表的数据源
    private static boolean isSorted = false;   //记录表是否被排序过
    private static List<Map.Entry<String, Integer>> sortedRecord = null;   //排序后频率最高的10个单词
    private static int chars = 0;  //字符总数
    private static int lines = 0;  //有效行数
    private static int words = 0;  //单词总数
    private static final int BUFFER_SIZE = 8192;   //缓冲区大小

    /* 检测所给文件路径是否有效，输入文件不存在则抛出异常，输出文件不存在则创建
       输入参数：输入文件路径inFilePath，输出文件路径outFilePath
       返回值：正确的输入文件路径inFilePath*/
    public static String checkFileValid(String inFilePath, String outFilePath) {
        File inFile = new File(inFilePath);
        //当输入文件不存在时，打印提示信息，要求重新输入输入文件
        while (!inFile.exists()) {
            System.out.println("File " + inFilePath + " doesn't exist, please input right file path.");
            System.out.print("InputFile:");
            Scanner in = new Scanner(System.in);
            inFilePath = in.nextLine();
            inFile = new File(inFilePath);
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
        return inFilePath;
    }

    /* 统计输入文件中的字符总数
       输入参数：输入文件路径inFilePath
       返回值：字符总数chars */
    public static int countTotalChar(String inFilePath) {
        //如果该文件的字符总数已被统计过，直接返回
        if (recordSource.equals(inFilePath) && chars != 0)
            return chars;

        init(inFilePath);
        //利用BufferedReader的读取特性，减少访问文件次数，获得速度提升
        try {
            BufferedReader in = new BufferedReader(new FileReader(inFilePath), BUFFER_SIZE);
            int temp;

            //读取文件，直到文件结束
            while ((temp = in.read()) != -1)
                ++chars;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chars;
    }

    /* 统计输入文件中的单词总数
       输入参数：输入文件路径inFilePath
       返回值：单词总数words */
    public static int countTotalWord(String inFilePath) {
        //如果该文件的单词表已创建过，即已统计过单词总数
        if (recordSource.equals(inFilePath) && words != 0)
            return words;

        try {
            BufferedReader in = new BufferedReader(new FileReader(inFilePath), BUFFER_SIZE);
            String str;
            String regex = "(^|[^a-z0-9])([a-z]{4}[a-z\\d]*)";
            init(inFilePath);

            //读取文件，直到文件结束
            while ((str = in.readLine()) != null) {
                //如果该行有效
                if (str.matches(".*\\S+.*")) {
                    str = str.toLowerCase(Locale.ROOT);
                    Matcher matcher = Pattern.compile(regex).matcher(str);

                    while (matcher.find())
                        ++words;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    /* 统计输入文件中的有效行数
       输入参数：输入文件路径inFilePath
       返回值：行数lines */
    public static int countValidLine(String inFilePath) {
        //如果该文件的单词表已创建过，即已统计过行数
        if (recordSource.equals(inFilePath) && lines != 0)
            return lines;

        //如果没创建过，则访问文件
        boolean isValidLine = false;
        init(inFilePath);

        try {
            BufferedReader in = new BufferedReader(new FileReader(inFilePath), BUFFER_SIZE);
            int temp;

            //只有\n才被认为换行，\r不认为是一行
            //readLine()函数读取到\r也当作一行返回，因此不能用readLine()
            while ((temp = in.read()) != -1) {
                if (!isBlankChar((char) temp))
                    isValidLine = true;
                if ((char) temp == '\n' && isValidLine) {
                    isValidLine = false;
                    ++lines;
                }
            }

            if (isValidLine)  //文件结束，但是最后没有\n，也当算作一行
                ++lines;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    /* 先按频率后按字典序给单词记录表排序
       输入参数：输入文件路径inFilePath
       返回值：记录最高的10个频率的单词的列表list */
    public static List<Map.Entry<String, Integer>> getSortWordFrequencyRecords(String inFilePath) {
        //没有输入文件对应的单词频率表的时候先创建
        if (!recordSource.equals(inFilePath))
            createWordFrequencyRecords(inFilePath);

        //记录表已被排序，不需要再排序
        if (isSorted)
            return sortedRecord;

        sortedRecord = new ArrayList<Map.Entry<String, Integer>>(wordFrequencyRecords.entrySet());
        sortedRecord.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue())
                    return o1.getKey().compareTo(o2.getKey());
                else
                    return o2.getValue() - o1.getValue();
            }
        });

        if (sortedRecord.size() > 10)
            sortedRecord = sortedRecord.subList(0, 10);
        isSorted = true;
        return sortedRecord;
    }

    /* 将统计结果写入输出文件
       输入参数：输入文件路径inFilePath，输出文件路径outFilePath
       返回值：空 */
    public static void writeToOutFile(String inFilePath, String outFilePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFilePath), StandardCharsets.UTF_8));
            createWordFrequencyRecords(inFilePath);
            writer.write(("characters: " + chars + '\n'));
            writer.write(("words: " + words + '\n'));
            writer.write(("lines: " + lines + '\n'));
            getSortWordFrequencyRecords(inFilePath);
            for (int i = 0; i < sortedRecord.size(); i++) {
                writer.write((sortedRecord.get(i).getKey() + ": " + sortedRecord.get(i).getValue() + '\n'));
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
        return (t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z');
    }

    /* 判断字符是否是数字
       输入参数：字符t
       返回值：判断结果的bool值 */
    public static boolean isDigit(char t) {
        return t >= '0' && t <= '9';
    }

    /* 判断字符是否是分隔符
       输入参数：字符t
       返回值：判断结果的bool值 */
    public static boolean isBlankChar(char t) {
        return t == '\t' || t == '\r' || t == '\n' || t == ' ';
    }

    /* 创建单词频率记录表
       输入参数：输入文件路径inFilePath
       返回值：无 */
    public static void createWordFrequencyRecords(String inFilePath) {
        int letterCount = 0;     //字母数
        String word = "";
        boolean isValidLine = false;
        boolean beforeIsDigit = false;
        init(inFilePath);

        try {
            BufferedReader in = new BufferedReader(new FileReader(inFilePath), BUFFER_SIZE);
            int temp;

            //读取文件，直到文件结束
            while ((temp = in.read()) != -1) {
                ++chars;

                if (!isBlankChar((char) temp))   //说明读入非空白字符
                    isValidLine = true;
                if ((char) temp == '\n' && isValidLine) {
                    isValidLine = false;
                    ++lines;
                }

                if (isDigit((char) temp)) {  //避免44aaaa这种形式的字符串被认作单词
                    if (word.equals(""))
                        beforeIsDigit = true;
                    else {
                        if (letterCount >= 4)
                            word += (char) temp;
                        else {
                            word = "";
                            letterCount = 0;
                            beforeIsDigit = true;
                        }
                    }

                } else if (isLetter((char) temp)) {
                    if (!beforeIsDigit) {
                        ++letterCount;
                        word += (char) temp;
                    }
                } else {
                    if (letterCount >= 4)
                        addRecord(word.toLowerCase(Locale.ROOT));
                    word = "";
                    letterCount = 0;
                    beforeIsDigit = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果最后一个单词没有碰到分隔符，文件就结束，应当加入该单词
        if (!word.equals(""))
            addRecord(word.toLowerCase(Locale.ROOT));

        if (isValidLine)
            ++lines;
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
        ++words;   //单词个数加一
    }

    /* 初始化单词表及相关参数
       输入参数：单词记录表wordFrequencyRecords
       返回值：无 */
    private static void init(String inFilePath) {
        recordSource = inFilePath;
        wordFrequencyRecords.clear();
        isSorted = false;
        sortedRecord = null;
        chars = 0;
        lines = 0;
        words = 0;
    }
}
