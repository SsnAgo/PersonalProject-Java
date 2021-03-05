import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

 
public class WordCount {  
  
    public static void main(String[] args) throws Exception {  
        String inputfile = args[0];
    
    	BufferedReader br = new BufferedReader(new FileReader(inputfile));  
    	int characterscount=0;
        int wordline = 0;
        int wordcount = 0;
        List<String> lists = new ArrayList<String>();  //存储处理后的列表  
        String readLine = null;
		while((readLine = br.readLine()) != null) {  
			wordline++;
            readLine = readLine.toLowerCase();
            
            characterscount += Lib.CountEachLineCharacter(readLine);//统计每行的字符数
                        
            wordcount += Lib.CountWordNum(readLine, lists);
        }           
        br.close();  
          
        Map<String, Integer> wordsCount = new TreeMap<String,Integer>(); 
        Lib.CountEachWordNum(wordsCount, lists);
          
        Print p = new Print(args[1]);
        p.SortMap(wordsCount,wordline,wordcount,characterscount+wordline);  //排序并输出
    }



}  
