package wordcount_java;
import java.io.*;
import java.util.*;

public class Lib 
{

    static Map<String,Integer> wordsMap=new HashMap<>();

    //获取文件的所有内容
    public static String charactersCount(String filePath) throws IOException
    {

        BufferedReader bReader=null;
        StringBuilder str=null;

        try 
        {
            FileReader reader=new FileReader(filePath);
            bReader=new BufferedReader(reader);
            str=new StringBuilder();
            int flag;
            //str.append(bufferedReader.read());
            while ((flag=bReader.read())!=-1)
            {
                str.append((char)flag);
            }
        } catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        bReader.close();

        return str.toString();
    }

    
    //字数总数统计
    public static int charactersNumberCount(String str)
    {

    	//将字符串改为字符
        char[] ch = str.toCharArray();
        int num = 0;
        for(int i = 0; i < ch.length; i++) {
            if(ch[i] >= 0 && ch[i] <= 127) {
                num++;
            }
        }
        
        return num;
    }
    
    
    //单词字数统计
    public static int wordsNumberCount(String str)
    {

        int num=0;
        
        //将所有字符用分隔符分开
        String[] word=str.split("\\s+");
        
        //判断是否为单词的条件
        String strings="^[a-zA-Z]{4,}.*";
        for(int i=0;i<word.length;i++)
        {
            if(word[i].matches(strings))
            {
                num++;
                
                //转化为小写
                String lowCase=word[i].toLowerCase();
                
                //将数据插入map在利用map实现排序
                if (wordsMap.containsKey(lowCase))
                {
                    int j=wordsMap.get(lowCase);
                    wordsMap.put(lowCase,j+1);
                }
                else 
                {
                    wordsMap.put(lowCase,1);
                }
            }
        }

        return num;
    }
    
  //计算行数
    public static int linesNumberCount(String filePath)
    {
    	File file=new File(filePath);
    	int count=0;
    	
    	if(file.exists()) 
    	{
    		try
    		{
    			BufferedReader in = new BufferedReader(new FileReader(file));
    			String line;
    			while((line = in.readLine()) != null)
    			{
    				//忽略空行
    				if(!line.equals("") )
    				{
    					count ++;
    				}
    			}
    			in.close();
    		}
    		catch(FileNotFoundException e)
    		{
    			e.printStackTrace();
    		}
    		catch(IOException e)
    		{
    			e.printStackTrace();
    		}
    		
    	}

        return count;
    }

    //按单词频率进行排序
    public List<Map.Entry<String,Integer>> SortMap()
    {

        List<Map.Entry<String,Integer>> wordList=new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());

        Collections.sort(wordList, new Comparator<Map.Entry<String, Integer>>() 
        {
        	
        	//利用匿名内部类实现排序
            @Override
            public int compare(Map.Entry<String, Integer> map1, Map.Entry<String, Integer> map2) 
            {
                if (map1.getValue().equals(map2.getValue()))
                {
                    return map1.getKey().compareTo(map2.getKey());
                }
                else 
                {
                    return map2.getValue()-map1.getValue();
                }
            }
        	
        });

        return wordList;
    }
    
}

