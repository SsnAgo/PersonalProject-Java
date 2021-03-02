package Lib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordCounter {
    private int wordNum;
    private HashMap<String,Integer> allWordHashMap;
    private String filePath;
    private HashMap<String,Integer> topTenWordHashMap;
    private long startTime;
    private long useTime;

    public WordCounter(String filePath) {
        this.filePath = filePath;
        startTime=System.currentTimeMillis();
        count(filePath,4);
        System.out.println("耗时："+ (System.currentTimeMillis()-startTime));
    }

    private void count(String filePath,int lineToThread){
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(filePath), "utf-8");
            BufferedReader in = new BufferedReader(read);
            String temp = null;
            StringBuffer toStatisticsStr=new StringBuffer();
            int nowlineNum=0;
            List<MultiCounter> multiCounterList=new ArrayList<>();
            while ((temp = in.readLine()) != null) {
                System.out.println("1:"+temp);
                toStatisticsStr.append(temp);
                nowlineNum++;
                if(nowlineNum>=lineToThread || temp==null){
                    multiCounterList.add(new MultiCounter(toStatisticsStr));
                    multiCounterList.get(multiCounterList.size()-1).start();
                    nowlineNum=0;
                    toStatisticsStr.delete(0,toStatisticsStr.length()-1);
                }
            }
            in.close();

            for(int i=0;i<multiCounterList.size();i++){
                System.out.println(multiCounterList.get(i).getPartWordHashMap().toString());
            }


    }catch (Exception e){
            e.printStackTrace();
        }


        //TODO 合并List的Map
    }

    public class MultiCounter extends Thread{

        private HashMap<String,Integer> partWordHashMap;
        private String str;

        public MultiCounter(StringBuffer toStatisticsStr){
            partWordHashMap=new HashMap<>();
           this.str=new String(toStatisticsStr);
        }

        @Override
        public void run(){
           str.toLowerCase();
           int wordLength=0;
           String tempWord=null;
           for (int i=0;i<str.length();i++){
               int asciiNum=(int)str.charAt(i);
               if((asciiNum>=30&&asciiNum<=39)||(asciiNum>=97&&asciiNum<=122)){
                   wordLength++;
               }else{
                   System.out.println(tempWord);
                   tempWord=str.substring(i-wordLength,i-1);
                   if(isWord(tempWord)){
                       if(partWordHashMap.containsKey(tempWord)){
                           partWordHashMap.put(tempWord,partWordHashMap.get(tempWord)+1);
                       }else{
                           partWordHashMap.put(tempWord,1);
                       }
                   }else{
                       tempWord=null;
                   }
                   wordLength=0;
               }
           }


        }

        private boolean isWord(String str){
            int _0=str.charAt(0);
            int _1=str.charAt(1);
            int _2=str.charAt(2);
            int _3=str.charAt(3);
            if((_0>=97&&_0<=122) && (_1>=97&&_1<=122) && (_2>=97&&_2<=122) && (_3>=97&&_3<=122) ) return true;
            else return false;
        }

        public HashMap<String, Integer> getPartWordHashMap() {
            return partWordHashMap;
        }


    }

}
