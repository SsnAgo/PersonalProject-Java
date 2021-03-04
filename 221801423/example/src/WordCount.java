import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;;

public class WordCount {
    public static void main(String[] args){
    	long start=System.currentTimeMillis(); //获取开始时间
      	 String finname = new String("C:\\ccc\\s13.txt");
      	String foutname = new String("C:\\ccc\\s14.txt");
      	 //String foutname = args[1];
      	Lib lib = new Lib(finname,foutname);
      	lib.characterCount();
      	lib.wordCount();
      	lib.wordOccMax();
      //要测试的bai程序或方法du
      long end=System.currentTimeMillis(); //获取结束时间
      System.out.println("程序运行时间： "+(end-start)+"ms"); 
    }
}