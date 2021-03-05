import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LibTest {

    //字符数量测试
    void testCharacters(){
        String sample = "tree12\nword11\nword apple23 banana word200 word8 word15\nhuman3000 sfss";
        String testStr = "";

        long a= System.currentTimeMillis();
        int loop = 10000;
        for(int i = 0; i < loop; i++) {
            testStr  += sample;
        }


        System.out.print("字符生成时间:");
        System.out.println(System.currentTimeMillis()-a+"毫秒");

        long b =  System.currentTimeMillis();

        Lib lib = new Lib();
        lib.getCharactersNum(testStr);
        System.out.print("获得字符数量时间:");
        System.out.println(System.currentTimeMillis()-b+"毫秒\n");

//        System.out.println("characters:"+lib.getCharactersNum(testStr));
    }

    //行数测试
    void testLines(){
        String sample = "tree12\nword11\nword apple23 banana word200 word8 word15\nhuman3000 sfss";
        String testStr = "";

        long a= System.currentTimeMillis();
        int loop = 10000;
        for(int i = 0; i < loop; i++) {
            testStr  += sample;
        }


        System.out.print("字符生成时间:");
        System.out.println(System.currentTimeMillis()-a+"毫秒");

        long b =  System.currentTimeMillis();


        Lib lib = new Lib();
        lib.getLinesNum(testStr);
        System.out.print("获得行数时间:");
        System.out.println(System.currentTimeMillis()-b+"毫秒\n");
//        System.out.println("Lines:"+lib.getLines(testStr));
    }

    //单词数测试
    void testWords(){
        String sample = "tree12\nword11\nword apple23 banana word200 word8 word15\nhuman3000 sfss";
        String testStr = "";

        long a= System.currentTimeMillis();
        int loop = 10000;
        for(int i = 0; i < loop; i++) {
            testStr  += sample;
        }


        System.out.print("字符生成时间:");
        System.out.println(System.currentTimeMillis()-a+"毫秒");

        long b = System.currentTimeMillis();

        Lib lib = new Lib();
        lib.getWordNum(testStr);

        System.out.print("获得单词数时间:");
        System.out.println(System.currentTimeMillis()-b+"毫秒\n");
//        System.out.println("words:"+lib.getWordNum(testStr));
    }

    //词频测试
    void testSort() {
        String sample = "tree12\nword11\nword apple23 banana word200 word8 word15\nhuman3000 sfss";
        String testStr = "";

        long a= System.currentTimeMillis();
        int loop = 10000;
        for(int i = 0; i < loop; i++) {
            testStr  += sample;
        }


        System.out.print("字符生成时间:");
        System.out.println(System.currentTimeMillis()-a+"毫秒");

        long b = System.currentTimeMillis();
        Lib lib = new Lib();

        List<Map.Entry<String, Integer>> topTenWords = new ArrayList<Map.Entry<String, Integer>>();
        topTenWords = lib.sortWordMap();
        System.out.print("获得十大单词时间:");
        System.out.println(System.currentTimeMillis()-b+"毫秒\n");

        for(Map.Entry<String,Integer> map : topTenWords) {
            System.out.println(map.getKey()+":"+map.getValue());
        }
    }
}
