package src;

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
	static HashMap<String, Integer > hashMap=new HashMap<String,Integer>();
    public static void main(String args[])
    {
      	 String finname = new String("C:\\ccc\\s5.txt");
      	String foutname = new String("C:\\ccc\\s6.txt");
      	 //String foutname = args[1];
      	Lib lib=new Lib(finname,foutname);
      	lib.charactercount();
      	lib.wordcount();
      	lib.wordoccmax();
    }
}
