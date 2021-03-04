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

    @Test
    void sortFrequency() {
        //构造一个包含字母、数字的字符串
        String str = "abc abcd abcd abcd123 abcd123 123ab 123456 Lakers Champion GOGO gogo where amazing happens nice\n";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(str);
        }
        String test = "abcd: 2000\nabcd123: 2000\ngogo: 2000\namazing: 1000\n";
        test += "champion: 1000\nhappens: 1000\nlakers: 1000\nnice: 1000\nwhere: 1000";
        assertEquals(test, Lib.sortFrequency(Lib.countFrequency(sb.toString()), 10));
    }

    @Test
    void getFileAndOutput() {
        String s = "abcd,abcd\nabcd abcd\r abcd(abcd) abcd+abcd-abcd abcd";
        String path = "test.txt";
        Lib.outputInfo(path, s);
        assertEquals(s, Lib.getFile(path));
    }

    @Test
    void answerBuilder() {
        String s="characters: 80000\nwords: 10000\nlines: 1000\n";
        String test = "abcd: 2000\nabcd123: 2000\ngogo: 2000\namazing: 1000\n";
        test += "champion: 1000\nhappens: 1000\nlakers: 1000\nnice: 1000\nwhere: 1000";
        assertEquals(s+test,Lib.answerBuilder(80000,10000,1000,test));
    }
}