import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordCount {

	public static String readTextFile(String filePath){
		StringBuilder Text = new StringBuilder();
		try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(read);
                    int x;
                    while((x=bufferedReader.read()) !=-1){
                    	Text.append((char)x);
                    }
                    read.close();

                }else{
                	System.out.println("找不到该文档！");
                }
        } catch (Exception e) {
            System.out.println("打开文档出错！");
            e.printStackTrace();
        }
		return Text.toString();
     
    }
	
	
	
    public static int countChars(String str) {	//返回字符数量
    	int sum=0;
    	char[] cs=str.toCharArray();
    	for(int i=0;i<cs.length;i++) {
    		if(cs[i]>=0&&cs[i]<128)sum++;
    	}
    	return sum;
    }
    
    
    
    
    public static int countWords(String str) {	//返回单词数量
    	str=str.toLowerCase();
    	String[] strArray=str.split("[^a-z0-9]+");	//先以除了数字字母的字符来分割
    	int words=0;
    	for(int i=0;i<strArray.length;i++) {
    		System.out.println(strArray[i]);
    	}
    	/*List<String> list = new ArrayList<String>();
    	for(int i=0;i<strArray.length; i++) {
    	   if (!list.contains(strArray[i])) {
    	       list.add(strArray[i]);
    	   }
    	}
    	strArray = list.toArray(new String[list.size()]);//把字符串数组过滤为没有重复元素的*/
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
        String str=readTextFile("input.txt");
        System.out.println("characters:"+countChars(str));
        System.out.println("words:"+countWords(str));
        //System.out.print("lines:"+countLines(str));
        //countWords(lineText);
    }
     
}
