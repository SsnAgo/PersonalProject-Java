import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
public class WordCount {
    public static void main(String[] args) {
        Lib lib = new Lib();
        if (args.length == 2) {
            try {
                File inputFile = new File(args[0]);
                File outputFile = new File(args[1]);
                BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
				
                //获取字符个数并输出
                int characterNum = lib.statisticsCharacters(inputFile);
                buffWriter.write("characters: " + characterNum);
                buffWriter.newLine();
				
                //获取单词个数并输出
                int wordNum = lib.statisticsWords(inputFile);
                buffWriter.write("words: " + wordNum);
                buffWriter.newLine();
				
                //获取有效行数并输出
                int lineNum = lib.statisticsLines(inputFile);
                buffWriter.write("lines: " + lineNum);
                buffWriter.newLine();
                
                //对单词频率映射表进行排序并输出出现频率最高的前十个单词
                List<Map.Entry<String, Integer>> list=lib.wordsFrequency(inputFile);
                int num = 1;
                for(Map.Entry<String, Integer> map : list) {  
                    if(num <= 10) {  
                        buffWriter.write(map.getKey() + ": " + map.getValue());
                        buffWriter.newLine();
                        num++;  
                    }else break;  
                }
                buffWriter.close();
            }catch (FileNotFoundException e) {
                System.out.print("无法找到文件");
            }catch(IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("文件输入个数错误");
        }
    }
}

