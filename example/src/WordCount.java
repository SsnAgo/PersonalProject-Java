import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WC {
    public static void main(String args[]) 
    { 
     try 
     { 
      FileInputStream fr=new FileInputStream(new File(args[0])); 
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
