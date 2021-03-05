import java.util.List;
import java.util.Map;

public class Lib {
    public static int CountEachLineCharacter(String line) {
        return line.length();
    }

    public static int CountWordNum(String line, List<String> lists) {
        int wordcount = 0;
        String[] wordsArr1 = line.split("[^a-zA-Z0-9]");  //切割筛选单词

        for (String newword : wordsArr1) {  
            if(newword.length() != 0) {               	
                if((newword.length() >= 4) && (Character.isLetter(newword.charAt(0))
                    && Character.isLetter(newword.charAt(1)) && Character.isLetter(newword.charAt(2))
                    && Character.isLetter(newword.charAt(3)))) {               		
                    wordcount++;
                    lists.add(newword);  
                }
            }  
        }

        return wordcount;
    }

    public static void CountEachWordNum(Map<String, Integer> wordsCount,List<String> lists) {
 
          
        for (String li : lists) {  //单词出现个数统计
            if(wordsCount.get(li) != null) { 
                wordsCount.put(li,wordsCount.get(li) + 1);  
            }
            else {  
                wordsCount.put(li,1);  
            }  
  
        }  
    }

}
