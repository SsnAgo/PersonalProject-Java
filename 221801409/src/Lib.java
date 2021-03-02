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
            String temp=null;
            while((temp=br.readLine())!=null){
                result.append(temp+"\n");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
