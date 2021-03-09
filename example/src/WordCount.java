package wordcount;

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
      	 wordmanage(finname,foutname);
      	System.out.println("asda");
      	mapmanage(foutname);
      	
    }
    public static void mapmanage(String foutname) {
    	List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet()); //将map的entryset放入list集合
		//对list进行排序，并通过Comparator传入自定义的排序规则
		Collections.sort(list,new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				int re = o2.getValue().compareTo(o1.getValue()); //重写排序规则，小于0表示升序，大于0表示降序
				if(re!=0){
					return re;
					}else {
						return o1.getKey().compareTo(o2.getKey());
					}
			}
		});
		for (int i = 0; i < list.size(); i++) {
			String str=list.get(i).getKey() + ": " + list.get(i).getValue();
			System.out.println(str);
			outfile(foutname,str);
        }    
		
    }
    public static void outfile(String foutname,String str) {
    	try 
        {
			BufferedWriter out = new BufferedWriter(new FileWriter(foutname,true));
			out.write(str);
			out.newLine();
			out.close();
		}
		catch(ArrayIndexOutOfBoundsException ex) 
        { 
         System.out.println("\nPlease Enter the File Name in Command Line Argument. \n"+ 
         "For Example :- java WordCount File1.txt File2.txt"); 
        } 
        catch(IOException ex) 
        { 
          System.out.println("File Does Not Found in given Directory. "); 
        } 
    }
    public static void wordmanage(String finname,String foutname) {
    	try 
        {
    		 FileInputStream fr=new FileInputStream(new File(finname));
	         int i=0;
	         char ch;
	         int chcount=0;   //字符总数
	         int wordcount=0;	//单词总数
	         int linecount=0;	//行总数
	         int canbeword=0; 	//每次增加，当最后大于四的时候成立
	         int canbetransferred=0;//可能为\n\r
	         int linechcount=0;    //为空不能算一行
	         String tempword ="";
	         //每个单词一次循环
	         while ((i=fr.read()) != -1){
	        	 
	        	 ch = (char)i;
	        	 chcount++;  //字符统计
	        	 if(ch=='\n') {
	        		 /*
	        		  * 如果linechcount==0，则说明换行前为空，空换行无效
	        		  * 换行后把linechcount置为0
	        		  */
	        		 if(linechcount!=0) {
	        			 linecount++;
	        		 }
	        		 linechcount=0;
	        	 }
	        	 if (canbetransferred == 1){
	        	/* 处理\n\r
	        	 * 一、若是下一个为'r'或'n'，则说明'\'的作用是用于转义
	        	 * 1、将canbetransferred重置
	        	 * 2、字符总数chcount--，因为\之前已经加过1次了
	        	 * 3、重新读取下一个文件字符。
	        	 * 
	        	 * 二、若是下一个不为'r'或'n'，则说明'\'的作用是用于分隔
	        	 * 1、将canbetransferred重置
	        	 * 2、将进入下一层判断首字母
	        	 */
	        		 canbetransferred=0;
	        		 if (ch == 'n'){
	        			 chcount--;
	        			 if(linechcount!=0) {
		        			 linecount++;
		        		 }
		        		 linechcount=0;
	        			 continue;
	        		 }
	        		 
	        		 linechcount++;//不是换行符就++
	        		 if (ch == 'r'){
	        			 chcount--;
	        			 continue;
	        		 }

	        	 }
	        	 if (!Character.isLetter(ch) && !Character.isDigit(ch)){
	        		 /* 分割符
	        		  * 一、ch为'\'
	 	        	 * 1、判断tempword是否可为word（通过canbeword是否为4），若是则加入hashmap, 且wordcount++;
	 	        	 * 2、将tempword、canbeword重置
	 	        	 * 3、将canbetransferred=1;
	 	        	 * 4、continue，进入下一层判断是否为'r'或'n'
	 	        	 * 
	 	        	 * 二、ch不为'\'
	 	        	 * 1、判断tempword是否可为word（通过canbeword是否为4），若是则加入hashmap, 且wordcount++;
	 	        	 * 2、将tempword、canbeword重置
	 	        	 * 3、continue，进入下一层判断是否为'r'或'n'
	 	        	 */
	        		 if ((i == 92)){
	        			 canbetransferred = 1;
	        		}
	        		 if (canbeword >= 4) {
	        			 if(hashMap.containsKey(tempword)) {
	        				 Integer hvalue = ((Integer)hashMap.get(tempword))+1;
	        				 hashMap.put(tempword, hvalue);
	        			 }else {
	        				 hashMap.put(tempword, 1);
	        			 }
	        			 wordcount++;
	        		 }
        			 tempword="";
        			 canbeword=0;
	        	 }else if (Character.isLetter(ch)){
	        		 canbeword++;
	        		 linechcount++;
	        		 if(Character.isUpperCase(ch)) {
	        			 ch = Character.toLowerCase(ch);
	        		 }
	        		 tempword+=ch;
	        	 }else if(Character.isDigit(ch)) {
	        		 linechcount++;
	        		 if (canbeword < 4) {
	        			 canbeword=0;
	        			 tempword="";
	        		 }else {
	        			 tempword+=ch;
	        		 }
	        	 }
	         } 
	         if (canbeword >= 4) {
    			 if(hashMap.containsKey(tempword)) {
    				 Integer hvalue = ((Integer)hashMap.get(tempword))+1;
    				 hashMap.put(tempword, hvalue);
    			 }else {
    				 hashMap.put(tempword, 1);
    			 }
    			 wordcount++;
    		 }
	         if(linechcount>0) {
	        	 linecount++;
	         }
	         fr.close(); 
	         outfile(foutname,"characters: "+chcount+'\n'+"words: "+wordcount+'\n'+"lines: "+linecount);
        } 
        catch(ArrayIndexOutOfBoundsException ex) 
        { 
         System.out.println("\nPlease Enter the File Name in Command Line Argument. \n"+ 
         "For Example :- java WordCount File1.txt File2.txt"); 
        } 
        catch(IOException ex) 
        { 
          System.out.println("File Does Not Found in given Directory. "); 
        } 
    }
}
