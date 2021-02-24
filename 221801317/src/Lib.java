import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class Lib {
    public static void main(String[] args) {
        TextFileSolver solver = new TextFileSolver("/Users/sarisemac/eclipse-workspace/testFunction/src/h.txt");

        System.out.println("characters: "+solver.getFileCharNum());
        System.out.println("words: "+solver.getWordNum());
        System.out.println("lines: "+solver.getValidLineNum());
        solver.getOrderedWordFrequencyMap(10).forEach((word,frequency)->{
            System.out.println(word+":"+frequency);
        });
//        IOUtil.getStrings("/Users/sarisemac/eclipse-workspace/testFunction/src/h.txt").forEach(s -> {
//            System.out.println(s);
//        });
        IOUtil.writeTo("/Users/sarisemac/eclipse-workspace/testFunction/src/output.txt", "hello");
    }
    public static class TextFileSolver{
        String filePath;
        StringBuilder fileText;
        List<String> strings;
        Map<String,Long> wordFrequencyMap;
        int fileCharNum;
        int validLineNum;

        public TextFileSolver(String filePath) {
            this.filePath = filePath;
            fileCharNum = 0;
            validLineNum=0;
            fileText = new StringBuilder();

            strings = IOUtil.getStrings(filePath);
            for (String s: strings) {
                solveString(s);
            }
            wordFrequencyMap = Arrays.asList(fileText.toString().split("\\s+"))
                    .stream()
                    .filter(word->{
                        if (word.length()<4) return false;
                        char[] chars = word.toCharArray();
                        for (int i = 0 ; i < 4 ; i++){
                            if (! Character.isLetter(chars[i])) return false;
                        }
                        return true;
                    })
                    .collect(Collectors.groupingBy(String::toLowerCase,Collectors.counting()));

        }

        public int getFileCharNum(){
            return fileCharNum+strings.size()-1;
        }
        public long getWordNum(){
            long num = 0;
            for (Map.Entry<String,Long> pair: wordFrequencyMap.entrySet()) {
                num += pair.getValue();
            }
            return num;
        }

        public int getValidLineNum(){
            return validLineNum;
        }
        public Map<String,Long> getOrderedWordFrequencyMap(int limit){
            limit = wordFrequencyMap.size()>limit?limit:wordFrequencyMap.size();
            return  wordFrequencyMap
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Long> comparingByValue()
                            .reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .limit(10)
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (oldVal, newVal) -> oldVal,
                                    LinkedHashMap::new
                            )
                    );
        }
        private void solveString(String s){
            fileCharNum+=s.length();
            s.trim();
            if (s.isEmpty()){
                return;
            }
            validLineNum++;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) { //非数字 非英文单词 转化为空白字符
                char cur = chars[i];
                if (!Character.isLetterOrDigit(cur)){
                    chars[i] =' ';
                }
            }
            fileText.append(' ').append(chars);
        }

    }
    public static class IOUtil{
        public static List<String> getStrings(String inputFilePath){
            ArrayList<String> strings = new ArrayList<>();
            File inputFile = new File(inputFilePath);
            BufferedReader reader = null;
            try {
                InputStreamReader fileInputStream = new InputStreamReader(
                        new FileInputStream(inputFile),"ascii");
                reader = new BufferedReader(fileInputStream);
                for (String temp = reader.readLine() ;temp !=null ; temp=reader.readLine()) {
                    strings.add(temp);
                }
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("错误");
            }finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        System.out.println("输入流关闭异常");
                    }
                }
            }
            return strings;
        }

        public static void writeTo(String outputFilePath,String content){
            File outputFile = new File(outputFilePath);
            BufferedWriter writer=null;
            try{
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFile), "utf-8"));

                writer.write(content);
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("文件读取错误");
            }finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("输出流关闭异常");
                    }
                }
            }
        }

    }
}