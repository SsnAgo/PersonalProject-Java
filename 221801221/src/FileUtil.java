/*
    Created at 2021/2/25 by GONGHAIXU
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtil {

    public static String getFullFile(String fileName){
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try(FileInputStream in = new FileInputStream(fileName)){
            in.read(fileContent);
            return new String(fileContent, encoding);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.print("文件不存在!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("IO异常,程序中断");
        }
        return null;
    }

}
