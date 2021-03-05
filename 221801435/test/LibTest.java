import org.junit.jupiter.api.Test;
import org.junit.runners.JUnit4;

import java.util.*;

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
        assertEquals(0,lib.getLinesCount("test999.txt"));
    }

    @Test
    public void testGetFrequentWords(){
        List<Map.Entry<String,Integer>> list = null;
        Map<String,Integer> map = new HashMap<>();
        map.put("asda3",1);
        map.put("asdd3",1);
        map.put("asdff1",1);
        map.put("asdas",1);
        map.put("asdd",2);
        list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue()!=o2.getValue()){
                    return o1.getValue().compareTo(o2.getValue());
                }else{
                    return -1*o1.getKey().compareTo(o2.getKey());
                }
            }
        });
        Collections.reverse(list);
        Lib lib = new Lib();
        assertEquals(list,lib.getMostFrequentlyWords(lib.fileToString("test4.txt")));
    }

    @Test
    public void testWordCount(){
        WordCount.main(new String[]{"input.txt"});
        WordCount.main(new String[]{"input.txt","output.txt","aaa.txt"});
        WordCount.main(new String[]{"input3333.txt","output.txt"});
        WordCount.main(new String[]{"input.txt","output.txt"});
    }
}