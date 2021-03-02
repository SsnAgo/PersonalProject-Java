import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Lib {


    public static String GetCharacters(String filePath){

        int num=0;
        BufferedReader bufferedReader=null;
        StringBuilder str=null;

        try {
            FileReader reader=new FileReader(filePath);
            bufferedReader=new BufferedReader(reader);
            str=new StringBuilder();
            int flag;
            str.append(bufferedReader.read());
            while ((flag=bufferedReader.read())!=-1){
                str.append(flag);
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

    static int GetCharactersNum(String temp){

        int num=0;

        return num;

    }

    static int GetWordsNum(String temp){

        int num=0;

        return num;

    }

    static int GetLinesNum(String temp){

        int num=0;

        return num;

    }

}
