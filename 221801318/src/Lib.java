import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Lib {
    final public String CHARACTER = "characters: ";
    final public String WORD = "words: ";
    final public String LINE = "lines: ";
    final public String TOP_WORD = "word";

    private int CHARACTERS_NUM = 0;
    private int WORDS_NUM = 0;
    private int LINES_NUM = 0;

    final private String WORD_FILTER_REGEX = "[^a-z0-9]";
    final private String WORD_REGEX = "[a-z]{4}[a-z0-9]*";
    final private String LINE_REGEX = "\\s*";

    Map<String,Integer> map = new HashMap<String,Integer>();

    /**
     *
     * @param filePath
     * @return result
     */
    public String returnCharacters(String filePath) {

        //该实现方法在最后一行为空行时，会导致结果出问题。
//        String stream = "";
//        int line = 0;
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream(filePath);
//            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
//            String temp = "";
//            try {
                //从BufferReader中读取下一行
//                while ((temp = br.readLine()) != null){
//                    //读取到的文件信息
//                    stream = stream + temp;
//                    line ++;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        if(line == 1){
//            line = 0;
//        }
//        System.out.println(LINE+line);
//        stream = stream.replaceAll(CHARACTER_REGEX,"");
//        CHARACTERS_NUM = stream.length() + line;

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
        System.out.println(result);
        return result;
    }

    /**
     *
     * @param filePath
     * @return result
     */
    public String returnWords(String filePath) {
        String result = "";
        String string = "";
        String[] Words = {};
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
     *
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
        System.out.println(result);
        return result;
    }

    /**
     *
     * @param filePath
     * @return result
     */
    public void createOutput(String filePath){

    }
}