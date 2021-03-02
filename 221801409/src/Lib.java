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
            br.close();
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
            //只计算ACSII字符
        }
        return count;
    }
    public static int getWordsCount(String str){
        int count=0;
        String temp=null;
        String regex="^[a-z]+$";
        str=str.toLowerCase();//将字符串变成小写
        String[] strArray=str.split("[^a-z0-9]+");
        //用split方法将非字母数字的字符作为分割线分割成单词数组，字符串已经是小写了，不存在A-Z
        for(int i=0;i<strArray.length;i++){
            if(strArray[i].length()>=4){//至少要四个英文字母开头，也就是说单词至少需要四个字符
                temp=strArray[i].substring(0,3);//取出分割后字符串的前四个字符
                if(temp.matches(regex))
                    count++;
            }
        }
        return count;
    }
}
