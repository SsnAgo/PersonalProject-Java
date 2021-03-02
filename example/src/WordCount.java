import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeMap;

public class WordCount {
	static TreeMap<String, Integer > treeMap=new TreeMap<String,Integer>();
    public static void main(String args[]) 
    { 
      	 String finname = args[0];
      	 
    } 
    public void wordmanage(String finname) {
    	
    	try 
        { 
    		FileInputStream fr=new FileInputStream(new File(finname)); 

         int i=0; 
         while((i=fr.read())!=-1) 
         { 
          System.out.print((char)i); 
         } 
         fr.close(); 
        } 
        catch(ArrayIndexOutOfBoundsException ex) 
        { 
         System.out.println("\nPlease Enter the File Name in Command Line Argument. \n"+ 
         "For Example :- java MyFileReader C:\\Users\\Desktop\\File1.txt"); 
        } 
        catch(IOException ex) 
        { 
          System.out.println("File Does Not Found in given Directory. "); 
        } 
    }
}
