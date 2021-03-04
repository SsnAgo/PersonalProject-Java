package Lib;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class WordCounter {
    private int wordNum;
    private HashMap<String,Integer> allWordHashMap;
    private String filePath;
    private int emptyLineNum;
    private List<Map.Entry<String,Integer>> maplist;
    private long startTime;
    private long useTime;

    public int getEmptyLineNum() {
        return emptyLineNum;
    }
    public List<Map.Entry<String, Integer>> getMaplist() {
        return maplist;
    }
    public long getUseTime() {
        return useTime;
    }
    public int getWordNum() {
        return wordNum;
    }

    public WordCounter(String filePath) {
        this.filePath = filePath;
        wordNum=0;
        emptyLineNum=0;
        allWordHashMap=new HashMap<String, Integer>();
        startTime=System.currentTimeMillis();
        count(filePath,10000);
        useTime=System.currentTimeMillis() - startTime;
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
                boolean flag= (temp = in.readLine()) == null;
                if(!flag){
                    toStatisticsStr.append(temp + "-");
                    if((temp + "-").length() == 1){
                        emptyLineNum++;
                    }
                }
                nowlineNum++;
                if(nowlineNum >= lineToThread || flag){
                    multiCounterList.add(new MultiCounter(toStatisticsStr));
                    multiCounterList.get(multiCounterList.size() - 1).start();
                    nowlineNum=0;
                    if(toStatisticsStr.length()!=0) toStatisticsStr.delete(0,toStatisticsStr.length()-1);
                }
                if (flag) {
                    in.close();
                    break;
                }
            }
            in.close();
            for(int i = 0;i < multiCounterList.size();i++){
                multiCounterList.get(i).join();
                mergeMap(multiCounterList.get(i).getPartWordHashMap());
            }
            sortByValue();
    }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sortByValue(){
        maplist = new ArrayList<Map.Entry<String, Integer>>(allWordHashMap.entrySet());
        Collections.sort(maplist, new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> stringIntegerEntry, Map.Entry<String, Integer> t1) {
                return t1.getValue()-stringIntegerEntry.getValue();
            }
        });
        for (Map.Entry<String,Integer> me:maplist) {
            wordNum+=me.getValue();
        }
    }

    private void mergeMap(HashMap<String,Integer> tempMap){
        tempMap.forEach((key, value) -> allWordHashMap.merge(key, value, (v1, v2) -> v1 + v2));
    }

    public class MultiCounter extends Thread{
        private HashMap<String,Integer> partWordHashMap;
        private String str;
        public HashMap<String, Integer> getPartWordHashMap() {
            return partWordHashMap;
        }

        public MultiCounter(StringBuffer toStatisticsStr){
            partWordHashMap=new HashMap<>();
           this.str=new String(toStatisticsStr);
        }

        @Override
        public void run(){
           str.toLowerCase();
           int wordLength=0;
           String tempWord=null;
           for (int i = 0;i < str.length();i++){
               int asciiNum=(int)str.charAt(i);
               if((asciiNum >=48 && asciiNum <= 57)||(asciiNum >= 97 && asciiNum <= 122)){
                   wordLength++;
               }else{
                   tempWord=str.substring(i - wordLength,i);
                   if(wordLength>3 && isWord(tempWord)){
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
            String pattern="[a-z]{4}[a-z0-9]*";
            return Pattern.matches(pattern,str);
        }
    }
}
