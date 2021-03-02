package Lib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class wordCounter {
    private int wordNum;
    private HashMap<String,Integer> allWordHashMap;
    private String filePath;
    private HashMap<String,Integer> topTenWordHashMap;
    private long startTime;
    private long useTime;

    public wordCounter(String filePath) {
        this.filePath = filePath;
        startTime=System.currentTimeMillis();


    }

    private void count(String filePath,int lineToThread){
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(filePath), "utf-8");
            BufferedReader in = new BufferedReader(read);
            String temp = null;
            String toStatisticsStr=null;
            int nowlineNum=0;
            List<MultiCounter> multiCounterList=new ArrayList<>();
            while ((temp = in.readLine()) != null) {
                toStatisticsStr+=temp;
                nowlineNum++;
                if(nowlineNum>=lineToThread){
                    multiCounterList.add(new MultiCounter(toStatisticsStr));
                    multiCounterList.get(multiCounterList.size()-1).start();
                    nowlineNum=0;
                    toStatisticsStr=null;
                }
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        //TODO 合并List的Map
    }

    public class MultiCounter extends Thread{
        private HashMap<String,Integer> partWordHashMap;


        public MultiCounter(String toStatisticsStr){

        }

        @Override
        public void run(){

        }

    }

}
