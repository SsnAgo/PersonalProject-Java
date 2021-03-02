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
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(filePath), "utf-8");
            BufferedReader in = new BufferedReader(read);
            String temp = null;
            long start = System.currentTimeMillis();
            while ((temp = in.readLine()) != null) {
                lineNum++;
                charNum += temp.length();
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.useTime=System.currentTimeMillis()-startTime;
        System.out.println("行数字符统计完成，耗时："+useTime+"毫秒，字符数："
                +charNum+",行数："+lineNum);
    }


    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getCharNum() {
        return charNum;
    }

    public void setCharNum(int charNum) {
        this.charNum = charNum;
    }


    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }
}
