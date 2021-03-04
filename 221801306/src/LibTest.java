import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibTest {

    @Test
    void countCharacters() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            char ch = 'i';
            sb.append(ch);
        }
        assertEquals(128, Lib.countCharacters(sb.toString()));
    }

    @Test
    void countWords() {
        String s = "abcd,abcd\nabcd abcd\r abcd(abcd) abcd+abcd-abcd abcd";
        assertEquals(10, Lib.countWords(Lib.countFrequency(s)));
    }

    @Test
    void countLines() {
        String s = " \n \r \r\n a\na\ra\n\ra \na \ra \n\r a a \n a a \r a a \n\r";
        int lines = 9;
        assertEquals(lines, Lib.countLines(s));
    }

    @Test
    void isWord() {
        String[] trueWords = {"abcd", "abcdef", "abcd123"};
        String[] falseWords = {"a", "abc", "a1", "ab123", "123ab", "123456"};
        for (String word : trueWords) {
            assertTrue(Lib.IsWord(word));
        }
        for (String word : falseWords) {
            assertFalse(Lib.IsWord(word));
        }
    }
}