import java.io.*;

public class CountCore {
    public static int characterCount(File inputFile){
        BufferedReader bufferedReader= null;
        int characterCount=0;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!(bufferedReader.read()!=-1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            characterCount++;
        }
        return characterCount;
    }

    public static void toLowerCase(File inputFile){
        BufferedReader bufferedReader= null;
        StringBuilder stringBuilder=new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            String str;
            while((str=bufferedReader.readLine())!=null){
                stringBuilder.append(str.toLowerCase());
                stringBuilder.append("\r\n");
            }
            bufferedReader.close();
            System.out.println(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        


    }
}
