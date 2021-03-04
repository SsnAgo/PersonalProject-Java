import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Lib {

    public static BufferedReader openInputFile(String fileName) {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),
                    StandardCharsets.UTF_8));
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

    public static int characterCount(String inputFile,BufferedWriter bufferedWriter){
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

        return count;
    }

    public static int lineCount(String inputFile,BufferedWriter bufferedWriter){
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
        return count;
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

    public static void printWord(BufferedWriter bufferedWriter,String [] resultStr){
        Map<String , Integer> resultMap=new TreeMap<String, Integer>();
        int count = 0;
        int num = 1;

        for (String s : resultStr) {
            if (s.matches("[a-z]{4}[a-z0-9]*")) {
                if (resultMap.containsKey(s)) {
                    //containKey()方法用于检查特定键是否在TreeMap中映射
                    count = resultMap.get(s);
                    resultMap.put(s, count + 1);
                } else {
                    resultMap.put(s, 1);
                }
            }
        }
        //通过比较器实现排序
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(resultMap.entrySet());
        //按降序排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) {
                if(second.getValue().compareTo(first.getValue()) == 0) {
                    //如果单词频数相同，返回字典序较大的单词
                    return second.getKey().compareTo(first.getKey());
                }
                //返回两个单词出现次数较多的那个单词的出现次数
                return second.getValue().compareTo(first.getValue());
            }
        });

        try {
            for (Map.Entry<String, Integer> map : list) {
                if (num <= 10) {
                    bufferedWriter.write(map.getKey() + ":" + map.getValue()+'\n');
                    num++;
                } else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
