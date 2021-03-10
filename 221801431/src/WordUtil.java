import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface WordUtil {

    /**
     * 统计字符数
     * @param fileName
     * @return Integer
     * @throws IOException
     */
     Integer countChar(String fileName) throws IOException;

    /**
     *
     * @param fileName
     * @param fileDirectory
     * @return Integer
     * @throws IOException
     */
     Integer countChar(String fileName, String fileDirectory) throws IOException;


    /**
     * 统计单词总数
     * @param fileName
     * @return Integer
     * @throws IOException
     */
     Integer countWord(String fileName) throws IOException;

    /**
     *
     * @param fileName
     * @return Integer
     * @throws IOException
     */
     Integer countWord(String fileName, String fileDirectory) throws IOException;

    /**
     * 统计行数
     * @param fileName
     * @return Integer
     * @throws IOException
     */
     Integer countLine(String fileName) throws IOException;

    /**
     *
     * @param fileName
     * @return Integer
     * @throws IOException
     */
     Integer countLine(String fileName, String fileDirectory) throws IOException;

    /**
     * 统计各单词出现次数,并排序
     * @param fileName
     * @return List<HashMap.Entry<String, Integer>>
     * @throws IOException
     */
     List<HashMap.Entry<String, Integer>> countWordFrequency(String fileName) throws IOException;

    /**
     *
     * @param fileName
     * @return List<HashMap.Entry<String, Integer>>
     * @throws IOException
     */
     List<HashMap.Entry<String, Integer>> countWordFrequency(String fileName, String fileDirectory) throws IOException;

    /**
     *
     * @param fileName
     * @return Set<WordEntity>
     * @throws IOException
     */
     Set<WordEntity> countWordFrequency2(String fileName) throws IOException;
    /**
     *
     * @param fileName
     * @return Set<WordEntity>
     * @throws IOException
     */
     Set<WordEntity> countWordFrequency2(String fileName, String fileDirectory) throws IOException;
}
