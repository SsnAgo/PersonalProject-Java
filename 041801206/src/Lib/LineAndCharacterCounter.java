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
            startTime = System.currentTimeMillis();
            char[] chars=new char[1000];
            int whileCount=0;
            int remain=0;
            while ((in.read(chars)) != -1) {
                remain=0;
                for (int i=0;i<chars.length;i++){
                    if((int)chars[i]==0) {
                        remain=i;
                        break;
                    }
                    if (chars[i]=='\n') lineNum++;
                }
                whileCount++;
            }
            charNum=(whileCount-1)*1000+remain-lineNum;
            if(lineNum!=0){
                lineNum+=1;
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
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
