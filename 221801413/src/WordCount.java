import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordCount
{
    public String inPut = "";
    public String outPut = "";
    public String readStr = "";

    /**
     * 构造函数
     *
     * @ param inPut,outPut
     * @ return
     * */
    public WordCount(String inPut, String outPut) {
        this.inPut = inPut;
        this.outPut =outPut;
    }

    /**
     * 获取读入的字符串
     *
     * @ param
     * @ return
     * */
    public void initWordCount(){
        this.readStr = Lib.readFile(this.inPut);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
//        if(args.length == 2) {
//            Lib lib= new Lib();
//            String inPut = "input.txt";
//            String outPut = "output.txt";
//
//            WordCount wordCount = new WordCount(inPut,outPut);
//            wordCount.initWordCount();
//
//            lib.initWordMap(wordCount.readStr);
//
//
//            System.out.println("characters:"+lib.getCharactersNum(wordCount.readStr));
//            System.out.println("wordsNum:"+lib.getWordNum(wordCount.readStr));
//            System.out.println("lines:"+lib.getLines(wordCount.readStr));
//
//            List<Map.Entry<String, Integer>> topTenWords = new ArrayList<Map.Entry<String, Integer>>();
//            topTenWords = lib.sortWordMap();
//
//            for(Map.Entry<String,Integer> map : topTenWords) {
//                System.out.println(map.getKey()+":"+map.getValue());
//            }
//
////        String outMessage = lib.outMessage(lib.getWordNum(str),lib.getLines(str),lib.getCharactersNum(str), topTenWords);
////        lib.writeFile(outMessage,outPut);
//        }
//        else {
//            System.out.println("参数错误，请重新输入");
//        }

        Lib lib= new Lib();
        String inPut = "input.txt";
        String outPut = "output.txt";

        WordCount wordCount = new WordCount(inPut,outPut);
        wordCount.initWordCount();

        lib.initWordMap(wordCount.readStr);


        System.out.println("characters:"+lib.getCharactersNum(wordCount.readStr));
        System.out.println("wordsNum:"+lib.getWordNum(wordCount.readStr));
        System.out.println("lines:"+lib.getLines(wordCount.readStr));

        List<Map.Entry<String, Integer>> topTenWords = new ArrayList<Map.Entry<String, Integer>>();
        topTenWords = lib.sortWordMap();

        for(Map.Entry<String,Integer> map : topTenWords) {
            System.out.println(map.getKey()+":"+map.getValue());
        }
    }
}
