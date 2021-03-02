import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Lib {
    public static String readFormTxt(String txtPath){
        File file= new File(txtPath);
        StringBuilder result=new StringBuilder();
        try{
            BufferedReader br=new BufferedReader(new FileReader(file));
            String str=null;
            while((str=br.readLine())!=null){
                result.append(str+"\n");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    public static int getCharactersCount(String str){
        int count=0;
        char[] ch=str.toCharArray();
        for(int i=0;i<ch.length;i++){
            if(ch[i]>=0&&ch[i]<128){
                count++;
            }
        }
        return count;
    }
}
