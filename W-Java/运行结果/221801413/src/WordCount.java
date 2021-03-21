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
        //判断命令行参数正确性
        if(args.length == 2) {
            WordCount wordCount = new WordCount(args[0],args[1]);
            Lib lib = new Lib();

            //读入的字符串存入readStr中
            long a = System.currentTimeMillis();
            wordCount.initWordCount();

            //数据处理，获得字符数、行数、单词数
            long characters = lib.getCharactersNum(wordCount.readStr);
            long words = lib.getWordNum(wordCount.readStr);
            int lines = lib.getLinesNum(wordCount.readStr);
            List<Map.Entry<String, Integer>> topTenWords = new ArrayList<Map.Entry<String, Integer>>();
            topTenWords = lib.sortWordMap();

            //获取十大频率最高单词
            String outMessage = lib.outMessage(words,lines,characters,topTenWords);
            lib.writeFile(outMessage,wordCount.outPut);

        }
        else {
            System.out.println("参数错误，请重新输入！");
        }
    }
}
