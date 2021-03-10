import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lib {
    private int chcount;   //字符总数
    private int wordcount;	//单词总数
    private int linecount;	//行总数
    String finname;
    String foutname;
    Map<String,  Integer > hashMap = new HashMap<String, Integer>();
    
    Lib(String finname, String foutname) {
    	chcount = 0;   //字符总数
        wordcount = 0;	//单词总数
        linecount = 0;	//行总数
        this.finname = finname;
        this.foutname = foutname;
    }
	void wordOccMax() {
			
    	List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,  Integer>>(hashMap.entrySet()); //将map的entryset放入list集合
		//对list进行排序，并通过Comparator传入自定义的排序规则
		Collections.sort(list, new Comparator<Map.Entry<String,  Integer>>() {
			@Override
			public int compare(Map.Entry<String,  Integer> o1,  Map.Entry<String,  Integer> o2) {
				int re = o2.getValue().compareTo(o1.getValue()); //重写排序规则，小于0表示升序，大于0表示降序
				if (re != 0) {
					return re;
					}else {
						return o1.getKey().compareTo(o2.getKey());
					}
			}
		});
		if (list.size() >= 10) {
			for (int j = 0; j < 10; j++) {
				String str = list.get(j).getKey() + ": " + list.get(j).getValue();
				outFile(foutname, str);
	        }    
		}else {
			for (int j = 0; j < list.size(); j++) {
				String str = list.get(j).getKey() + ": " + list.get(j).getValue();
				outFile(foutname, str);
	        }
		}
    }
    void outFile(String fname, String str) {
    	try{
			BufferedWriter out = new BufferedWriter(new FileWriter(fname, true));
			out.write(str);
			out.newLine();
			out.close();
		}
    	catch (ArrayIndexOutOfBoundsException ex) { 
         System.out.println("\n File error"); 
        }
    	catch (IOException ex) { 
          System.out.println("File Does Not Found in given Directory. "); 
        } 
    }
    
    int characterCount() {
    	try{
    		BufferedReader br = new BufferedReader(new FileReader(finname));
	         int i = 0;
	         char ch;
	         int canbetransferred = 0;//可能为\n\r
	         //每个单词一次循环
	         while ((i = br.read()) != -1) {
	        	 ch = (char)i;
	        	 chcount++;  //字符统计
	        	 if ((i == 92)) {
        			 canbetransferred = 1;
        		}
	        	 if (canbetransferred == 1) {
	        		 canbetransferred = 0;
	        		 if (ch == 'n' || ch == 'r') {
	        			 chcount--;
	        			 continue;
	        		 }
	        	 }
	         } 
	         br.close(); 
	         outFile(foutname, "characters: "+chcount);
        }
    	catch (ArrayIndexOutOfBoundsException ex) { 
         System.out.println("\nPlease Enter the File Name in Command Line Argument. \n"+ 
         "For Example :- java WordCount File1.txt File2.txt"); 
        } 
        catch (IOException ex) { 
          System.out.println("File Does Not Found in given Directory. "); 
        } 
    	return chcount;
    }
    
    int wordCount() {
    	try 
        {
    		 BufferedReader br = new BufferedReader(new FileReader(finname));
	         int i = 0;
	         char ch;

	         int canbeword = 0; 	//每次增加，当最后大于四的时候成立
	         int canbetransferred = 0;//可能为\n\r
	         int linechcount = 0;    //为空不能算一行
	         String tempword = "";
	         //每个单词一次循环
	         while ((i = br.read()) != -1) {
	        	 
	        	 ch = (char)i;
	        	 if (i == 10 || i == 13) {
	        		 /*
	        		  * 如果linechcount =  = 0，则说明换行前为空，空换行无效
	        		  * 换行后把linechcount置为0
	        		  */
	        		 if (linechcount != 0) {
	        			 linecount++;
	        		 }
	        		 linechcount = 0;
	        		 if (canbeword >=  4) {
	        			 if (hashMap.containsKey(tempword)) {
		       				 Integer hvalue = ((Integer)hashMap.get(tempword))+1;
		       				 hashMap.put(tempword,  hvalue);
		       			 }else {
		       				 hashMap.put(tempword,  1);
		       			 }
	          			 wordcount++;
	          		 }
	      			 tempword = "";
	      			 canbeword = 0;
	      			 continue;
	        	 }
	        	 if (ch == ' ') {
	        		 if (canbeword >=  4) {
	        			 if (hashMap.containsKey(tempword)) {
		       				 Integer hvalue = ((Integer)hashMap.get(tempword))+1;
		       				 hashMap.put(tempword,  hvalue);
		       			 }else {
		       				 hashMap.put(tempword,  1);
		       			 }
	          			 wordcount++;
	          		 }
	      			 tempword = "";
	      			 canbeword = 0;
	      			 continue;
	        	 }
	        	 if (canbetransferred == 1) {
	        	/* 处理\n\r\t
	        	 * 一、若是下一个为'r'或'n'或't'，则说明'\'的作用是用于转义
	        	 * 1、将canbetransferred重置
	        	 * 2、字符总数chcount--，因为\之前已经加过1次了
	        	 * 3、重新读取下一个文件字符。
	        	 * 
	        	 * 二、若是下一个不为'r'或'n'，则说明'\'的作用是用于分隔
	        	 * 1、将canbetransferred重置
	        	 * 2、将进入下一层判断首字母
	        	 */
	        		 canbetransferred = 0;
	        		 if (ch == 'n') {
	        			 if (linechcount != 0) {
		        			 linecount++;
		        		 }
		        		 linechcount = 0;
	        			 continue;
	        		 }
	        		 
	        		 if (ch == 'r' || ch == 't') {
	        			 continue;
	        		 }
	        		 linechcount++;
	        	 }
	        	 if (!Character.isLetter(ch) && !Character.isDigit(ch)) {
	        		 /* 分割符
	        		  * 一、ch为'\'
	 	        	 * 1、判断tempword是否可为word（通过canbeword是否为4），若是则加入hashmap,  且wordcount++;
	 	        	 * 2、将tempword、canbeword重置
	 	        	 * 3、将canbetransferred = 1;
	 	        	 * 4、continue，进入下一层判断是否为'r'或'n'
	 	        	 * 
	 	        	 * 二、ch不为'\'
	 	        	 * 1、判断tempword是否可为word（通过canbeword是否为4），若是则加入hashmap,  且wordcount++;
	 	        	 * 2、将tempword、canbeword重置
	 	        	 * 3、continue，进入下一层判断是否为'r'或'n'
	 	        	 */
	        		 
	        		 if (canbeword >=  4) {
	        			 if (hashMap.containsKey(tempword)) {
		       				 Integer hvalue = ((Integer)hashMap.get(tempword))+1;
		       				 hashMap.put(tempword, hvalue);
		       			 }else {
		       				 hashMap.put(tempword, 1);
		       			 }
	        			 wordcount++;
	        		 }
        			 tempword = "";
        			 canbeword = 0;
        			 if ((i == 92)) {
	        			 canbetransferred = 1;
	        			 continue;
	        		}
        			 linechcount++;
	        	 }else if (Character.isLetter(ch)) {
	        		 canbeword++;
	        		 linechcount++;
	        		 if (Character.isUpperCase(ch)) {
	        			 ch = Character.toLowerCase(ch);
	        		 }
	        		 tempword += ch;
	        	 }else if (Character.isDigit(ch)) {
	        		 linechcount++;
	        		 if (canbeword < 4) {
	        			 canbeword = 0;
	        			 tempword = "";
	        		 }else {
	        			 tempword += ch;
	        		 }
	        	 }
	         } 
	         if (canbeword >=  4) {
	        	 if (hashMap.containsKey(tempword)) {
       				 Integer hvalue = ((Integer)hashMap.get(tempword))+1;
       				 hashMap.put(tempword,  hvalue);
       			 }else {
       				 hashMap.put(tempword,  1);
       			 }
    			 wordcount++;
    		 }
	         if (linechcount > 0) {
	        	 linecount++;
	         }
	         br.close(); 
	         outFile(foutname, "words: "+wordcount+'\n'+"lines: "+linecount);

        } 
        catch (ArrayIndexOutOfBoundsException ex) { 
	        System.out.println("\nPlease Enter the File Name in Command Line Argument. \n"+ 
	        "For Example :- java WordCount File1.txt File2.txt"); 
        } 
        catch (IOException ex) { 
        	System.out.println("File Does Not Found in given Directory. "); 
        }
		 return wordcount;
    }
}
