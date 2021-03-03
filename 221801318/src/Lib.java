import java.io.*;

public class Lib {
    final public String CHARACTER = "characters: ";
    final public String WORD = "words: ";
    final public String LINE = "lines: ";
    final public String TOP_WORD = "word";

    private int CHARACTERS_NUM = 0;

    final private String CHARACTER_REGEX = "[^a-zA-Z0-9\\t\\n\\s]";


    public String returnCharacters(String filePath)
    {

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

        byte[] stream = new byte[20*1024];
        int len = stream.length;
        String temp = "";
        String result = "";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            while (fileInputStream.read(stream,0,len) != -1){
                temp = new String(stream);
                result += temp;
            }
            result = result.replaceAll(CHARACTER_REGEX,"");
            CHARACTERS_NUM += result.length();
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

        System.out.println(CHARACTERS_NUM);

        return "test";
    }
    public String returnWords(String filePath)
    {
        return "test";
    }
    public String returnLines(String filePath)
    {
        return "test";
    }
    public void createOutput(String filePath){

    }
}