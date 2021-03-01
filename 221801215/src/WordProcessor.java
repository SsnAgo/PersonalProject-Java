import java.util.Map;
import java.util.TreeMap;

/**
 * 对单词校验、统计的类
 */
public class WordProcessor {
    private boolean isInPossibleWord = false;
    private final int INIT_WORD_MAX_LENGTH = 200;
    private boolean isPossibleWordAvailable;
    private Map<String,Integer> wordSumMap = new TreeMap<>();

    /**
     * The Possible word.
     */
    public StringBuffer possibleWord;
    /**
     * The Word sum.
     */
    public int wordSum = 0;

    /**
     * 判断字符串是否符合单词要求
     *
     * <p>单词的判断标准：
     * 1.至少以4个英文字母开头
     * 2.跟上字母数字符号
     * 3.英文字母： A-Z，a-z，字母数字符号：A-Z， a-z，0-9
     * eg.
     * file123是一个单词， 123file不是一个单词。</p>
     *
     * @param stringBufferToBeExam the stringBuffer 需要检测的字符串
     * @return the boolean 字符串检测的结果
     */
    public boolean isLegalWord(StringBuffer stringBufferToBeExam) {
        boolean isLegal = true;
        int wordLength = stringBufferToBeExam.length();
        if(wordLength>3) {
            int i = 0;
            for ( ; i < 4; i++) {
                if (!(isLetterChar(stringBufferToBeExam.charAt(i)))) {
                    isLegal = false;
                    break;
                }
            }
            for ( ; i < wordLength && isLegal; i++) {
                if ((!isLetterChar(stringBufferToBeExam.charAt(i))) && (!isDigitChar(stringBufferToBeExam.charAt(i)))) {
                    isLegal = false;
                    break;
                }
            }
        }else {
            isLegal = false;
        }
        return isLegal;
    }

    /**
     * 将读入的字符组成可能的单词
     *
     * <p>可能的单词为仅由字母和数字组成的字符串</p>
     *
     * @param scanChar    the scan char
     * @param isEndOfFile the is end of file
     * @return the boolean
     */
    public boolean buildPossibleWord(char scanChar,boolean isEndOfFile) {
        isPossibleWordAvailable = false;
        if (!isEndOfFile) {
            if (isLetterChar(scanChar) || isDigitChar(scanChar)) {
                if (!isInPossibleWord) {
                    isInPossibleWord = true;
                    possibleWord = new StringBuffer(INIT_WORD_MAX_LENGTH);
                }
                possibleWord.append(scanChar);
            } else {
                if (isInPossibleWord) {
                    isInPossibleWord = false;
                    isPossibleWordAvailable = true;
                }
            }
        }else {
            isPossibleWordAvailable = isInPossibleWord;
        }
        return isPossibleWordAvailable;
    }

    /**
     * 统计各个单词独立的出现频数（不区分大小写）
     *
     * <p>按照传入的单词统计各自出现的次数，返回该传入的单词是否已统计过<em>单词不区分大小写</em></p>
     *
     * @param legalWord the legal 要统计的合法单词
     * @return the boolean 传入的单词是否已统计过
     */
    public boolean individualWordSumUp(String legalWord) {
        boolean isCounted = false;
        if (wordSumMap.containsKey(legalWord)) {
            wordSumMap.replace(legalWord,wordSumMap.get(legalWord) + 1);
            isCounted = true;
        }else {
            wordSumMap.put(legalWord,1);
        }
        return isCounted;
    }

    private boolean isLetterChar(char charToExam) {
        return (charToExam >= 'a' && charToExam <='z') || (charToExam >= 'A' && charToExam <= 'Z');
    }
    private boolean isDigitChar(char charToExam) {
        return (charToExam >= '0' && charToExam <= '9');
    }
}
