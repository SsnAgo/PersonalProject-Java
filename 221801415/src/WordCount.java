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
	       
	        String words="";
	        
	        
	        for(Map.Entry<String,Integer>map:wordsList)
	        {
	            words+=map.getKey()+":"+map.getValue()+"\n";
	            i++;
	            if(i==10)
	            {
	                break;
	            }
	        }
	        
	        FileOutputStream fileOutputStream=new FileOutputStream(outPath);
	        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
	        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
	        
	        bufferedWriter.write("characters:"+charNum+"\nwords:"+wordNum+"\nlines:"+lineNum);
	        
	        bufferedWriter.write("\n"+words);

	        bufferedWriter.flush();
	        
	        bufferedWriter.close();
	        
	        System.out.print(charNum);
	}
    public static void main(String[] args) throws IOException 
    {

        
        //String inPath= args[0];
        //String outPath=args[1];
        String inPath="D:\\eclipse\\wordcount_java\\src\\wordcount_java\\test.txt";
        String outPath="D:\\eclipse\\wordcount_java\\src\\wordcount_java\\te.txt";
        WordCount.countNumber(inPath,outPath);
        


       

    }
}
