package Lib;

import java.util.*;

public class Output {
    private int characterNum;
    private int lineNum;
    private int wordNum;
    private String outPutFilePath;
    private List<String> topTen;

    public Output(int characterNum, int lineNum, int wordNum,List<Map.Entry<String,Integer>> mapList, String outPutFilePath) {
        this.characterNum = characterNum;
        this.lineNum = lineNum;
        this.wordNum = wordNum;
        this.outPutFilePath = outPutFilePath;
        topTen=new ArrayList<>();
        int count=0;
        for (Map.Entry<String,Integer> me:mapList) {
           topTen.add(me.getKey()+": "+me.getValue());
        }
    }

    public void show(){
        System.out.println("character: "+characterNum);
        System.out.println("word: "+wordNum);
        System.out.println("line: "+lineNum);
        for (int i=0;i<10;i++){
            System.out.println(topTen.get(i));
        }
    }
}
