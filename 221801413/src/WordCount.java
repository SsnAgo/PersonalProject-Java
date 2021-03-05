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


        long a= System.currentTimeMillis();
        LibTest libTest = new LibTest();

        libTest.testLines();
        libTest.testCharacters();
        libTest.testWords();
        libTest.testSort();


        System.out.print("程序结束时间");
        System.out.println(System.currentTimeMillis()-a+"毫秒");
    }
}
