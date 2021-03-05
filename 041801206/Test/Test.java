import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Test {

    /*字符行数统计单元测试*/
    @org.junit.Test
    public void t1(){
        int lineNum=0;
        int charNum=0;
        String path="C:\\Users\\cmy\\Desktop\\input.txt";
        try {   //输入
            InputStreamReader read = new InputStreamReader(new FileInputStream(path), "utf-8");
            BufferedReader in = new BufferedReader(read);
            String temp = null;
            char[] chars = new char[1000];
            int whileCount = 0;
            int remain = 0;
            while ((in.read(chars)) != -1) {
                remain = 0;
                for (int i = 0;i < chars.length;i++){
                    if (chars[i] == '\n') lineNum++;
                    if((int)chars[i] == 0) {
                        remain = i;
                        break;
                    }
                }
                whileCount++;
            }

            int flag = 0;
            if(lineNum == 0 && whileCount != 0){
                lineNum += 1;
                flag = 1;
            }

            if(whileCount == 0) whileCount++;
            if(flag == 1) charNum = (whileCount - 1 ) * 1000 + remain;
            else  charNum = (whileCount -1) * 1000 + remain - lineNum;
            if(lineNum != 0 ){
                lineNum += 1;
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals(charNum,5);
        Assert.assertEquals(lineNum,2);
    }
}
