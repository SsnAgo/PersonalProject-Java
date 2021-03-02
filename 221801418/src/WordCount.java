import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class WordCount {

	public static void readTxtFile(String filePath){
        try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
                    BufferedReader bufferedReader = new BufferedReader(read);
                    StringBuilder lineText = new StringBuilder();
                    int lines=0;
                    String temp;
                    while((temp=bufferedReader.readLine()) !=null){
                    	lineText.append(temp);
                        System.out.println(lineText);
                        lines++;
                    }      
                    read.close();
                    //countLetters(lineText.toString());
                    //countTimes();
                    //countNumbers(lineText);
                    
                }else{
                	System.out.println("找不到指定的文件");
                }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
    }
    /*public static void countLetters(String str) {
    	
        
    }*/
    public static void main(String argv[]){
        readTxtFile("src\\input.txt");
    }
     
}
