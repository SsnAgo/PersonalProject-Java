import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Lib {

    static Map<String,Integer> wordsMap=new HashMap<>();

    public static String GetCharacters(String filePath){

        int num=0;
        BufferedReader bufferedReader=null;
        StringBuilder str=null;

        try {
            FileReader reader=new FileReader(filePath);
            bufferedReader=new BufferedReader(reader);
            str=new StringBuilder();
            int flag;
            //str.append(bufferedReader.read());
            while ((flag=bufferedReader.read())!=-1){
                str.append((char)flag);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return str.toString();
    }

    public static int GetCharactersNum(String str){

        int num=0;

        char[] temp=str.toCharArray();
        for(int i=0;i<temp.length;i++){
            if(temp[i]>=0&&temp[i]<=127){
                num++;
            }
        }

        return num;

    }

    static int GetWordsNum(String str){

        int num=0;
        String[] temp=str.split("\\s+");
        String regexs="^[a-zA-Z]{4,}.*";
        for(int i=0;i<temp.length;i++){
            if(temp[i].matches(regexs)){
                num++;
                String insertKey=temp[i].toLowerCase();
                if (wordsMap.containsKey(insertKey)){
                    int j=wordsMap.get(insertKey);
                    wordsMap.put(insertKey,j+1);
                }else {
                    wordsMap.put(insertKey,1);
                }
            }
        }

        return num;

    }

    static int GetLinesNum(String str){

        int num=0;
        String[] temp=str.split("\\s+");
        for (int i=0;i<temp.length;i++){
            if(temp[i].equals("\r\n")){
                num++;
            }
        }

        return num;

    }

}
