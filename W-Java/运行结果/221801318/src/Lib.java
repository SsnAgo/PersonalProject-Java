import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Lib {
    final public String CHARACTER = "characters: ";
    final public String WORD = "words: ";
    final public String LINE = "lines: ";
    private int CHARACTERS_NUM = 0;
    private int WORDS_NUM = 0;
    private int LINES_NUM = 0;
    private int TOP_WORD_NUM = 10;

    final private String WORD_FILTER_REGEX = "[^a-z0-9]";
    final private String WORD_REGEX = "[a-z]{4}[a-z0-9]*";
    final private String LINE_REGEX = "\\s*";

    Map<String,Integer> map = new HashMap<String,Integer>();

    /**
     *统计字符数
     * @param filePath
     * @return result
     */
    public String returnCharacters(String filePath) {
        int bytes = 0;
        byte[] stream = new byte[20*1024];
        int len = stream.length;
        FileInputStream fileInputStream = null;
        String result = "";
        try {
            fileInputStream = new FileInputStream(filePath);
            while ( (bytes = fileInputStream.read(stream,0,len)) != -1){
                CHARACTERS_NUM += bytes;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result = CHARACTER + CHARACTERS_NUM;
        return result;
    }

    /**
     *统计单词数
     * @param filePath
     * @return result
     */
    public String returnWords(String filePath) {
        String result = "";
        String string = "";
        // 因为string具有不可变性，用StringBuffer来进行读取的添加
        StringBuffer stringBuffer = new StringBuffer();
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String temp = "";
            try {
                while ((temp = bufferedReader.readLine()) != null){
                    stringBuffer.append(temp);
                    stringBuffer.append(" ");
                }
                string = stringBuffer.toString();
                string = string.toLowerCase();
                string = string.replaceAll(WORD_FILTER_REGEX," ");
                StringTokenizer stringTokenizer = new StringTokenizer(string);
                while (stringTokenizer.hasMoreTokens()){
                    String word = stringTokenizer.nextToken();
                    if(Pattern.matches(WORD_REGEX,word)){
                        WORDS_NUM ++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result = WORD + WORDS_NUM;
        return result;
    }

    /**
     *统计有效行数
     * @param filePath
     * @return result
     */
    public String returnLines(String filePath) {
        String result = "";
        int line = 0;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String temp = "";
            try {
                //从BufferReader中读取下一行
                while ((temp = br.readLine()) != null){
                    //读取到的文件信息
                    temp.replaceAll(LINE_REGEX,"");
                    if (temp.length()!=0){
                        LINES_NUM ++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result = LINE + LINES_NUM;
        return result;
    }

    /**
     * 统计最多的10个单词及其词频
     * @param filePath
     * @return
     */
    public String returnTopWords(String filePath){
        String result = "";
        String string = "";
        StringBuffer stringBuffer = new StringBuffer();
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String temp = "";
            try {
                while ((temp = bufferedReader.readLine()) != null){
                    stringBuffer.append(temp);
                    stringBuffer.append(" ");
                }
                string = stringBuffer.toString();
                string = string.toLowerCase();
                string = string.replaceAll(WORD_FILTER_REGEX," ");
                StringTokenizer stringTokenizer = new StringTokenizer(string);
                while (stringTokenizer.hasMoreTokens()){
                    String word = stringTokenizer.nextToken();
                    if(Pattern.matches(WORD_REGEX,word)){
                        if (map.get(word)==null){
                            map.put(word,1);
                        } else {
                            int times = map.get(word);
                            map.put(word,++times);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,valueComparator);
        if (list.size() < 10){
            TOP_WORD_NUM = list.size();
        }

        for (int i =0; i < TOP_WORD_NUM; i++){
            result += list.get(i).getKey() + ": " + list.get(i).getValue();
            if (i != TOP_WORD_NUM -1){
                result += System.getProperty("line.separator");
            }
        }
        return result;
    }

    /**
     *生成最终的输出结果，并将其写入到指定文件
     * @param inFilePath , outFilePath
     * @return 无
     */
    public void createOutput(String inFilePath,String outFilePath) throws IOException {
        String result = "";
        String characterResult = returnCharacters(inFilePath);
        String wordResult = returnWords(inFilePath);
        String lineResult = returnLines(inFilePath);
        String topWordResult = returnTopWords(inFilePath);

        if (characterResult != CHARACTER){
            result += characterResult;
            result += System.getProperty("line.separator");
        }else {
            System.out.println("程序在统计字符数时出错，无返回结果！");
        }


        if (wordResult != WORD){
            result += wordResult;
            result += System.getProperty("line.separator");
        }else {
            System.out.println("程序在统计单词数时出错，无返回结果！");
        }

        if (lineResult != LINE){
            result += lineResult;
            result += System.getProperty("line.separator");
        }else {
            System.out.println("程序在统计有效行时出错，无返回结果！");
        }

        if (topWordResult != null){
            result += topWordResult;
            result += System.getProperty("line.separator");
        }else {
            System.out.println("程序在统计词频时出错，无返回结果！");
        }

        System.out.println(result);

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(outFilePath);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedWriter.close();
        }

    }

    Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
        public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2) {
            if(o1.getValue() == o2.getValue()) {
                return o1.getKey().compareTo(o2.getKey());
            }else{
                return (o2.getValue() - o1.getValue());
            }

        }};

}