/**
 * 对单词校验、统计的类
 */
public class WordProcessor {
    private boolean isInPossibleWord = false;
    private final int INIT_WORD_MAX_LENGTH=200;
    private boolean isPossibleWordAvailable;
    /**
     * The Possible word.
     */
    public StringBuffer possibleWord;


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
     * @param stringToBeExam the string 需要检测的字符串
     * @return the boolean 字符串检测的结果
     */
    public boolean isLegalWord(String stringToBeExam) {
        boolean isLegal = true;
        int wordLength = stringToBeExam.length();
        if(wordLength>3) {
            int i = 0;
            for ( ; i < 4; i++) {
                if (!(isLetterChar(stringToBeExam.charAt(i)))) {
                    isLegal = false;
                    break;
                }
            }
            for ( ; i < wordLength && isLegal; i++) {
                if ((!isLetterChar(stringToBeExam.charAt(i))) && (!isDigitChar(stringToBeExam.charAt(i)))) {
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
    private boolean isLetterChar(char charToExam) {
        return (charToExam >= 'a' && charToExam <='z') || (charToExam >= 'A' && charToExam <= 'Z');
    }
    private boolean isDigitChar(char charToExam) {
        return (charToExam >= '0' && charToExam <= '9');
    }
}
