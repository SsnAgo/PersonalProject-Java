package Lib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Output {
    private int characterNum;
    private int lineNum;
    private int wordNum;
    private int emptyNum;
    private String outPutFilePath;
    private List<String> topTen;


    public Output(int characterNum, int lineNum, int wordNum,int emptyLine,int endWithN,
                  List<Map.Entry<String,Integer>> mapList, String outPutFilePath) {
        this.characterNum = characterNum;
        if(emptyLine==1 && lineNum == 0) emptyLine = 0;  
        this.lineNum = lineNum - emptyLine - endWithN;
        this.wordNum = wordNum;
        this.outPutFilePath = outPutFilePath;
        this.emptyNum=emptyLine;
        topTen=new ArrayList<>();
        int count=0;
        for (Map.Entry<String,Integer> me:mapList) {
           topTen.add(me.getKey()+": "+me.getValue());
        }
    }

    public void writeToOutPut(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(outPutFilePath));
            out.write("character: "+characterNum+"\n");
            out.write("word: "+wordNum+"\n");
            out.write("line: "+lineNum+"\n");
            for (int i=0;i<(topTen.size()>=10?10:topTen.size());i++){
                out.write(topTen.get(i)+"\n");
            }
            out.close();
            System.out.println("文件输出成功！");
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public void show(){
        System.out.println("character: "+characterNum);
        System.out.println("word: "+wordNum);
        System.out.println("line: "+lineNum);
        for (int i=0;i<(topTen.size()>=10?10:topTen.size());i++){
            System.out.println(topTen.get(i));
        }
    }
}
