/*
    Created at 2021/2/25 by GONGHAIXU
 */
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    public static String getFullFile(String fileName){
        String encoding = "UTF-8";
        File InputFile = new File(fileName);
        Long fileLength = InputFile.length();
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

    public static boolean outputStringToFile(String fileName,String fileContent){
        File outputFile = new File(fileName);
        try(OutputStream outputStream = new FileOutputStream(outputFile)){
            byte[] data = fileContent.getBytes(StandardCharsets.UTF_8);
            outputStream.write(data);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            System.out.print("IO异常,程序中断");
            return false;
        }

    }

}
