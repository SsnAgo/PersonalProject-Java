import java.io.*;
import java.util.*;

import static java.lang.Character.isLetter;

/*
  类名：WordCount
  作者:黄明亮 日期:2021-3-4
  模块描述: 实现读取一个文件，调用Lib类的方法执行操作，并输出一个output文件。
  函数列表: outputFile(File inputFile, File outputFile)
*/
public class WordCount{
    public static void main(String[] args){
        if(args.length!=2){
            System.out.println("参数输入个数有误，请重新输入");
            return ;
        }else{
            File inputFile = new File(args[0]);
            File outputFile = new File(args[1]);
            Lib.outputFile(inputFile, outputFile);
        }

    }


}