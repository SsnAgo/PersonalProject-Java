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

    @Test
    public void testFileToString(){
        Lib lib = new Lib();
        assertEquals("",lib.fileToString("test999.txt"));
        assertEquals("wsad我在这里等你!2312  ASDa! asd1 $a2ds @1sda @as3a",lib.fileToString("test3.txt"));
    }

    @Test
    public void testLinesCount(){
        Lib lib = new Lib();
        assertEquals(3,lib.getLinesCount("test2.txt"));
    }
    
}