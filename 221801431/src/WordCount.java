import java.io.*;
import java.util.*;

public class WordCount {
    //获取当前绝对目录路径 + "src" + File.separator
    public static final String ABSOLUTE_PATH = System.getProperty("user.dir") + File.separator;
    private static String filePath = ABSOLUTE_PATH + "src" + File.separator;

    public static void main(String[] args) throws IOException{

        String input = "input.txt";
        String output = "output.txt";
        if (args.length >= 2){
            input = args[0];
            output = args[1];
            filePath = ABSOLUTE_PATH;
        }
        //System.out.println(filePath) ;

        File inputFile = new File(filePath + input);
        //判断文件是否存在？
        if (!inputFile.exists()) {
            System.out.println(inputFile + "文件未找到");
            System.exit(0);
        } else if (!inputFile.isFile()) {
            System.out.println("文件读取异常");
            System.exit(0);
        } else {
            WordUtil wu = new WordUtilImpl();

            //String result1 = "";
            StringBuffer result = new StringBuffer("");

            //统计文件的字符数（对应输出第一行）：
            result.append("characters: "+wu.countChar(input, filePath) + "\n");
            //统计文件的单词总数（对应输出第二行）
            result.append("words: "+wu.countWord(input, filePath) + "\n");
            //统计文件的有效行数（对应输出第三行）
            result.append("lines: "+wu.countLine(input, filePath) + "\n");
            //统计文件中各单词的出现次数（对应输出接下来10行）
            List<HashMap.Entry<String, Integer>> wordList = wu.countWordFrequency(input, filePath);
            result.append(WordFrequency1(wordList));
            /*
            //法二：
            Set<WordEntity> wordSet =  wu.countWordFrequency2(input, filePath);
            result.append(WordFrequency2(wordSet));*/

            //结果输出至文件
            outputToTxt(output, result.toString());
        }
    }


    //输出结果到文件
    public static void outputToTxt(String outout, String result) throws IOException {
        File file = new File(filePath + outout);//定义文件位置
        //判断文件是否存在，不存在则直接创建新文件
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        writer.write(result);

        writer.close();
    }

    public static StringBuffer WordFrequency1(List<HashMap.Entry<String, Integer>> wordList){
        StringBuffer result = new StringBuffer("");
        if (!wordList.equals(null)){
            if (wordList.size() <= 10){
                for (HashMap.Entry h : wordList) {
                    result.append(h.getKey() + ": " + h.getValue() + "\n");
                }
            } else {
                for (int i=0; i<10; i++){
                    HashMap.Entry<String,Integer> h = wordList.get(i);
                    result.append(h.getKey() + ": " + h.getValue() + "\n");
                }
            }
        } else {
            result.append("无单词\n");
        }
        return result;
    }

    public static StringBuffer WordFrequency2(Set<WordEntity> wordSet){
        if (wordSet.equals(null)){
            return new StringBuffer("无单词\n");
        }

        StringBuffer result = new StringBuffer("");
        Iterator<WordEntity> iterator = wordSet.iterator();
        int count=0;
        while (iterator.hasNext()){
            if (count>=10){
                break;
            }
            WordEntity we = iterator.next();
            result.append(we.getKey() + ": " + we.getValue() + "\n");
            count++;
        }
        return result;
    }
}
