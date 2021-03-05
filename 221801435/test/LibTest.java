import org.junit.jupiter.api.Test;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;

class LibTest {

    @Test
    public void testGetAsciiCount(){
        Lib lib = new Lib();
        assertEquals(36,lib.getAsciiCount(lib.fileToString("test0.txt")));
        assertEquals(0,lib.getAsciiCount(lib.fileToString("test1.txt")));
        assertEquals(39,lib.getAsciiCount(lib.fileToString("test3.txt")));
    }

    @Test
    public void testGetWordsCount(){
        Lib lib = new Lib();
        assertEquals(0,lib.getWordsCount(lib.fileToString("test1.txt")));
        assertEquals(0,lib.getWordsCount(lib.fileToString("test0.txt")));
        assertEquals(2,lib.getWordsCount(lib.fileToString("test3.txt")));
    }


}