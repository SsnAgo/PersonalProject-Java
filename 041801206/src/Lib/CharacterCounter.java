package Lib;

import java.io.*;
import java.util.*;

public class CharacterCounter {
    private int characterNum;
    private int lineNum;
    private long useTime;
    private long startTime;
    private int theadNum;
    private int onceReadCount;
    private int threadReadNum;
    private String filePath;

    private StringBuffer sbf=new StringBuffer();
    private HashMap<Character,Integer> hashMap;

    public CharacterCounter(String filePath, int theadNum, int onceReadCount, int threadReadNum) {
        this.startTime = System.currentTimeMillis();
        this.theadNum = theadNum;
        this.onceReadCount = onceReadCount;
        this.threadReadNum = threadReadNum;
        this.filePath=filePath;
        this.lineNum=0;
        hashMap=new HashMap<>();

        this.count(new File(filePath),0);
        useTime=System.currentTimeMillis()-startTime;
        this.characterNum=this.getSbf().length()-lineNum;
        System.out.println("获取字符总长度:" + this.characterNum +"  行数："+lineNum+"  耗时"+useTime);
    }

    public StringBuffer getSbf() {
        return sbf;
    }

    public void count(File file,int startPosition){
        List<MultiCounter> list = new ArrayList<MultiCounter>();
        for (int i=0; i < threadReadNum;i++){
            list.add(new MultiCounter(file,startPosition+i*onceReadCount*threadReadNum));
            list.get(list.size()-1).start();
        }

        System.out.println("线程数："+list.size());
        for(int i=0; i<list.size();i++){
            try {
                list.get(i).join(); System.out.println(i+"长度："+list.get(i).getSbf().length());
                sbf.append(list.get(i).getSbf());
                this.lineNum+=list.get(i).get_nNum();
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
        private int _nNum;

        @Override
        public String toString() {
            return "线程情况  MultiCounter{" +
                    "start=" + start +
                    ", sbf=" + sbf +
                    ", _nNum=" + _nNum +
                    '}';
        }

        public int get_nNum() {
            return _nNum;
        }

        public MultiCounter(File file, int start){
            try{
                InputStream is=new FileInputStream(file);
                this.reader=new BufferedReader(new InputStreamReader(is));
            }catch (Exception e){
                e.printStackTrace();
            }
            this.start=start;
            this.sbf=new StringBuffer();
            this._nNum=0;
        }
        @Override
        public void run(){
            char[] tempChars=new char[onceReadCount];
            try{
                reader.skip(this.start);
                for(int i=0;i<threadReadNum && (lastNum=reader.read(tempChars))!= -1;i++){
                    lineCounter(tempChars);
                  sbf.append(new String(tempChars,0,lastNum));

                }
                System.out.println("\nsbf内容："+sbf);


            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println("位置:"+start); System.out.println("添加:"+sbf);
//            this.toString();
        }

        private void lineCounter(char[] chars){
            for (int i=0; i<chars.length;i++){
                if (chars[i]=='\n') this._nNum++;
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
