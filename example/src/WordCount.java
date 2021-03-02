
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeMap;

public class WordCount {
	static TreeMap<String, Integer > treeMap=new TreeMap<String,Integer>();
    public static void main(String args[])
    {
      	 String finname = args[0];
      	 //String foutname = args[1];
    }
    public void wordmanage(String finname) {
    	try 
        {
    		 FileInputStream fr=new FileInputStream(new File(finname));
	         int i=0;
	         char ch;
	         int chcount=0;   //字符总数
	         int wordcount=0;	//单词总数
	         int canbeword=0; 	//每次增加，当最后大于四的时候成立
	         int canbetransferred=0;//可能为\n\r
	         StringBuffer tempword;
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
	        	 * 2、将进入下一层判断
	        	 */
	        		 if (ch != 'r' || ch != 'n'){
	        			 chcount--;
	        			 continue;
	        		 }else {
	        			 
	        		 }
	        	 }
	        	 if (!Character.isLetter(ch) && !Character.isDigit(ch)){
	        		 /* 分割符
	 	        	 * 1、判断tempword是否可为word（通过canbeword=0是否为4），若是则加入Treemap, 且wordcount=0++;
	 	        	 * 2、将tempword、canbeword重置
	 	        	 * 3、将进入下一层判断是否为'r'或'n'
	 	        	 */
	        		 if ((i==92)){
	        			 canbetransferred=1;
	        		}else {
	        			
	        		}
	        	 }
	        	 if (Character.isLetter(ch)){
	        		 canbeword++;
	        		 if(Character.isUpperCase(ch)) {
	        			 ch = Character.toLowerCase(ch);
	        		 }
	        		 tempword.append(ch);
	        	 }else if() {
	        		 
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
