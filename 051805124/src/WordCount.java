package src;

import java.util.*;

/****
 * 2021.3.2
 * 主程序入口
 * @author 王少聪
 */
public class WordCount {

    public static void main(String[] args){
        if (args.length != 2){
            System.out.println("请添加输入输出的文件名");
            return;
        }
        String filePath = args[0];
        String content = Lib.getInstance().readTxt(filePath);
        //获取字符总数
        int charNum = Lib.getInstance().getCharTotalCount(content);
        //获取单词总数
        int wordNum = Lib.getInstance().getWordTotalCount(content);
        //获取有效行数
        int lineNum = Lib.getInstance().getLineCount(content);
        //获取单词出现次数
        Map<String,Integer> map = Lib.getInstance().getWordNum(content);

        System.out.println("characters:" + charNum);
        System.out.println("words:" + wordNum);
        System.out.println("lines:" + lineNum);
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String mapKey = entry.getKey();
            Integer mapValue = entry.getValue();
            System.out.println(mapKey+":"+mapValue);
        }
    }


}