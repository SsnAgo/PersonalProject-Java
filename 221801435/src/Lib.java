import java.io.*;
import java.util.Map;


/**
 * 编写者：221801435
 * 主要完成四个个功能：
 * 1. 统计Ascii字符数
 * 2. 统计单词数
 * 3. 统计行数
 * 4. 统计最多的十个单词
 */
public class Lib {

    /**
     * 编写者：221801435
     * 功能：统计文件的Ascii字符数
     * @param file_path 文件路径
     * @return Ascii字符数
     */
    public int getAsciiCount(String file_path){
        int counter = 0;
        InputStream in = null;
        try {
            in = new FileInputStream(file_path);
            int c = in.read();
            while (c!=-1){
                if (counter>=0&&counter<=127)
                    counter++;
                c = in.read();
            }
        }catch (FileNotFoundException e){
            System.err.println(file_path+"文件不存在，请检查路径！");
        }catch (IOException e){
            System.err.println(file_path+"文件打开失败");
        }catch (Exception e){
            System.err.println("未知错误");
        }finally {
            if (in != null){
                try {
                    in.close();
                }catch (IOException e){
                    System.err.println(file_path+"文件关闭失败");
                }
            }
        }
        return counter;
    }

    /**
     * 编写者：221801435
     * 功能：统计文件的非空行数
     * @param file_path 文件路径
     * @return int 文件行数
     */
    public int getLinesCount(String file_path){
        BufferedReader reader = null;
        int counter = 0;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file_path)));
            String line = null;
            while((line=reader.readLine())!=null){
                //判断改行是否非空
                if (line.length()>0&&!line.matches("\\s+")){
                    counter++;
                }
            }
        }catch (IOException e){
            System.err.println(file_path+"文件打开失败");
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    System.err.println(file_path+"文件关闭失败");
                }
            }
        }
        return counter;
    }

    /**
     * 编写者：221801435
     * @param file_path 文件路径
     * @return int 文件单词数
     */
    public int getWordsCount(String file_path){
        return 0;
    }

    /**
     * 编写者：221801435
     * 功能：统计文件中出现次数最多的十个单词
     * @param file_path 文件路径
     * @return
     */
    public Map<String,Integer> getMostFrequentlyWords(String file_path){
        return null;
    }
}
