package Lib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

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
        count(filePath,10);
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
            while (true) {
               boolean flag= (temp = in.readLine())==null;
               if(flag) in.close();
                toStatisticsStr.append(temp);
                nowlineNum++;
                if(nowlineNum>=lineToThread || flag){
                    multiCounterList.add(new MultiCounter(toStatisticsStr)); System.out.println("1:"+toStatisticsStr);
                    multiCounterList.get(multiCounterList.size()-1).start();
                    nowlineNum=0;
                    toStatisticsStr.delete(0,toStatisticsStr.length()-1);
                }
                if (flag) break;
            }
            in.close();

            for(int i=0;i<multiCounterList.size();i++){
                multiCounterList.get(i).join();
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
                   wordLength++;System.out.println("wl:"+wordLength);
               }else{
                   System.out.println("kkk:"+str);
                   tempWord=str.substring(i-wordLength,i);
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
           System.out.println("isword:"+str);
            String pattern="[a-z]{4}[a-z0-9]*";
            return Pattern.matches(pattern,str);
//            if(str.length()<=3) return false;
//            else {
//                int _0=str.charAt(0);
//                int _1=str.charAt(1);
//                int _2=str.charAt(2);
//                int _3=str.charAt(3);
//                if ((_0 >= 97 && _0 <= 122) && (_1 >= 97 && _1 <= 122) && (_2 >= 97 && _2 <= 122) && (_3 >= 97 && _3 <= 122))
//                    return true;
//                else return false;
//            }
        }

        public HashMap<String, Integer> getPartWordHashMap() {
            return partWordHashMap;
        }


    }

}
