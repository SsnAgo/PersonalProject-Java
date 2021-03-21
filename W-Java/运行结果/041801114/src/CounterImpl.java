import java.util.Map;

/**
 * <p>
 * WordCount 所具有的功能
 * </p>
 * 
 * @author Tozzger
 *
 */
public interface CounterImpl {

    /**
     * 
     * <p>
     * <strong>统计字符数</strong>：
     * </p>
     * <ul>
     * <li>只需要统计Ascii码，汉字不需考虑</li>
     * <li>空格，水平制表符，换行符，均<strong>算</strong>字符</li>
     * </ul>
     * 
     * @return 字符数
     */
    long getCharCount();

    /**
     * 
     * <p>
     * 统计<strong>单词总数</strong>，单词：至少以<strong>4</strong>个英文字母开头，跟上字母数字符号，单词以分隔符分割，<strong>不区分大小写</strong>。
     * </p>
     * <ul>
     * <li>英文字母： A-Z，a-z</li>
     * <li>字母数字符号：A-Z， a-z，0-9</li>
     * <li>分割符：空格，非字母数字符号</li>
     * <li><strong>例</strong>：file123是一个单词， 123file不是一个单词。file，File和FILE是同一个单词</li>
     * </ul>
     * 
     * @return 单词总数
     */
    long getWordCount();

    /**
     * <p>
     * 统计<strong>有效行数</strong>：任何包含<strong>非空白</strong>字符的行，都需要统计。
     * </p>
     * 
     * @return 行数
     */
    long getLineCount();

    /**
     * 
     * <p>
     * 统计各<strong>单词的出现次数</strong>，最终只输出频率最高的<strong>10</strong>个。
     * </p>
     * <ul>
     * <li>频率相同的单词，优先输出字典序靠前的单词。 <blockquote>
     * <p>
     * 例如，windows95，windows98和windows2000同时出现时，则先输出windows2000
     * </p>
     * </blockquote></li>
     * <li>输出的单词统一为小写格式</li>
     * </ul>
     * 
     * @return 单词—频数映射
     */
    Map<String, Long> getWordFrequency();

}
