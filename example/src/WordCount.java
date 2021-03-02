
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;;

public class WordCount {
	static HashMap<String, Integer > hashMap=new HashMap<String,Integer>();
    public static void main(String args[])
    {
      	 String finname = new String(args[0]);
      	 //String foutname = args[1];
      	 wordmanage(finname);
 		Set<Map.Entry<String, Integer>> ms =hashMap.entrySet();
 		for (Map.Entry<String, Integer> entry : ms) {
 			System.out.print(entry.getKey()+"="+entry.getValue());
 		}
    }
    public static void wordmanage(String finname) {
    	try 
        {
    		 FileInputStream fr=new FileInputStream(new File(finname));
	         int i=0;
	         char ch;
	         int chcount=0;   //字符总数
	         int wordcount=0;	//单词总数
	         int canbeword=0; 	//每次增加，当最后大于四的时候成立
	         int canbetransferred=0;//可能为\n\r
	         StringBuffer tempword = new StringBuffer();
	         //每个单词一次循环
	         while ((i=fr.read()) != -1){
	        	 ch = (char)i;
	        	 chcount++;  //字符统计
	        	 
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
	        		 if (ch == 'r' || ch == 'n'){
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
	        				 hashMap.put(tempword.toString(), hvalue);
	        			 }
	        		 }
        			 tempword=null;
        			 canbeword=0;
	        	 }else if (Character.isLetter(ch)){
	        		 canbeword++;
	        		 if(Character.isUpperCase(ch)) {
	        			 ch = Character.toLowerCase(ch);
	        		 }
	        		 tempword.append(ch);
	        	 }else if(Character.isDigit(ch)) {
	        		 if (canbeword < 4) {
	        			 canbeword=0;
	        			 tempword=null;
	        		 }else {
	        			 tempword.append(ch);
	        		 }
	        	 }
	         } 
	         fr.close(); 
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
