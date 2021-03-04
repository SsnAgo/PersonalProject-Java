package wordcount_java;
import java.io.*;
import java.util.*;

public class Lib 
{

    static Map<String,Integer> wordsMap=new HashMap<>();


    public static String getCharacters(String filePath)
    {

        int num=0;
        BufferedReader bufferedReader=null;
        StringBuilder str=null;

        try 
        {
            FileReader reader=new FileReader(filePath);
            bufferedReader=new BufferedReader(reader);
            str=new StringBuilder();
            int flag;
            //str.append(bufferedReader.read());
            while ((flag=bufferedReader.read())!=-1)
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
        }finally 
        {
            try 
            {
                bufferedReader.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        return str.toString();
    }

    public static int getCharactersNumber(String str)
    {

        int num=0;

        char[] temp=str.toCharArray();
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i]>=0&&temp[i]<=127)
            {
                num++;
            }
        }

        return num;
    }

    static int getWordsNumber(String str)
    {

        int num=0;
        String[] temp=str.split("\\s+");
        String regexs="^[a-zA-Z]{4,}.*";
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i].matches(regexs))
            {
                num++;
                String insertKey=temp[i].toLowerCase();
                if (wordsMap.containsKey(insertKey))
                {
                    int j=wordsMap.get(insertKey);
                    wordsMap.put(insertKey,j+1);
                }
                else 
                {
                    wordsMap.put(insertKey,1);
                }
            }
        }

        return num;
    }

    public List<Map.Entry<String,Integer>> SortMap()
    {

        List<Map.Entry<String,Integer>> wordList=new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());

        Collections.sort(wordList, new Comparator<Map.Entry<String, Integer>>() 
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) 
            {
                if (o1.getValue().equals(o2.getValue()))
                {
                    return o1.getKey().compareTo(o2.getKey());
                }
                else 
                {
                    return o2.getValue()-o1.getValue();
                }
            }
        });

        return wordList;
    }

    /*public String GetSortWord(){
        List<Map.Entry<String,Integer>> wordsList=SortMap();
        String words="";
        int i=0;
        for(Map.Entry<String,Integer>map:wordsList){
            words+=map.getKey()
        }
    }*/

    static int getLinesNumber(String filePath)
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

}

