import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordCount {

	public static void readTextFile(String filePath){
        try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(read);
                    StringBuilder lineText = new StringBuilder();
                    int lines=0;//用于统计行数
                    String temp;
                    while((temp=bufferedReader.readLine()) !=null){
                    	lineText.append(temp+"\n");
                        lines++;
                    }      
                    read.close();        
                    System.out.println("characters:"+countChars(lineText.toString()));
                    System.out.println("words:"+countWords(lineText.toString()));
                    System.out.print("lines:"+lines);
                    //countWords(lineText);
                    
                }else{
                	System.out.println("找不到该文档！");
                }
        } catch (Exception e) {
            System.out.println("打开文档出错！");
            e.printStackTrace();
        }
     
    }
    public static int countChars(String str) {	//返回字符数量
    	return str.length()-1;        
    }
    public static int countWords(String str) {	//返回单词数量
    	str=str.toLowerCase();
    	String[] strArray=str.split("[^a-z0-9]");	//先以除了数字字母的字符来分割
    	int words=0;
    	List<String> list = new ArrayList<String>();
    	for(int i=0;i<strArray.length; i++) {
    	   if (!list.contains(strArray[i])) {
    	       list.add(strArray[i]);
    	   }
    	}
    	strArray = list.toArray(new String[list.size()]);//把字符串数组过滤为没有重复元素的
    	for(int i=0;i<strArray.length;i++) {
    		if(strArray[i].length()<4)continue;
    		else {
    			String temp=strArray[i].substring(0,4);
    			if(temp.matches("[a-z]*")) {		//判断是否符合单词规则
    				words++;
    			}
    		}
    	}
    	return words;
    }
    public static void main(String argv[]){
        readTextFile("input.txt");
    }
     
}
