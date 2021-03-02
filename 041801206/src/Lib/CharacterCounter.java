package Lib;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CharacterCounter {
    private long characterNum;
    private long useTime;
    private long startTime;
    private int theadNum;
    private int onceReadCount;



    private int threadReadNum;
    private String filePath;

    private StringBuffer sbf=new StringBuffer();
    private HashMap<Character, Integer> characterMap;


    public CharacterCounter(String filePath, int theadNum, int onceReadCount, int threadReadNum) {
        this.startTime = System.currentTimeMillis();
        this.theadNum = theadNum;
        this.onceReadCount = onceReadCount;
        this.threadReadNum = threadReadNum;
        this.filePath=filePath;
        this.count(new File(filePath),0);
        useTime=System.currentTimeMillis()-startTime;
        System.out.println("获取字符总长度:" + this.getSbf().length()+"耗时"+useTime);
//        characterMap=new HashMap<Character, Integer>();

    }

    public StringBuffer getSbf() {
        return sbf;
    }
    public int getTheadNum() {
        return theadNum;
    }

    public void setTheadNum(int theadNum) {
        this.theadNum = theadNum;
    }

    public int getOnceReadCount() {
        return onceReadCount;
    }

    public void setOnceReadCount(int onceReadCount) {
        this.onceReadCount = onceReadCount;
    }

    public int getThreadReadNum() {
        return threadReadNum;
    }

    public void setThreadReadNum(int threadReadNum) {
        this.threadReadNum = threadReadNum;
    }

    public long getCharacterNum() {
        return characterNum;
    }

    public void setCharacterNum(long characterNum) {
        this.characterNum = characterNum;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void count(File file,int startPosition){
        List<MultiCounter> list = new ArrayList<MultiCounter>();
        for (int i=0; i < threadReadNum;i++){
            list.add(new MultiCounter(file,startPosition+i*onceReadCount*threadReadNum));
            list.get(list.size()-1).start();
        }

        for(int i=0; i<list.size();i++){
            try {
                list.get(i).join();
                sbf.append(list.get(i).getSbf());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (list.get(list.size()-1).getLastNum()==onceReadCount){
            count(file,startPosition+theadNum*threadReadNum*onceReadCount);
        }
    }

    public class MultiCounter extends Thread{
        private BufferedReader reader;
        private int start,lastNum;
        private StringBuffer sbf;

        public MultiCounter(File file,int start){
            try{
                InputStream is=new FileInputStream(file);
                this.reader=new BufferedReader(new InputStreamReader(is));

            }catch (Exception e){
                e.printStackTrace();
            }
            this.start=start;
            this.sbf=new StringBuffer();
        }

        @Override
        public void run(){
            char[] tempChars=new char[onceReadCount];
            try{
                reader.skip(this.start);
                for(int i=0;i<threadReadNum && (lastNum=reader.read(tempChars))!= -1;i++){
                sbf.append(new String(tempChars,0,lastNum));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public BufferedReader getReader() {
            return reader;
        }

        public void setReader(BufferedReader reader) {
            this.reader = reader;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getLastNum() {
            return lastNum;
        }

        public void setLastNum(int lastNum) {
            this.lastNum = lastNum;
        }

        public StringBuffer getSbf() {
            return sbf;
        }

        public void setSbf(StringBuffer sbf) {
            this.sbf = sbf;
        }

    }
}
