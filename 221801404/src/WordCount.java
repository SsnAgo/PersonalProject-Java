import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WordCount
{
    public static void main(String[] args)
    {
        String filePath = args[0];
        String content = Lib.readFile(filePath);
        FileWriter writer;
        //对文件字符的读取做测试，数据量过于巨大时，出现下面问题
        //并且在运行期间画面都不太稳定
        /*java.io.IOException: 磁盘空间不足。
	at java.base/java.io.FileOutputStream.writeBytes(Native Method)
	at java.base/java.io.FileOutputStream.write(FileOutputStream.java:347)
	at java.base/sun.nio.cs.StreamEncoder.writeBytes(StreamEncoder.java:242)
	at java.base/sun.nio.cs.StreamEncoder.implWrite(StreamEncoder.java:312)
	at java.base/sun.nio.cs.StreamEncoder.implWrite(StreamEncoder.java:290)
	at java.base/sun.nio.cs.StreamEncoder.write(StreamEncoder.java:131)
	at java.base/sun.nio.cs.StreamEncoder.write(StreamEncoder.java:141)
	at java.base/java.io.OutputStreamWriter.write(OutputStreamWriter.java:226)
	at java.base/java.io.Writer.write(Writer.java:249)
	at WordCount.main(WordCount.java:15)
*/
        try {
            writer = new FileWriter(args[1]);
            //for(int i = 0;i < 100000;i++)
            //{
            writer.write(content);
            // }
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int charactersNum = Lib.getCharacterNum(content);
        System.out.println("字符数："+charactersNum);
        int linesNum = Lib.getLinesNum(content);
        System.out.println("行数："+linesNum);
        int a = Lib.getWordsNum(content);
        System.out.println("单词数："+a);
        Lib lib = new Lib();
        List list = lib.sortMap();
        System.out.println(list);
    }
}