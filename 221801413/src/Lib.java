import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lib
{
    /**
     * 读取文件，生成字符串并返回
     *
     * @ param filePath
     * @ return
     * */
    public static String readFile(String filePath) {
        int temp;
        BufferedReader br = null;
        StringBuilder builder = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            builder = new StringBuilder();

            while((temp = br.read()) != -1){
                builder.append((char)temp);
            }
            /* 使用readline（）读取时，返回的字符串缺少“\n”
            String temp;
            while((temp = br.readLine()) != null){
                builder.append(temp);
            }
            */
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }

    /**
     * 遍历字符串，判断其是否为Ascii码并统计其数量
     *
     * @ param chStr
     * @ return characters' number
     * */
    public static int getCharactersCount(String chStr) {
        int charCount = 0;
        char[] charArray = chStr.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            if(charArray[i] <= 127)
                charCount++;
        }

        return charCount;
    }

        /**
     * 利用正则表达式，判断有效行数
     *
     * @ param chStr
     * @ return valid lines
     * */
    public static int getLines(String chStr){
        int lines = 0;
        String regex = "\\s+";

        //使用正则表达式匹配有效的字符行
        Pattern charPattern = Pattern.compile(regex);
        Matcher matcher = charPattern.matcher(chStr);

        while(matcher.find()){
            lines++;
        }

        return lines;
    }
}
