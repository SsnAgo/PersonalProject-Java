package Lib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LineAndCharacterCounter extends Thread {
    private int lineNum;
    private int charNum;
    private long startTime;
    private long useTime;
    private String filePath;

    public LineAndCharacterCounter(String filePath){
        this.startTime=System.currentTimeMillis();
        this.lineNum=0;
        this.charNum=0;
        this.filePath=filePath;
    }

    @Override
    public void run() {
        try {   //输入
            InputStreamReader read = new InputStreamReader(new FileInputStream(filePath), "utf-8");
            BufferedReader in = new BufferedReader(read);
            String temp = null;
            startTime = System.currentTimeMillis();  //开始计时
            char[] chars = new char[1000];
            int whileCount = 0;
            int remain = 0;
            while ((in.read(chars)) != -1) {  //统计换行符与字符数
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
            if(whileCount == 0) whileCount++;   //将统计结果分类，计算实际结果
            if(flag == 1) charNum = (whileCount - 1 ) * 1000 + remain;
            else  charNum = (whileCount -1) * 1000 + remain - lineNum;
            in.close();
            useTime = System.currentTimeMillis() - startTime;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getCharNum() {
        return charNum;
    }
    public long getUseTime() {
        return useTime;
    }

}
