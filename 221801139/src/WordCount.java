import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

 
public class WordCount {  
  
    public static void main(String[] args) throws Exception {  
        File file = new File("input.txt");
    	String tempathname = file.getPath();
    	
    	 new Read();
		 String pathname = Read.ReadFile(tempathname);
    	    	
    	BufferedReader br = new BufferedReader(new FileReader(pathname));  
    	
    	int characterscount=0;
        int wordline = 0;
        int wordcount = 0;
        List<String> lists = new ArrayList<String>();  //存储处理后的列表  
        String readLine = null;
		while((readLine = br.readLine()) != null) {  
			wordline++;
            String[] wordsArr1 = readLine.split("[^a-zA-Z0-9]");  //切割筛选单词
            
            characterscount+=readLine.length();//统计每行的字符数
                        
            for (String newword : wordsArr1) {  
                if(newword.length() != 0) {               	
                	if((newword.length()>=4)&&(Character.isLetter(newword.charAt(0))
                        &&Character.isLetter(newword.charAt(1))&&Character.isLetter(newword.charAt(2))
                        &&Character.isLetter(newword.charAt(3)))) {               		
                        wordcount++;
                        lists.add(newword);  
                	}
                }  
            }  
        }           
        br.close();  
          
        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();  
          
        for (String li : lists) {  //单词出现个数统计
            if(wordsCount.get(li) != null) { 
                wordsCount.put(li,wordsCount.get(li) + 1);  
            }
            else {  
                wordsCount.put(li,1);  
            }  
  
        }  
          
        Print.SortMap(wordsCount,wordline,wordcount,characterscount+wordline-1);  //排序并输出
    }



}  
