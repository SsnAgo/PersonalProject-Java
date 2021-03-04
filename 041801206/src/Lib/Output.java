package Lib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Output {
    private int characterNum;
    private int lineNum;
    private int wordNum;
    private String outPutFilePath;
    private List<Map.Entry<String,Integer>> topTen;

    public Output(int characterNum, int lineNum, int wordNum, String outPutFilePath) {
        this.characterNum = characterNum;
        this.lineNum = lineNum;
        this.wordNum = wordNum;
        this.outPutFilePath = outPutFilePath;
    }

    private void show(){
        System.out.println();
    }
}
