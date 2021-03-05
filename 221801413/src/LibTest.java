import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LibTest {

    //字符数量测试
    void testCharacters(){
        String sample = "tree12\nword11\nword apple23 banana word200 word8 word15\nhuman3000 sfss";
        String testStr = "";

        int loop = 1000;
        for(int i = 0; i < loop; i++) {
            testStr  += sample;
        }

        Lib lib = new Lib();
        System.out.println(lib.getCharactersNum(testStr));
    }

    //行数测试
    void testLines(){
        String sample = "tree12\nword11\nword apple23 banana word200 word8 word15\nhuman3000 sfss";
        String testStr = "";

        int loop = 1000;
        for(int i = 0; i < loop; i++) {
            testStr  += sample;
        }

        Lib lib = new Lib();
        System.out.println(lib.getLines(testStr));
    }

    //单词数测试
    void testWords(){
        String sample = "tree12\nword11\nword apple23 banana word200 word8 word15\nhuman3000 sfss";
        String testStr = "";

        int loop = 1000;
        for(int i = 0; i < loop; i++) {
            testStr  += sample;
        }

        Lib lib = new Lib();
        System.out.println(lib.getWordNum(testStr));
    }

    //词频测试
    void testSort() {
        String sample = "tree12\nword11\nword apple23 banana word200 word8 word15\nhuman3000 sfss";
        String testStr = "";

        int loop = 1000;
        for(int i = 0; i < loop; i++) {
            testStr  += sample;
        }

        Lib lib = new Lib();
        lib.initWordMap(testStr);

        List<Map.Entry<String, Integer>> topTenWords = new ArrayList<Map.Entry<String, Integer>>();
        topTenWords = lib.sortWordMap();

        for(Map.Entry<String,Integer> map : topTenWords) {
            System.out.println(map.getKey()+":"+map.getValue());
        }
    }
}
