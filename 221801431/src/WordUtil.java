import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface WordUtil {

    /**
     * 统计字符数
     * @param fileName
     * @return Integer
     * @throws IOException
     */
    public Integer countChar(String fileName) throws IOException;

    /**
     * 统计单词总数
     * @param fileName
     * @return Integer
     * @throws IOException
     */
    public Integer countWord(String fileName) throws IOException;

    /**
     * 统计行数
     * @param fileName
     * @return Integer
     * @throws IOException
     */
    public Integer countLine(String fileName) throws IOException;

    /**
     * 统计各单词出现次数,并排序
     * @param fileName
     * @return String
     * @throws IOException
     */
    public List<HashMap.Entry<String, Integer>> countWordFrequency(String fileName) throws IOException;
}
