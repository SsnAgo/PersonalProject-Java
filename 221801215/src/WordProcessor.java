/**
 * 对单词校验、统计的类
 */
public class WordProcessor {
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
                if (!(Character.isLetter(stringToBeExam.charAt(i)))) {
                    isLegal = false;
                    break;
                }
            }
            for ( ; i < wordLength && isLegal; i++) {
                if ((!Character.isLetter(stringToBeExam.charAt(i))) && (!Character.isDigit(stringToBeExam.charAt(i)))) {
                    isLegal = false;
                    break;
                }
            }
        }else {
            isLegal = false;
        }
        return isLegal;
    }
}
