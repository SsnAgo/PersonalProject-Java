import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class WordCount {

	public static void readTxtFile(String filePath){
        try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(read);
                    StringBuilder lineText = new StringBuilder();
                    int lines=0;
                    String temp;
                    while((temp=bufferedReader.readLine()) !=null){
                    	lineText.append(temp+"\n");
                        lines++;
                    }      
                    read.close();
                    System.out.print(countChars(lineText.toString()));
                    //countTimes();
                    //countWords(lineText);
                    
                }else{
                	System.out.println("找不到该文档！");
                }
        } catch (Exception e) {
            System.out.println("打开文档出错！");
            e.printStackTrace();
        }
     
    }
    public static int countChars(String str) {
    	return str.length();        
    }
    public static void main(String argv[]){
        readTxtFile("input.txt");
    }
     
}
