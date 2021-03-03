import java.io.*;
import java.util.*;

public class Lib {

    public static BufferedReader openInputFile(String fileName) {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bufferedReader;
    }

    public static BufferedWriter openOutputFile(String fileName) {
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedWriter;
    }

    public static void characterCount(String inputFile,BufferedWriter bufferedWriter){
        BufferedReader bufferedReader = Lib.openInputFile(inputFile);
        int count = 0;
        int temp;

        try {
            while((temp = bufferedReader.read()) != -1){
                count ++;
            }

            bufferedWriter.write("characters:"+count+'\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lineCount(String inputFile,BufferedWriter bufferedWriter){
        BufferedReader bufferedReader = Lib.openInputFile(inputFile);
        String temp;
        int count = 0;

        try {
            while ((temp = bufferedReader.readLine()) != null) {
                //如果读出来的行不是空行（包含只含有空格的行），行数加1
                if(!temp.matches("[\\s]*")) count++;
            }

            bufferedReader.close();
            bufferedWriter.write("lines:"+count+'\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String [] wordCount(String inputFile,BufferedWriter bufferedWriter){
        BufferedReader bufferedReader = Lib.openInputFile(inputFile);
        String originStr = null;
        String tempStr = null;
        String [] resultStr = null;
        StringBuffer tempBuffer = new StringBuffer();
        int totalCount = 0;

        try {
            while((originStr = bufferedReader.readLine())!=null){
                tempBuffer.append(originStr+" ");
            }

            bufferedReader.close();
            tempStr = tempBuffer.toString().toLowerCase();
            resultStr = tempStr.split("[^a-zA-Z0-9]+");

            for (String s : resultStr) {
                if (s.matches("[a-z]{4}[a-z0-9]*")) {
                    totalCount++;
                }
            }

            bufferedWriter.write("Words:"+totalCount+'\n');
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }


}

