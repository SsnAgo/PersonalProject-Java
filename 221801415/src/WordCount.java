package wordcount_java;

import java.io.*;
import java.util.List;
import java.util.Map;

public class WordCount 
{

	public static void countNumber(String inPath, String outPath)throws IOException 
	{
		Lib lib=new Lib();
		 String str=lib.charactersCount(inPath);
		 
	        int charNum=lib.charactersNumberCount(str);
	        int wordNum=lib.wordsNumberCount(str);
	        int lineNum=lib.linesNumberCount(inPath);
	        int i=0;
	        
	        List<Map.Entry<String,Integer>> wordsList=lib.SortMap();
	       
	        String wordsSort="";
	        
	        //获取频率前十单词
	        for(Map.Entry<String,Integer>map:wordsList)
	        {
	            wordsSort+=map.getKey()+":"+map.getValue()+"\n";
	            i++;
	            if(i==10)
	            {
	                break;
	            }
	        }
	        
	        lib.writeNum(charNum,wordNum,lineNum,wordsSort,outPath);
	        
	        //System.out.print(charNum);
	}
	
    public static void main(String[] args) throws IOException 
    {

        
        String inPath= args[0];
        String outPath=args[1];
        
        WordCount.countNumber(inPath,outPath);
        


       

    }
}
