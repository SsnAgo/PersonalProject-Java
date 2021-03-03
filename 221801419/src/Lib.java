import java.io.*;
import java.util.*;

public class Lib {

    static Map<String,Integer> wordsMap=new HashMap<>();


    public static String getCharacters(String filePath){

        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        StringBuilder str=null;

        try {
            fileReader=new FileReader(filePath);
            bufferedReader=new BufferedReader(fileReader);
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
                fileReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return str.toString();
    }

    public static int getCharactersNum(String str){

        int num=0;

        char[] temp=str.toCharArray();
        for(int i=0;i<temp.length;i++){
            if(temp[i]>=0&&temp[i]<=127){
                num++;
            }
        }

        return num;
    }

    static int getWordsNum(String str){

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

    public List<Map.Entry<String,Integer>> sortMap(){

        List<Map.Entry<String,Integer>> wordList= new ArrayList<>(wordsMap.entrySet());

        Collections.sort(wordList, (o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())){
                return o1.getKey().compareTo(o2.getKey());
            }else {
                return o2.getValue()-o1.getValue();
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

    /*static int GetLinesNum(String str){

        int num=0;

        char[] temp=str.toCharArray();
        for(int i=1;i<temp.length;i++){
            if(temp[i]==10){
                num++;
                if(temp[i-1]==10){
                    num--;
                }
            }

        }

        return num;
    }*/

    public static int getLinesNum(String filePath){

        int num=0;
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;

        try {
            fileReader=new FileReader(filePath);
            bufferedReader=new BufferedReader(fileReader);
            String flag;
            while ((flag=bufferedReader.readLine())!=null){
                if(flag.length()>0&&!flag.matches("\\s+")){
                    num++;
                }
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return num;
    }


}
